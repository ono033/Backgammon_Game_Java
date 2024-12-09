package com.example.game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayInputStream;

class AddPointsToPlayerMatchScore {

    @Test
    public void testAddPointsToPlayerMatchscore() {
        // Simulate user input for the player's name
        String simulatedInput = "TestPlayerAddPoints\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        // Create a new Player instance
        Player player = new Player();

        // Set an initial match score
        int initialMatchScore = 20;
        player.setPlayerMatchscore(initialMatchScore);

        // Add points to the match score
        int pointsToAdd = 5;
        player.addPointsToPlayerMatchscore(pointsToAdd);

        // Verify that the match score was updated correctly
        int expectedMatchScore = initialMatchScore + pointsToAdd;
        assertEquals(expectedMatchScore, player.getPlayerMatchscore(), "The player's match score should be updated with the added points.");
    }
}