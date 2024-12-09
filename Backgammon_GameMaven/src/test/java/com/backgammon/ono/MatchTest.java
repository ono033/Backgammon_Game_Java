package com.backgammon.ono;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
class MatchTest {


    private Match match;
    private Player player1;
    private Player player2;

    @BeforeEach
    void setUp() {

            // Reset player count for a fresh setup
            Player.resetPlayercount();

            // Simulate sequential input for both players
            String simulatedInput = "Player1\n1\n2\n2\n1\n1\n1\n1";
            InputStream originalIn = System.in; // Save the original System.in
            ByteArrayInputStream simulatedInputStream = new ByteArrayInputStream(simulatedInput.getBytes());
            System.setIn(simulatedInputStream);

            // Create match with players
            match = new Match(); // Match creation internally initializes players and calls their input methods

            // Restore the original System.in after setup
            System.setIn(originalIn);


    }

    @Test
    void testConstructorInitialization() {
        assertNotNull(match, "Match object should not be null");
        assertNotNull(player1, "Player 1 should be initialized");
        //assertNotNull(player2, "Player 2 should be initialized");
    }

    @Test
    void announceMatchwinner() {


    }

    @Test
    void setMatchLength() {
        // Simulate setting the match length
        String simulatedInput = "3\n"; // Simulates entering "3" as match length
        InputStream originalIn = System.in;
        ByteArrayInputStream simulatedInputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(simulatedInputStream); // Set the simulated input stream before the method call

        match.setMatchLength();

        // Validate the match length using getMatchLength
        assertEquals(3, match.getMatchLength(), "Match length should be set to 3");

        // Restore original input stream
        System.setIn(originalIn);
    }

    @Test
    void printMatchScore() {

    }

    @Test
    void updateMatchOver() {
    }

    @Test
    void isMatchOver() {
    }

    @Test
    void main() {
    }
}