package com.example.game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
class DisplayPlayerInfoTest {

    @Test
    public void testDisplayPlayerInfo() {
        // Simulate user input for the player's name
        String simulatedInput = "InfoTestPlayer\n";
       // System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        // System.out to capture the output of displayPlayerInfo
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Create a new Player instance
        Player player = new Player();
        player.setPlayerName("InfoTestPlayer");
        // Set the player's score
        player.setScore(10);

        // Call displayPlayerInfo to print the player's information
        player.displayPlayerInfo();

        // Capture and verify the printed output
        String expectedOutput = "Player: InfoTestPlayer, Score: 10";
        assertEquals(expectedOutput, outputStream.toString().trim(), "Output of displayPlayerInfo should match the expected string.");
    }
}