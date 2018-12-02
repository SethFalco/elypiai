package com.elypia.elypiai.nanowrimo.test;

import com.elypia.elypiai.nanowrimo.*;
import com.elypia.elypiai.nanowrimo.data.WordCountError;
import okhttp3.mockwebserver.*;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

public class NanowrimoTest {

    private static MockWebServer server;
    private static Nanowrimo nano;

    @BeforeEach
    public void beforeEach() throws IOException {
        server = new MockWebServer();
        server.start();

        nano = new Nanowrimo(new URL("http://localhost:" + server.getPort()));
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

        Writer user = nano.getUser("Cieria").complete();
        assertAll("Try parse NaNoWriMo user.",
            () -> assertEquals("Cieria", user.getUsername()),
            () -> assertEquals(50019, user.getWordCount()),
            () -> assertEquals(201596, user.getId()),
            () -> assertTrue(user.isWinner()),
            () -> assertEquals("https://nanowrimo.org/participants/Cieria", user.getUrl()),
            () -> assertTrue(user.getEntries().isEmpty()),
            () -> assertNull(user.getError())
        );
    }

    @Test
    public void getUserHistory() throws IOException {
        server.enqueue(new MockResponse().setBody("<wchistory><uid>201596</uid><uname>Cieria</uname><user_wordcount>50019</user_wordcount><winner>true</winner><wordcounts><wcentry><wc>5009</wc><wcdate>2017-11-01</wcdate></wcentry><wcentry><wc>1768</wc><wcdate>2017-11-02</wcdate></wcentry><wcentry><wc>1859</wc><wcdate>2017-11-03</wcdate></wcentry><wcentry><wc>2885</wc><wcdate>2017-11-04</wcdate></wcentry><wcentry><wc>1686</wc><wcdate>2017-11-05</wcdate></wcentry><wcentry><wc>1707</wc><wcdate>2017-11-06</wcdate></wcentry><wcentry><wc>1746</wc><wcdate>2017-11-07</wcdate></wcentry><wcentry><wc>1609</wc><wcdate>2017-11-08</wcdate></wcentry><wcentry><wc>1747</wc><wcdate>2017-11-09</wcdate></wcentry><wcentry><wc>1858</wc><wcdate>2017-11-10</wcdate></wcentry><wcentry><wc>5322</wc><wcdate>2017-11-11</wcdate></wcentry><wcentry><wc>834</wc><wcdate>2017-11-12</wcdate></wcentry><wcentry><wc>0</wc><wcdate>2017-11-13</wcdate></wcentry><wcentry><wc>0</wc><wcdate>2017-11-14</wcdate></wcentry><wcentry><wc>0</wc><wcdate>2017-11-15</wcdate></wcentry><wcentry><wc>0</wc><wcdate>2017-11-16</wcdate></wcentry><wcentry><wc>0</wc><wcdate>2017-11-17</wcdate></wcentry><wcentry><wc>2400</wc><wcdate>2017-11-18</wcdate></wcentry><wcentry><wc>1718</wc><wcdate>2017-11-19</wcdate></wcentry><wcentry><wc>1211</wc><wcdate>2017-11-20</wcdate></wcentry><wcentry><wc>1685</wc><wcdate>2017-11-21</wcdate></wcentry><wcentry><wc>1749</wc><wcdate>2017-11-22</wcdate></wcentry><wcentry><wc>1589</wc><wcdate>2017-11-23</wcdate></wcentry><wcentry><wc>0</wc><wcdate>2017-11-24</wcdate></wcentry><wcentry><wc>4312</wc><wcdate>2017-11-25</wcdate></wcentry><wcentry><wc>1262</wc><wcdate>2017-11-26</wcdate></wcentry><wcentry><wc>1747</wc><wcdate>2017-11-27</wcdate></wcentry><wcentry><wc>2470</wc><wcdate>2017-11-28</wcdate></wcentry><wcentry><wc>1846</wc><wcdate>2017-11-29</wcdate></wcentry><wcentry><wc>0</wc><wcdate>2017-11-30</wcdate></wcentry></wordcounts></wchistory>"));

        Writer user = nano.getUser("Cieria", true).complete();
        assertAll("Try parse NaNoWriMo user with History.",
            () -> assertEquals("Cieria", user.getUsername()),
            () -> assertEquals(50019, user.getWordCount()),
            () -> assertEquals(201596, user.getId()),
            () -> assertTrue(user.isWinner()),
            () -> assertEquals(30, user.getEntries().size()),
            () -> assertNull(user.getError())
        );
    }

