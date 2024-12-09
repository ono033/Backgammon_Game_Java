package com.example.game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayInputStream;
class EnterPlayerNameTest {

    @Test
    public void testEnterPlayerName() {
        // Simulate user input for the player's name

        // Create a new Player instance
        Player player = new Player();
        player.setPlayerName("NewTestPlayer");
        // Verify the name was set correctly during initialization
        assertEquals("NewTestPlayer", player.getPlayerName(), "Player name should be 'NewTestPlayer'.");
    }
}