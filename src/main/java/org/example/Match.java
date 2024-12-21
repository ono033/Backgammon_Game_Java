package org.example;
import java.util.Scanner;



/**
 * The {@code Match} class represents a series of games between two players.
 * A match consists of multiple games until one player's score reaches the
 * specified match length. This class manages the match lifecycle, including
 * setting up players, tracking scores, and determining the match winner.
 */

public class Match{
    // Play multiple games in a match

    /**
     * Indicates if the match is over.
     */
    boolean matchOver = false;

    /**
     * Indicates if the current game is over.
     */
    boolean gameOver = false;

    private Player[] players; // Array to hold players

    private int matchLength = 0;

    /**
     * Constructs a new {@code Match} object and initializes the players,
     * determines the first player, and starts the game loop.
     */
    public Match(){

        Player.resetPlayercount();
        players = new Player[2];

        players[0] = new Player(); // Player One
        players[0].enterPlayerName();
        players[1] = new Player();   // Player Two
        players[1].enterPlayerName();

        DetermineFirstPlayer.determineFirstPlayer(players[0],players[1]);

        setMatchLength();


        while(!matchOver){


            printMatchScore();

            Game game = new Game(players[0], players[1]);
            System.out.println("Game over .. ");
            if (game.quit){
                matchOver = true;
            }



            updateMatchOver(); // updates matchover
        }


        }






    /**
     * Announces the winner of the match.
     *
     * @param winningPlayer the player who has won the match
     */
    public void announceMatchwinner(Player winningPlayer){

        System.out.println("-------------------MATCH OVER--------------\n");

    System.out.println("Congratulations, the overall winner is ......" + winningPlayer.getPlayerName() + "\n With a score of " + winningPlayer.getScore()+ "!" );
        System.out.println("-------------------------------------------\n");

}
    /**
     * Sets the length of the match by prompting the user to enter a positive integer.
     */
    public void setMatchLength() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("-------------------------------------------\n");
        while (true) {
            try {
                System.out.println("Enter Match Length (positive integer): ");
                matchLength = scanner.nextInt();

                if (matchLength > 0) {
                    break; // Exit loop if valid input
                } else {
                    System.out.println("Match length must be over 0. Try again.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a positive integer.");
                scanner.next(); // Clear invalid input
            }
        }

        System.out.println("Match length set to: " + matchLength);

    }


    /**
     * Prints the current match scores for both players.
     */
    public void printMatchScore() {
        System.out.println("\n--- Current Match Scores ---");
        for (Player player : players) {
            System.out.println(player.getPlayerName() + ": " + player.getPlayerMatchscore() + " points");
        }
        System.out.println("-------------------------------------------\n");
    }

    /**
     * Updates the {@code matchOver} field based on whether any player's score
     * has reached or exceeded the match length.
     */
    public void updateMatchOver() {
        System.out.println("--- Overall Scores ---\n");

        for(Player player:players){
            player.displayPlayerInfo();
        }
    for(Player player:players){
            if (player.getScore() >=matchLength){
                Player winningPlayer = player;
                matchOver = true;
                announceMatchwinner(player);

            }
        }
    }

    /**
     * Checks if the match is over.
     *
     * @return {@code true} if the match is over, {@code false} otherwise
     */
    public boolean isMatchOver(){
    return matchOver;
    }


 


}