    @Test
    public void getUserHistoryWordCountEntry() throws IOException {
        server.enqueue(new MockResponse().setBody("<wchistory><uid>201596</uid><uname>Cieria</uname><user_wordcount>50019</user_wordcount><winner>true</winner><wordcounts><wcentry><wc>5009</wc><wcdate>2017-11-01</wcdate></wcentry><wcentry><wc>1768</wc><wcdate>2017-11-02</wcdate></wcentry><wcentry><wc>1859</wc><wcdate>2017-11-03</wcdate></wcentry><wcentry><wc>2885</wc><wcdate>2017-11-04</wcdate></wcentry><wcentry><wc>1686</wc><wcdate>2017-11-05</wcdate></wcentry><wcentry><wc>1707</wc><wcdate>2017-11-06</wcdate></wcentry><wcentry><wc>1746</wc><wcdate>2017-11-07</wcdate></wcentry><wcentry><wc>1609</wc><wcdate>2017-11-08</wcdate></wcentry><wcentry><wc>1747</wc><wcdate>2017-11-09</wcdate></wcentry><wcentry><wc>1858</wc><wcdate>2017-11-10</wcdate></wcentry><wcentry><wc>5322</wc><wcdate>2017-11-11</wcdate></wcentry><wcentry><wc>834</wc><wcdate>2017-11-12</wcdate></wcentry><wcentry><wc>0</wc><wcdate>2017-11-13</wcdate></wcentry><wcentry><wc>0</wc><wcdate>2017-11-14</wcdate></wcentry><wcentry><wc>0</wc><wcdate>2017-11-15</wcdate></wcentry><wcentry><wc>0</wc><wcdate>2017-11-16</wcdate></wcentry><wcentry><wc>0</wc><wcdate>2017-11-17</wcdate></wcentry><wcentry><wc>2400</wc><wcdate>2017-11-18</wcdate></wcentry><wcentry><wc>1718</wc><wcdate>2017-11-19</wcdate></wcentry><wcentry><wc>1211</wc><wcdate>2017-11-20</wcdate></wcentry><wcentry><wc>1685</wc><wcdate>2017-11-21</wcdate></wcentry><wcentry><wc>1749</wc><wcdate>2017-11-22</wcdate></wcentry><wcentry><wc>1589</wc><wcdate>2017-11-23</wcdate></wcentry><wcentry><wc>0</wc><wcdate>2017-11-24</wcdate></wcentry><wcentry><wc>4312</wc><wcdate>2017-11-25</wcdate></wcentry><wcentry><wc>1262</wc><wcdate>2017-11-26</wcdate></wcentry><wcentry><wc>1747</wc><wcdate>2017-11-27</wcdate></wcentry><wcentry><wc>2470</wc><wcdate>2017-11-28</wcdate></wcentry><wcentry><wc>1846</wc><wcdate>2017-11-29</wcdate></wcentry><wcentry><wc>0</wc><wcdate>2017-11-30</wcdate></wcentry></wordcounts></wchistory>"));

        WordCountEntry entry = nano.getUser("Cieria", true).complete().getEntries().get(0);
        assertAll("Try parse the first WordCountEntry from a NaNoWriMo user with history.",
            () -> assertEquals(5009, entry.getWordcount()),
            () -> assertEquals(1509494400000L, entry.getDate().getTime())
        );
    }

    @Test
    public void getUserWithError() throws IOException {
        server.enqueue(new MockResponse().setBody("<wc><error>user does not have a current novel</error></wc>"));

        Writer user = nano.getUser("Seth X3").complete();
        assertAll("Try parse NaNoWriMo user.",
            () -> assertNull(user.getUsername()),
            () -> assertEquals(0, user.getWordCount()),
            () -> assertEquals(0, user.getId()),
            () -> assertFalse(user.isWinner()),
            () -> assertEquals(WordCountError.USER_DOES_NOT_HAVE_A_CURRENT_NOVEL, user.getError())
        );
    }
}
