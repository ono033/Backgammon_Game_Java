package jUnitExample;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CheckerTest {
//Assertions.assertEquals();
    private Checker checker_bytype;
    private Checker checker_byname;

    @BeforeEach
    void setUp() {
        checker_bytype = new Checker("O");
        checker_byname = new Checker(1);
    }
/*
    @Test
    @DisplayName("Checks that an object has been created.")
    void testChecker() {
        assertNotNull(checker_byname);
    }
*/
    @Test
    void printChecker() {
    }

    @Test
    void getPlayerNumber() {
        assertEquals(1,checker_bytype.getPlayerNumber());

    }

    @Test
    void getType() {
    }

    @Test
    void testToString() {
    }
}