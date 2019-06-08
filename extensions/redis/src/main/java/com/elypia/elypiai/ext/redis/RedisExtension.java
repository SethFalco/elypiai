package com.elypia.elypiai.ext.redis;

import com.elypia.elypiai.common.core.ext.WrapperExtension;
import okhttp3.Protocol;
import okhttp3.Response;
import okhttp3.*;
import redis.clients.jedis.*;

import java.io.IOException;
import java.util.List;

public class RedisExtension implements WrapperExtension {

    /** The connection to Redis. */
    private Jedis jedis;

    /** The default TTL in seconds. */
    private int ttl;

    public RedisExtension(JedisShardInfo info, int ttl) {
        this(new Jedis(info), ttl);
    }

    public RedisExtension(Jedis jedis, int ttl) {
        this.jedis = jedis;
    }

    /**
     * Check Redis if it has cached a response for this
     * request already. Does nothing for non-GET requests.
     *
     * @param request The request that was executed.
     * @return The response if a response is applicable.
     */
    @Override
    public Response pre(Request request) throws IOException {
        if (!request.method().equals("GET"))
            return null;

        String key = request.toString();
        List<String> values = jedis.lrange(key, 0, 4);

        if (values.isEmpty())
            return null;

        String body = values.get(0);
        String mediaType = values.get(1);
        String protocol = values.get(2);
        int code = Integer.parseInt(values.get(3));
        String message = values.get(4);

        if (mediaType == null)
            return null;

        ResponseBody response = ResponseBody.create(MediaType.parse(mediaType), body);
        return new Response.Builder()
            .request(request)
            .protocol(Protocol.get(protocol))
            .code(code)
            .message(message)
            .body(response)
            .build();
    }

    @Override
    public Response post(Request request, Response response) throws IOException {
        ResponseBody body = response.body();

        if (body == null)
            return null;

        String bodyString = body.string();
        MediaType mediaType = body.contentType();
        String protocol = response.protocol().toString();
        int code = response.code();
        String message = response.message();

        if (bodyString == null || mediaType == null || request == null || protocol == null || code < 0 || message == null)
            return null;

        String key = request.toString();

        String[] values = {
            bodyString,
            mediaType.toString(),
            protocol,
            String.valueOf(code),
            message
        };

        jedis.rpush(key, values);
        jedis.expire(key, ttl);

        return response.newBuilder().body(ResponseBody.create(mediaType, bodyString)).build();
    }

    public Jedis getJedis() {
        return jedis;
    }
}
