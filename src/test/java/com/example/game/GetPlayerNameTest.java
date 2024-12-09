package com.example.game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayInputStream;

class GetPlayerNameTest {

    @Test
    public void testGetPlayerName() {
        // Simulated user input for the player's name
        String simulatedInput = "TestPlayer\n";
        //System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        // Create a new Player instance
        Player player = new Player();
        player.setPlayerName("TestPlayer");
        // Assert that the player's name matches the simulated input
        assertEquals("TestPlayer", player.getPlayerName(), "Player name should match the simulated input.");
    }
}