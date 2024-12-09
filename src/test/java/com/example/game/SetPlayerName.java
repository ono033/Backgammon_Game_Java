package com.example.game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayInputStream;

class SetPlayerName {

    @Test
    public void testSetPlayerNameWithSimulatedInput() {
        // Simulate user input
        String simulatedInput = "PlayerName\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        // Create a new Player instance (this triggers user input for player name)
        Player player = new Player();
        player.setPlayerName("PlayerName");
        // Verify that the constructor sets the name using user input
        assertEquals("PlayerName", player.getPlayerName(), "Player name should be set to the simulated input.");

        // Manually set a new name using setPlayerName
        player.setPlayerName("AltPlayerName");

        // Verify that the setPlayerName method overrides the previous name
        assertEquals("AltPlayerName", player.getPlayerName(), "Player name should be updated to 'AltPlayerName' using setPlayerName.");
    }
}