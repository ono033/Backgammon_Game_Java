import java.util.ArrayList;

//Code to check Game status
public class GameStatus {

    private static String status; // Holds the current game status (e.g., "Backgammon", "Gammon", etc.)
    private static Player winner; // Holds the current game's winner
    private static int points; // Points awarded for the win
    public static int matchStake=1;

    // Method to check the game status and determine the winner
    public static boolean checkGameEnd(Board board, Player player1, Player player2) {
        int player1win = board.getplayerOff(1); // Checkers removed by Player 1
        int player2win = board.getplayerOff(2); // Checkers removed by Player 2

        // Reset the status for a new check
        status = "Ongoing";
        winner = null;
        points = 0;
        if (player1win == 15) {
            if (player2win == 0 && !board.getPlayerbar(2).isEmpty() && hasCheckersInOpponentHome(board, 1)) {
                System.out.println("Backgammon condition met for Player 1.");
                player1.setScore(player1.getScore() + 3);
                status = "Backgammon";
                winner = player1;
                points = 3;
            } else if (player2win == 0) {
                System.out.println("Gammon condition met for Player 1.");
                player1.setScore(player1.getScore() + 2);
                status = "Gammon";
                winner = player1;
                points = 2;
            } else {
                System.out.println("Single Game Win for Player 1.");
                player1.setScore(player1.getScore() + 1);
                status = "Single";
                winner = player1;
                points = 1;
            }
        }
        if (player2win == 15) {
            if (player1win == 0 && !board.getPlayerbar(2).isEmpty() && hasCheckersInOpponentHome(board, 2)) {
                System.out.println("Backgammon condition met for Player 2.");
                player2.setScore(player2.getScore() + 3);
               status = "Backgammon";
                winner = player2;
                points = 3;
            } else if (player1win == 0) {
                System.out.println("Gammon condition met for Player 2.");
                player2.setScore(player2.getScore() + 2);
                status = "Gammon";
                winner = player2;
                points = 2;
            } else {
                System.out.println("Single Game Win for Player 2.");
                player2.setScore(player2.getScore() + 1);
                status = "Single";
                winner = player2;
               points = 1;
            }
        }



        // Print the winning statement if a winner is detected
        if (winner != null) {
            System.out.println("Game over!");
            System.out.println(winner.getPlayerName() + " wins with a " + status + "! (" + points + " points)");

            return true; // Game ended
        }

        return false; // Game is ongoing
    }

    // Method to check if a player has checkers in their opponent's home quadrant
    private static boolean hasCheckersInOpponentHome(Board board, int playerNumber) {
        // Player 1's home quadrant is pips 19 to 24
        // Player 2's home quadrant is pips 1 to 6

        int startPip = (playerNumber == 1) ? 19 : 1;
        int endPip = (playerNumber == 1) ? 24 : 6;

        // Iterate through all the pips in the opponent's home quadrant
        for (int pipIndex = startPip; pipIndex <= endPip; pipIndex++) {
            // Get the checkers on the current pip

            ArrayList<Checker> pip = board.getPip(pipIndex);
            System.out.println("Checking pip " + pipIndex + " for player " + playerNumber);
            if (pip != null && !pip.isEmpty()) {
                for (Checker checker : pip) {

                    // Check if the checker belongs to the opponent
                    System.out.println("Checker at pip " + pipIndex + ": " + checker);
                    if (!board.isPlayerChecker(playerNumber, checker)) {
                        System.out.println("Opponent checker detected in home quadrant at pip " + pipIndex);
                        return true;
                    }
                }
            }
        }
        return false;
    }




    // Getters and Setters
    public static String getStatus() {
        return status;
    }

    public static Player getWinner() {
        return winner;
    }

    public static int doubleMatchStake(){
        matchStake=2*matchStake;
        return matchStake;
    }
    public static void resetMatchStake(){
        matchStake=0;
    }
    public static int getMatchStake(){return matchStake;}
    public static int getPointsAwarded() {
        return matchStake*points;
    }

    public void reset() {
        status = "Ongoing";
        winner = null;
        points = 0;
    }
}
