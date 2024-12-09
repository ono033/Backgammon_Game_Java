package com.example.game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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