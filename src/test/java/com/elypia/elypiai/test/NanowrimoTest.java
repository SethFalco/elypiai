package com.elypia.elypiai.test;

import com.elypia.elypiai.nanowrimo.*;
import com.elypia.elypiai.nanowrimo.data.WordCountResponse;
import okhttp3.mockwebserver.*;
import org.junit.jupiter.api.*;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class NanowrimoTest {

    private static MockWebServer server;
    private static Nanowrimo nano;

    @BeforeEach
    public void beforeEach() throws IOException {
        server = new MockWebServer();
        server.start();

        nano = new Nanowrimo("http://localhost:" + server.getPort());
    }

    @AfterEach
    public void afterEach() throws IOException {
        server.close();
    }

    @Test
    public void testNano() {
        Nanowrimo nano = new Nanowrimo();
        assertNotNull(nano);
    }

    @Test
    public void getUser() throws IOException {
        server.enqueue(new MockResponse().setBody("<wc><uname>Cieria</uname><user_wordcount>50019</user_wordcount><uid>201596</uid><winner>true</winner></wc>"));

        NanoUser user = nano.getUser("Cieria").complete();
        assertAll("Try parse NaNoWriMo user.",
            () -> assertEquals("Cieria", user.getUsername()),
            () -> assertEquals(50019, user.getWordCount()),
            () -> assertEquals(201596, user.getId()),
            () -> assertTrue(user.isWinner()),
            () -> assertNull(user.getError())
        );
    }

    @Test
    public void setWordCount() throws IOException {
        server.enqueue(new MockResponse().setBody("ERROR no active event"));

        WordCountResponse response = nano.setWordCount("ba284cf3e4cd671", "Seth X3", 5);
        assertEquals(WordCountResponse.ERROR_NO_ACTIVE_EVENT, response);
    }
}
