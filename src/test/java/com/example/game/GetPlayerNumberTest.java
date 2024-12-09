package com.example.game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayInputStream;

class GetPlayerNumberTest {

    @Test
    public void testGetPlayerNumber() {
        // Simulate user input for the player's name
        //String simulatedInput = "TestPlayerNumber\n";
        //System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        // Create a new Player instance
        Player player = new Player();

        // Verify that the player number is correctly assigned
        assertEquals(1, player.getPlayerNumber(), "The first player's number should be 1.");

        // Simulate input for a second player
        //String simulatedInput2 = "TestPlayerNumber2\n";
        //System.setIn(new ByteArrayInputStream(simulatedInput2.getBytes()));

        // Create a second Player instance
        Player player2 = new Player();

        // Verify that the second player's number is correctly assigned
        assertEquals(2, player2.getPlayerNumber(), "The second player's number should be 2.");
    }
}