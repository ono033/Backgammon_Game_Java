package com.example.game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayInputStream;
class SetPlayerMatchScoreTest {

    @Test
    public void testSetPlayerMatchscore() {
        // Simulate user input for the player's name
        String simulatedInput = "TestPlayerSetMatchscore\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        // Create a new Player instance
        Player player = new Player();

        // Set match score to a specific value
        int newMatchScore = 10;
        player.setPlayerMatchscore(newMatchScore);

        // Verify that the match score was set correctly
        assertEquals(newMatchScore, player.getPlayerMatchscore(), "The player's match score should be set to the new value.");
    }
}