package com.example.game;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class ResetPlayerCountTest {

    @Test
    //Tests the ResetPlayerCount
    public void testResetPlayercount() throws Exception {
        System.out.println("Starting testResetPlayercount...");

        // Set the playerCount to simulate existing players
        Field playerCountField = Player.class.getDeclaredField("playerCount");
        playerCountField.setAccessible(true);
        playerCountField.setInt(null, 3); // Simulate 3 players

        // Verify the playerCount is 3 before reset
        assertEquals(3, playerCountField.getInt(null), "Player count should be 3 before reset.");

        // Call the resetPlayercount method
        Player.resetPlayercount();
        System.out.println("Player count reset!");

        // Verify the playerCount is reset to 0
        assertEquals(0, playerCountField.getInt(null), "Player count should be reset to 0 after reset.");
    }
}