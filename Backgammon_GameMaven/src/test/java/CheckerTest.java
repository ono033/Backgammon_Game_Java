package com.backgammon.ono;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals; // Import the Assertions.assertEquals method
import static org.junit.jupiter.api.Assertions.assertNotNull; // Import for assertNotNull

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

//package com.backgammon.ono;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;

//import static org.junit.jupiter.api.Assertions.*;

class CheckerTest {
//Assertions.assertEquals();
    private Checker checkerO_bytype;
    private Checker checkerO_byname;

    private Checker checkerX_bytype;
    private Checker checkerX_byname;


        @BeforeEach
        void setUp() {
            checkerO_bytype = new Checker("O");
            checkerO_byname = new Checker(1);

            checkerX_bytype = new Checker("X");
            checkerX_byname = new Checker(2);
        }

    @Test
    void testConstructor() {
        // Validate checkerO_bytype (constructed with "O")
        assertEquals(CheckerProperties.O, checkerO_bytype.getType(), "checkerO_bytype should have type O");
        assertEquals(1, checkerO_bytype.getPlayerNumber(), "checkerO_bytype should have player number 1");

        // Validate checkerO_byname (constructed with player number 1)
        assertEquals(CheckerProperties.O, checkerO_byname.getType(), "checkerO_byname should have type O");
        assertEquals(1, checkerO_byname.getPlayerNumber(), "checkerO_byname should have player number 1");

        // Validate checkerX_bytype (constructed with "X")
        assertEquals(CheckerProperties.X, checkerX_bytype.getType(), "checkerX_bytype should have type X");
        assertEquals(2, checkerX_bytype.getPlayerNumber(), "checkerX_bytype should have player number 2");

        // Validate checkerX_byname (constructed with player number 2)
        assertEquals(CheckerProperties.X, checkerX_byname.getType(), "checkerX_byname should have type X");
        assertEquals(2, checkerX_byname.getPlayerNumber(), "checkerX_byname should have player number 2");

        // Test invalid type
        Checker invalidChecker = new Checker("Z");
        assertNotNull(invalidChecker, "Invalid checker object should still be created");
        assertEquals(null, invalidChecker.getType(), "Invalid checker type should be null");

        // Test invalid player number
        Checker invalidPlayerChecker = new Checker(3);
        assertNotNull(invalidPlayerChecker, "Checker object should still be created for invalid player number");
        assertEquals(null, invalidPlayerChecker.getType(), "Type should remain null for invalid player number");
    }

    @Test
        void printChecker() {
            // Arrange: Capture System.out output
            ByteArrayOutputStream outContent = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outContent)); // Redirect System.out to capture output

            // Act: Call the printChecker method
            checkerO_bytype.printChecker();  // This should print "Checker type: O"

            // Assert
            assertEquals("Checker type: O" + System.lineSeparator(), outContent.toString(),
                    "The printed output for checkerO_bytype should match the expected value");

            // Clean up: Restore original System.out
            System.setOut(System.out);
        }

        @Test
        void getPlayerNumber() {
            // Assert player numbers
            assertEquals(1, checkerO_bytype.getPlayerNumber(), "checkerO_bytype should have player number 1");
            assertEquals(1, checkerO_byname.getPlayerNumber(), "checkerO_byname should have player number 1");
            assertEquals(2, checkerX_bytype.getPlayerNumber(), "checkerX_bytype should have player number 2");
            assertEquals(2, checkerX_byname.getPlayerNumber(), "checkerX_byname should have player number 2");
        }

        @Test
        void getType() {
            // Assert types
            assertEquals(CheckerProperties.O, checkerO_bytype.getType(), "checkerO_bytype should have type O");
            assertEquals(CheckerProperties.O, checkerO_byname.getType(), "checkerO_byname should have type O");
            assertEquals(CheckerProperties.X, checkerX_bytype.getType(), "checkerX_bytype should have type X");
            assertEquals(CheckerProperties.X, checkerX_byname.getType(), "checkerX_byname should have type X");
        }

        @Test
        void testToString() {
            // Assert toString() values
            assertEquals("O", checkerO_bytype.toString(), "checkerO_bytype toString() should return 'O'");
            assertEquals("O", checkerO_byname.toString(), "checkerO_byname toString() should return 'O'");
            assertEquals("X", checkerX_bytype.toString(), "checkerX_bytype toString() should return 'X'");
            assertEquals("X", checkerX_byname.toString(), "checkerX_byname toString() should return 'X'");
        }

}