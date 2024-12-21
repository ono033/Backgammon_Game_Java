package org.example;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        // Initial greeting to the player
        System.out.println("Welcome to Backgammon");

        // Main loop for starting new matches or quitting
        while(true) {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Would you like to start a new match? (y/n)");
            String userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("y")) {
                // If user wants to start a new match, set it up
                System.out.println("-------------------------------------------\n");

                Match match = new Match();

                // After a match finishes, if it's over, we continue back to the prompt
                if (match.isMatchOver()){
                    continue;
                }

            } else if (userInput.equalsIgnoreCase("n")) {
                // User does not want to start a new match, so exit the program
                System.out.println(" Thanks for playing! ");
                break;
            } else {
                // If user input is invalid, prompt them again
                System.out.println("Invalid input");
            }

        }

    }

}
