package com.example.game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayInputStream;

class SetScoreTest {

    @Test
    public void testSetScore() {
        // Simulate user input for the player's name
        String simulatedInput = "TestPlayer\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        // Create a new Player instance
        Player player = new Player();
        player.setPlayerName("TestPlayer");
        // Verify the name was set correctly during initialization
        assertEquals("TestPlayer", player.getPlayerName(), "Player name should be 'TestPlayer'.");

        // Set the player's score
        player.setScore(10);

        // Verify that the score is set correctly
        assertEquals(10, player.getScore(), "The score should be 10.");

        // Update the player's score
        player.setScore(20);

        // Verify the updated score
        assertEquals(20, player.getScore(), "The score should be updated to 20.");
    }
}