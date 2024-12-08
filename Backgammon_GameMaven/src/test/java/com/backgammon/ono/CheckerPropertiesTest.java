package com.backgammon.ono;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CheckerPropertiesTest {

    @Test
    void testEnumValues() {
        // Verify that the enum contains exactly two values: X and O
        CheckerProperties[] values = CheckerProperties.values();
        assertEquals(2, values.length, "CheckerProperties should contain exactly 2 values");
    }

    @Test
    void testEnumValueOf() {
        // Verify valueOf returns correct enums for valid inputs
        assertEquals(CheckerProperties.X, CheckerProperties.valueOf("X"));
        assertEquals(CheckerProperties.O, CheckerProperties.valueOf("O"));
    }
}
