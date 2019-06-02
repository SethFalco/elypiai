package com.elypia.elypiai.test.common.core;

import com.elypia.elypiai.common.core.Checks;
import com.elypia.elypiai.test.common.core.impl.TestEnum;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ChecksTests {

    @Test
    public void checkUnknown() {
        assertThrows(IllegalArgumentException.class, () -> Checks.requireNonUnknown(TestEnum.UNKNOWN));
    }

    @Test
    public void checkNotUnknown() {
        assertDoesNotThrow(() -> Checks.requireNonUnknown(TestEnum.SOMETHING_ELSE));
    }
}
