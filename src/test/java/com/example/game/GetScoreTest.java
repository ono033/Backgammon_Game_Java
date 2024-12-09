package com.example.game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayInputStream;

class GetScoreTest {

    @Test
    public void testGetScore() {
        // Simulated user input for the player's name
        //String simulatedInput = "TestPlayer\n";
        //System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        // Create a new Player instance
        Player player = new Player();
        player.setPlayerName("ass");
        // Set a score for the player
        player.setScore(50);

        // Verify that getScore retrieves the correct score
        assertEquals(50, player.getScore(), "The score should be 50.");

        // Update the score
        player.setScore(75);

        // Verify that getScore retrieves the updated score
        assertEquals(75, player.getScore(), "The score should be updated to 75.");
    }
}