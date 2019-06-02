package com.elypia.elypiai.ext.redis;

import com.elypia.elypiai.common.core.WrapperExtension;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisShardInfo;

public class RedisExtension implements WrapperExtension {

    /** The connection to Redis. */
    private Jedis jedis;

    /** The default TTL in seconds. */
    private int ttl;

    /** If expoential backoff should be used when results are the same. */
    private int exponentialBackoff;

    public RedisExtension(String host, int port) {
        this(new HostAndPort(host, port));
    }

    public RedisExtension(HostAndPort hostPort) {
        this(new JedisShardInfo(hostPort));
    }

    public RedisExtension(JedisShardInfo info) {
        jedis = new Jedis(info);
    }

    @Override
    public void pre() {

    }

    @Override
    public void post() {

    }

    public Jedis getJedis() {
        return jedis;
    }
}
