package com.backgammon.ono;

import java.util.Scanner;

public class Match{
    // Play multiple games in a match

    boolean matchOver = false;
    boolean gameOver = false;

    private Player[] players; // Array to hold players

    private int matchLength = 0;


    public Match(){

        Player.resetPlayercount();
        players = new Player[2];

        players[0] = new Player(); // Player One
        players[1] = new Player();   // Player Two

        setMatchLength();


        while(!matchOver){


            printMatchScore();

             //while(true) { //- if the game doesn't end inside game or game status?
            GameInterface.Game game = new GameInterface.Game(players[0], players[1]);
            System.out.println("Game over .. "); // remove ono is already printed by someone else


            //  if(!game.isgameOver){ // need to make this function or similar

                //update match score ? (only if its not done in somewhere else)
              //  System.out.println("Game over .. "); // remove ono is already printed by someone else
              //  break;

          //  }
            updateMatchOver(); // updates matchover
        }


        }






    public void announceMatchwinner(Player winningPlayer){

    System.out.println("Congratulations, the winner is ......" + winningPlayer.getPlayerName() + "\n with a score of " + winningPlayer.getPlayerMatchscore()  );


}

    public void setMatchLength() {
        Scanner scanner = new Scanner(System.in);

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



    public void printMatchScore() {
        System.out.println("\n--- Current Match Scores ---");
        for (Player player : players) {
            System.out.println(player.getPlayerName() + ": " + player.getPlayerMatchscore() + " points");
        }
        System.out.println("-----------------------------");
    }

    public void updateMatchOver() {
        
    for(Player player:players){
            if (player.getPlayerMatchscore() >=matchLength){
                Player winningPlayer = player;
                matchOver = true;
                announceMatchwinner(player);

            }
        }
    }

    public boolean isMatchOver(){
    return matchOver;
    }

    public static void main(String[] args) {        //testing
    Match match = new Match();
    //printMatchScore();  ono

    }


}


/* This is for testing the Match constructor without actually playing the game
// to use replace constructor with this
 //lets you add match points to players to test match class

    public Match(){

        Player.resetPlayercount();
        players = new Player[2];

        players[0] = new Player(); // Player One
        players[1] = new Player();   // Player Two

        setMatchLength();


        while(!matchOver){


            printMatchScore();

            for(Player player: players) {
                System.out.println("add points to player: " + player.getPlayerNumber());

                Scanner scanner = new Scanner(System.in);

                int n = scanner.nextInt();

                player.addPointsToPlayerMatchscore(n);


            }
            // ono add back in after match test Game game = new Game(players[0], players[1]);
            // if game over then update match score depending on type of win

// make sure that when game is over it gets to this point , if not added by seun or sahar add check for gameover
            updateMatchOver(); // updates matchover
        }

        // when match over - start again?




    }

*/

