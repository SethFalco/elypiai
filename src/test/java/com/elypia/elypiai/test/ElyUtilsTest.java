package com.elypia.elypiai.test;

import com.elypia.elypiai.utils.ElyUtils;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ElyUtilsTest {

    @Test
    public void convertTimes() {
        assertAll("Convert Lots of Times",
            () -> assertEquals(60, ElyUtils.convertTime(1, TimeUnit.MINUTES, TimeUnit.SECONDS)),
            () -> assertEquals(60, ElyUtils.convertTime(1, TimeUnit.HOURS, TimeUnit.MINUTES)),
            () -> assertEquals(3600, ElyUtils.convertTime(1, TimeUnit.HOURS, TimeUnit.SECONDS)),
            () -> assertEquals(300, ElyUtils.convertTime(5, TimeUnit.MINUTES, TimeUnit.SECONDS)),
            () -> assertEquals(24, ElyUtils.convertTime(1, TimeUnit.DAYS, TimeUnit.HOURS)),
            () -> assertEquals(86400, ElyUtils.convertTime(1, TimeUnit.DAYS, TimeUnit.SECONDS)),
            () -> assertEquals(5, ElyUtils.convertTime(5000, TimeUnit.MILLISECONDS, TimeUnit.SECONDS))
        );
    }
}
