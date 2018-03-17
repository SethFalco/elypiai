package com.elypia.elypiai.test;

import com.elypia.elypiai.utils.math.MathUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MathUtilsTest {

    @Test
    public void writeNumbers() {
        assertAll("Write Numbers from Values",
            () -> assertEquals("one", MathUtils.asWritten(1)),
            () -> assertEquals("nine quintillion two hundred and twenty-three quadrillion three hundred and seventy-two trillion and thirty-six billion eight hundred and fifty-four million seven hundred and seventy-five thousand eight hundred and seven", MathUtils.asWritten(9223372036854775807L)),
            () -> assertEquals("one hundred and twenty-four thousand", MathUtils.asWritten(124000)),
            () -> assertEquals("negative one hundred", MathUtils.asWritten(-100)),
            () -> assertEquals("one thousand two hundred and thirty-four", MathUtils.asWritten(1234)),
            () -> assertEquals("two hundred thousand two hundred", MathUtils.asWritten(200200)),
            () -> assertEquals("zero", MathUtils.asWritten(0)),
            () -> assertEquals("twelve", MathUtils.asWritten(12)),
            () -> assertEquals("seven hundred and seventy-seven", MathUtils.asWritten(777)),
            () -> assertEquals("zero", MathUtils.asWritten(-0)),
            () -> assertEquals("one thousand one hundred and one", MathUtils.asWritten(1101)),
            () -> assertEquals("one million", MathUtils.asWritten(1000000)),
            () -> assertEquals("one million and one", MathUtils.asWritten(1000001)),
            () -> assertEquals("zero", MathUtils.asWritten(-0)),
            () -> assertEquals("one billion two hundred and thirty-four million five hundred and sixty-seven thousand eight hundred and ninety", MathUtils.asWritten(1234567890))
        );
    }
}
