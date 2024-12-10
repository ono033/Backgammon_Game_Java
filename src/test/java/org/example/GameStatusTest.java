package org.example;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class GameStatusTest {
}

 class CheckGameEndTest {

    @Test
    public void testCheckGameEndGammonWin() {
        System.out.println("Testing Gammon Win...");

        // Simulate user input for player names
        InputStream originalIn = System.in;

        try {
            // Simulate input for Player 1
            // System.setIn(new ByteArrayInputStream("Player 1\n".getBytes()));
            Player player1 = new Player(); // Reads "Player 1"
            player1.setPlayerName("Player1");
            System.out.println("Player 1 created with name: " + player1.getPlayerName());

            // Simulate input for Player 2
            //System.setIn(new ByteArrayInputStream("Player 2\n".getBytes()));
            Player player2 = new Player(); // Reads "Player 2"
            player2.setPlayerName("Player 2");
            System.out.println("Player 2 created with name: " + player2.getPlayerName());

            // Create and configure the board for a Gammon win
            Board board = new Board();
            setBoardForGammonWin(board);

            // Check the game status
            boolean gameEnded = GameStatus.checkGameEnd(board, player1, player2);

            System.out.println(GameStatus.getStatus());

            // Assertions
            assertTrue(gameEnded, "The game should end when Player 1 wins with a Gammon.");
            assertEquals("Gammon", GameStatus.getStatus(), "The game status should be 'Gammon'.");
            assertEquals(player1, GameStatus.getWinner(), "Player 1 should be the winner.");
            assertEquals(2, GameStatus.getPointsAwarded(), "Player 1 should be awarded 2 points for a Gammon.");
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


        /*
        addCheckerstoPip("O", 24, 2);
        addCheckerstoPip("O", 13, 5);
        addCheckerstoPip("O", 8, 3);
        addCheckerstoPip("O", 6, 5);
        */




        // Ensure Player 2 has no checkers borne off and no checkers in Player 1's home quadrant
        for (int pipIndex = 19; pipIndex <= 24; pipIndex++) {
            board.getPip(pipIndex).clear(); // Clear Player 1's home quadrant
        }
        board.getPlayerbar(2).clear(); // Ensure Player 2's bar is empty
    }
}

class GameStatusResetTest {

    @Test
    public void testReset() {
        System.out.println("Testing reset...");

        // Set up a scenario where the game has ended
        GameStatus.checkGameEnd(setupBoardForSingleWin(), setupPlayer("Player 1"), setupPlayer("Player 2"));



        // Reset GameStatus
        GameStatus gameStatus = new GameStatus();
        gameStatus.reset();

        // Print reset values
        System.out.println("After reset:");
        System.out.println("Status: " + GameStatus.getStatus());
        System.out.println("Winner: " + (GameStatus.getWinner() != null ? GameStatus.getWinner().getPlayerName() : "null"));
        System.out.println("Points Awarded: " + GameStatus.getPointsAwarded());

        // Confirm reset values
        assertEquals("Ongoing", GameStatus.getStatus(), "Status should be reset to 'Ongoing'.");
        assertNull(GameStatus.getWinner(), "Winner should be reset to null.");
        assertEquals(0, GameStatus.getPointsAwarded(), "Points should be reset to 0.");
    }

    // Helper method to create a test player
    private Player setupPlayer(String name) {
        Player player = new Player();
        player.setPlayerName(name);
        return player;
    }

    // Helper method to set up a board for a single win
    private Board setupBoardForSingleWin() {
        Board board = new Board();
        board.initialiseVariables();

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

        // Ensure Player 2 has no borne-off checkers
        return board;
    }
}

class GameStatusTest2 {

    @Test
    public void testGetWinner() {
        System.out.println("Testing getWinner...");

        // Simulate user input for player names
        InputStream originalIn = System.in;

        try {
            // Simulate input for Player 1

            Player player1 = new Player(); // Reads "Player 1"
            player1.setPlayerName("Player 1");
            System.out.println("Player 1 created with name: " + player1.getPlayerName());

            // Simulate input for Player 2

            Player player2 = new Player(); // Reads "Player 2"
            player2.setPlayerName("Player 2");

            System.out.println("Player 2 created with name: " + player2.getPlayerName());
            // Create and configure the board for a Gammon win
            Board board = new Board();
            setBoardForGammonWin(board);

            // Check the game status
            boolean gameEnded = GameStatus.checkGameEnd(board, player1, player2);

            System.out.println(GameStatus.getStatus());

            // Assertions
            assertTrue(gameEnded, "The game should end when Player 1 wins with a Gammon.");
            assertEquals("Gammon", GameStatus.getStatus(), "The game status should be 'Gammon'.");
            assertEquals(player1, GameStatus.getWinner(), "Player 1 should be the winner.");
            assertEquals(2, GameStatus.getPointsAwarded(), "Player 1 should be awarded 2 points for a Gammon.");
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

                Player player1 = new Player(); // Reads "Player 1"
                System.out.println("Player 1 created with name: " + player1.getPlayerName());

                // Simulate input for Player 2

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