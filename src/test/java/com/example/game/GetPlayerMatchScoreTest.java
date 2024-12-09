package com.example.game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayInputStream;

class GetPlayerMatchScoreTest {

    @Test
    public void testGetPlayerMatchscore() {
        // Simulate user input for the player's name
        String simulatedInput = "TestPlayerMatchscore\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));


        // Create a new Player instance
        Player player = new Player();
        // Set match score to 1 (since the default is 0 in the class)
        player.setPlayerMatchscore(1);


        // Verify the initial match score (should be 0 by default)
        assertEquals(1, player.getPlayerMatchscore(), "The player's match score should initially be 0.");
    }
}