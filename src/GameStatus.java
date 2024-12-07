import java.util.ArrayList;


public class GameStatus {

    private String status; // Holds the current game status (e.g., "Backgammon", "Gammon", etc.)
    private Player winner; // Holds the current game's winner
    private int points; // Points awarded for the win

    public GameStatus() {
        this.status = "Ongoing";
        this.winner = null;
        this.points = 0;
    }

    // Method to check the game status and determine the winner
    public void checkGameEnd(Board board, Player player1, Player player2) {
        int player1win = board.getplayerOff(1); // Checkers removed by Player 1
        int player2win = board.getplayerOff(2); // Checkers removed by Player 2

        // Reset the status for a new check
        this.status = "Ongoing";
        this.winner = null;
        this.points = 0;

        if (player1win == 12) {
            if (player2win == 0 && !board.getPlayerbar(2).isEmpty() && hasCheckersInOpponentHome(board, 1)) {
                System.out.println("Backgammon condition met for Player 1.");
                player1.setScore(player1.getScore() + 3);
                this.status = "Backgammon";
                this.winner = player1;
                this.points = 3;
            } else if (player2win == 0) {
                System.out.println("Gammon condition met for Player 1.");
                player1.setScore(player1.getScore() + 2);
                this.status = "Gammon";
                this.winner = player1;
                this.points = 2;
            } else {
                System.out.println("Single Game Win for Player 1.");
                player1.setScore(player1.getScore() + 1);
                this.status = "Single";
                this.winner = player1;
                this.points = 1;
            }
        }



        if (this.winner != null) {
            System.out.println(this.winner.getPlayerName() + " wins with a " + this.status + "! (" + this.points + " points)");
        }
    }

    // Method to check if a player has checkers in their opponent's home quadrant
    private boolean hasCheckersInOpponentHome(Board board, int playerNumber) {
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
    public String getStatus() {
        return status;
    }

    public Player getWinner() {
        return winner;
    }

    public int getPointsAwarded() {
        return points;
    }

    public void reset() {
        this.status = "Ongoing";
        this.winner = null;
        this.points = 0;
    }
}
