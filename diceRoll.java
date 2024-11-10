import java.util.Random;
import java.util.Scanner;

public class diceRoll {
    public static void main(String[] args) {
        // Create a Scanner object for user input
        Scanner scanner = new Scanner(System.in);
        // Create a Random object for generating random numbers
        Random random = new Random();

        System.out.println("Time to roll the dice!");

        // Check if the user typed 'roll'
        while(true) {
            String userInput = scanner.nextLine();
            if (userInput.equalsIgnoreCase("roll")) {
                // Roll two dice
                int die1 = random.nextInt(6) + 1;
                int die2 = random.nextInt(6) + 1;

                // Display the result of each dice
                System.out.println("You rolled:");
                System.out.println("Die 1: " + die1);
                System.out.println("Die 2: " + die2);
                break;
            }
            else if (userInput.equalsIgnoreCase("quit")) {
                break;
            }
            else {
                System.out.println("Invalid input. Type 'roll' to roll the dice!");
            }}


        // Close the scanner to prevent resource leaks
        scanner.close();
    }
}
