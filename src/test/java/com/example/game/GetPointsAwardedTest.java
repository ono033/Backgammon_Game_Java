package com.example.game;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class GetPointsAwardedTest {


    @Nested
    class GetPointsAwarded {

        @Test
        public void testGetPointsAwarded() {
            System.out.println("Testing getPointsAwarded...");

            // Simulate user input for player names
            InputStream originalIn = System.in;

            try {
                // Simulate input for Player 1
                System.setIn(new ByteArrayInputStream("Player 1\n".getBytes()));
                Player player1 = new Player(); // Reads "Player 1"
                System.out.println("Player 1 created with name: " + player1.getPlayerName());

                // Simulate input for Player 2
                System.setIn(new ByteArrayInputStream("Player 2\n".getBytes()));
                Player player2 = new Player(); // Reads "Player 2"
                System.out.println("Player 2 created with name: " + player2.getPlayerName());

                // Create and configure the board for a Gammon win
                Board board = new Board();
                setBoardForGammonWin(board);

                // Check the game status
                boolean gameEnded = GameStatus.checkGameEnd(board, player1, player2);

                // Assertions for getPointsAwarded
                assertTrue(gameEnded, "The game should end.");
                assertEquals(2, GameStatus.getPointsAwarded(), "The points awarded should be 2 for a Gammon.");
            } finally {
                // Restore the original System.in
                System.setIn(originalIn);
            }
        }

        // Helper method to configure the board for a Gammon win
        public static void setBoardForGammonWin(Board board) {
            // Ensure the board is initialized properly
            board.initialiseVariables();
            board.setUpBoard();

            // Set Player 1's borne-off checkers to 15
            for (int i = 0; i < 15; i++) {
                board.bearOffChecker(24); // Player 1 bears off checkers
                board.bearOffChecker(13); // Player 1 bears off checkers
                board.bearOffChecker(8); // Player 1 bears off checkers
                board.bearOffChecker(6); // Player 1 bears off checkers
            }

            // Ensure Player 2 has no checkers borne off and no checkers in Player 1's home quadrant
            for (int pipIndex = 19; pipIndex <= 24; pipIndex++) {
                board.getPip(pipIndex).clear(); // Clear Player 1's home quadrant
            }
            board.getPlayerbar(2).clear(); // Ensure Player 2's bar is empty
        }
    }

}