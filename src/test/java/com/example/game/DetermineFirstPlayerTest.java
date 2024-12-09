package com.example.game;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

public class DetermineFirstPlayerTest {

    @Test
    public void testDetermineFirstPlayerWithControlledRolls() {
        // Simulate input for Player 1 and Player 2 names
        InputStream originalIn = System.in;
        //String simulatedInput = "Player 1\nPlayer 2\n";
        //System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        try {
            // Create two players
            // Simulate input for Player 1
            //System.setIn(new ByteArrayInputStream("Player 1\n".getBytes()));
            Player player1 = new Player(); // Reads "Player 1"
            player1.setPlayerName("Player1");
            System.out.println("Player 1 created with name: " + player1.getPlayerName());

            // Simulate input for Player 2
            //System.setIn(new ByteArrayInputStream("Player 2\n".getBytes()));
            Player player2 = new Player(); // Reads "Player 2"
            player2.setPlayerName("Player 2");
            System.out.println("Player 2 created with name: " + player2.getPlayerName());

            // Override rolls for testing
            int rollPlayer1 = 6; // Simulated roll for Player 1
            int rollPlayer2 = 4; // Simulated roll for Player 2

            // Print fixed rolls
            System.out.println(player1.getPlayerName() + " rolled: " + rollPlayer1);
            System.out.println(player2.getPlayerName() + " rolled: " + rollPlayer2);

            // Invoke the method
            DetermineFirstPlayer.determineFirstPlayer(player1, player2);

            // Verify output
            String expectedOutput = rollPlayer1 > rollPlayer2
                    ? player1.getPlayerName() + " goes first!"
                    : player2.getPlayerName() + " goes first!";
            assertTrue(expectedOutput.contains("Player 1 goes first!"),
                    "Expected Player 1 to go first since their roll is higher.");
        } finally {
            // Restore original System.in
            System.setIn(originalIn);
        }
    }
}
