package org.example;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class testdicecommand {




    public testdicecommand() {

    }


    public static ArrayList<Integer> getCommand() {

        ArrayList<Integer> commandCode = new ArrayList<>();

        while (true) {


            System.out.println("enter command- (dice int int or hint ) " );
            Scanner scanner = new Scanner(System.in);
            String userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("hint")) { // make else if lol in real
                commandCode.add(2);
                System.out.println("Processed Command Code: " + commandCode);

                return commandCode;

            }
            //added by ono
            else if  (userInput.matches("(?i)^dice \\d+ \\d+$")) {


                Pattern pattern = Pattern.compile("^dice (\\d+) (\\d+)$");
                Matcher matcher = pattern.matcher(userInput.toLowerCase());

                if (matcher.find()) {
                    try {
                        int dice1 = Integer.parseInt(matcher.group(1)); // First number
                        int dice2 = Integer.parseInt(matcher.group(2)); // Second number


                        // Validate the dice values ono change limit
                        if (dice1 >= 1 && dice1 <= 6 && dice2 >= 1 && dice2 <=6) {
                            // Add custom processing for the dice command here
                            commandCode.add(6); // right command
                            //. commandCode.add(dice1); // Add the first dice value
                            // commandCode .add(dice2); // Add the second dice value
                            System.out.println("dice 1 :" + dice1 + "dice 2: " + dice2);

                            setCustomDice(dice1,dice2);

                            return commandCode;


                        } else {
                            System.out.println("Custom Dice values must be between 1 and 6.");
                        }

                        System.out.println("Processed Command Code: " + commandCode);
                        return commandCode;


                    }

                    catch (NumberFormatException e) {
                        System.out.println("Error: Invalid number format. Please enter valid integers.");
                    }
                }
            }

            else {
                System.out.println("Invalid command. Please try again.");
            }

        }
    }

    public static void setCustomDice(int dice1, int dice2) {

    }

    public static void main(String[] args) {
        Board board = new Board(); // Dummy board instance
        while (true) {
            ArrayList<Integer> result = getCommand();
        }
        // Output the processed command for debugging
       // System.out.println("Processed Command Code: " + result);
    }
}
