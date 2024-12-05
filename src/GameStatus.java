public class GameStatus {

    // Determine the game outcome and update the winner's score
    public static String checkGameEnd(Board board, Player player1, Player player2) {
        int player1win = board.getplayerOff(1); // Checkers removed by Player 1
        int player2win = board.getplayerOff(2); // Checkers removed by Player 2
        String results = null;

        // Check if Player 1 has removed all their checkers
        if (player1win == 12) {
            if (player2win == 0 && !board.getPlayerbar(2).isEmpty()) {
                // Backgammon: Player 2 has no checkers removed and has checkers on the bar
                player1.setScore(player1.getScore() + 3);
                return player1.getPlayerName() + " wins with a Backgammon! (3 points)";
            } else if (player2win == 0) {
                // Gammon: Player 2 has no checkers removed
                player1.setScore(player1.getScore() + 2);
                return player1.getPlayerName() + " wins with a Gammon! (2 points)";
            } else {
                // Single Game Win: Player 2 has at least one checker removed
                player1.setScore(player1.getScore() + 1);
                return player1.getPlayerName() + " wins with a Single! (1 point)";
            }
        }

        // Check if Player 2 has removed all their checkers
        if (player2win == 12) {
            if (player1win == 0 && !board.getPlayerbar(1).isEmpty()) {
                // Backgammon: Player 1 has no checkers removed and has checkers on the bar
                player2.setScore(player2.getScore() + 3);
                return player2.getPlayerName() + " wins with a Backgammon! (3 points)";
            } else if (player1win == 0) {
                // Gammon: Player 1 has no checkers removed
                player2.setScore(player2.getScore() + 2);
                return player2.getPlayerName() + " wins with a Gammon! (2 points)";
            } else {
                // Single Game Win: Player 1 has at least one checker removed
                player2.setScore(player2.getScore() + 1);
                return player2.getPlayerName() + " wins with a Single Game Win! (1 point)";
            }
        }

        //If a winner is determined, reset the board and start a new game
        if (results!= null) {
            System.out.println(results);
            System.out.println("Starting a new game...");
            board.initialiseVariables(); // Reset the board
            board.setUpBoard();          // Set up the new game board
        } else {
            // Game continues if the game isn't over
            results = null;
        }

        return results;
    }
}
