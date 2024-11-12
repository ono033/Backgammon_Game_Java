import java.util.Random;
import java.util.Scanner;

public class Commands {

    public static int getCommand() {
        while(true) {

            Scanner scanner = new Scanner(System.in);
            String userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("quit")) {
                System.out.println("Quitting game..");
                return 1;
            } else if (userInput.equalsIgnoreCase("roll")) {
                Commands.Roll();
                return 2;
            }
         else if (userInput.equalsIgnoreCase("hint")) {
            Commands.Hint();
        }
            else {
                System.out.println("Invalid input. Type 'roll' to roll the dice!");
            }
        }
    }
    public static void Roll() {

        Random random = new Random();
        // Roll two dice
        int die1 = random.nextInt(6) + 1;
        int die2 = random.nextInt(6) + 1;

        // Display the result of each dice
        if (die1==die2){
            System.out.println("You rolled:");
            System.out.println("Die 1: " + die1);
            System.out.println("Die 2: " + die2);
            System.out.println("You rolled a double!");
            System.out.println("You can roll " + die1 +"-"+ die1 +"-"+ die1 +"-"+ die1);
        }
        else{
            System.out.println("You rolled:");
            System.out.println("Die 1: " + die1);
            System.out.println("Die 2: " + die2);
        }}

    public static void Hint() {
            System.out.println("");
            System.out.println("Here are the list of allowed commands:");
            System.out.println("---------------------------------------");
            System.out.println("'quit': Exit the game");
            System.out.println("'roll': Roll the dice");
            System.out.println("'pip': Reports the pip count for both players");
            System.out.println("'hint': List all allowed commands");
            System.out.println("---------------------------------------");
            System.out.println("(Note, these are not case sensitive!)");
            System.out.println("");

        }


}
