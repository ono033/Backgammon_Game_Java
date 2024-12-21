package org.example;

import java.util.Random;
//
public class DetermineFirstPlayer {

    public static void determineFirstPlayer(Player firstPlayer, Player secondPlayer) {
        // Create a random number generator for dice rolls
        Random random = new Random();

        System.out.println("-------------------------------------------\n");
        System.out.println("Rolling dice to determine which player goes first...");

        // Keep rolling until there's no tie
        while(true){
            // Each player rolls one die
            int roll1 = random.nextInt(6) + 1;
            int roll2 = random.nextInt(6) + 1;

            // Show the results of the roll
            System.out.println(firstPlayer.getPlayerName() + " rolled: " + roll1);
            System.out.println(secondPlayer.getPlayerName() + " rolled: " + roll2);

            // Check who rolled higher
            if (roll1 > roll2) {
                // If firstPlayer wins the roll, they become Player 1
                System.out.println(firstPlayer.getPlayerName() + " goes first! You are Player 1. Your checker type is \"O\".");
                firstPlayer.playerNumber=1;
                secondPlayer.playerNumber=2;
                break;
            } else if (roll2 > roll1) {
                // If secondPlayer wins the roll, they become Player 1
                System.out.println(secondPlayer.getPlayerName() + " goes first! You are Player 1. You are O");
                firstPlayer.playerNumber=2;
                secondPlayer.playerNumber=1;
                break;
            } else {
                // If there's a tie, roll again
                System.out.println("It's a tie! Rolling again...");
            }
            System.out.println("-------------------------------------------\n");
        }
    }

}
