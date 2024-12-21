package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Commands {

    // Holds custom dice results if the user sets them
    private static ArrayList<Integer> customDiceResult = new ArrayList<>();


    public static ArrayList<Integer> getCommand() {

        // commandCode will store the result of what command the user entered
        ArrayList<Integer> commandCode = new ArrayList<>();

        // Get user input from the console
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();

        // Check what command the user typed in
        if (userInput.equalsIgnoreCase("quit")) {
            // User wants to quit the game
            System.out.println("Quitting game..");
            commandCode.add(1);
            return commandCode;
        } else if (userInput.equalsIgnoreCase("roll")) {
            // User wants to roll dice
            commandCode.add(4);
            return commandCode;
        } else if (userInput.equalsIgnoreCase("test")) {
            // User wants to run commands from a file
            commandCode = Commands.Test();
            return commandCode;
        } else if (userInput.equalsIgnoreCase("hint")) {
            // User wants to see a list of commands
            commandCode.add(2);
            return commandCode;

        } else if (userInput.equalsIgnoreCase("pip")) {
            // User wants to see pip count
            commandCode.add(3);
            return commandCode;
        } else if (userInput.matches("(?i)^dice \\d+ \\d+$")) {
            // User wants to set custom dice
            int code = customDiceprocessInput(userInput);
            if (code == 6) {
                commandCode.add(code);
                return commandCode;
            }
        } else if (userInput.equalsIgnoreCase("double")) {
            // User wants to propose doubling the stakes
            commandCode.add(7);
            return commandCode;
        } else if (userInput.equalsIgnoreCase("doubleStatus")) {
            // User wants to see who owns the doubling cube
            commandCode.add(9);
            return commandCode;
        } else {
            // User entered an unknown command
            commandCode.add(8);
            return commandCode;
        }
        return commandCode;
    }


    public static ArrayList<Integer> Roll() {

        // Create a random number generator for dice rolls
        Random random = new Random();

        // Roll two six-sided dice
        int die1 = random.nextInt(6) + 1;
        int die2 = random.nextInt(6) + 1;

        ArrayList<Integer> result;

        // If we rolled a double, print a special message and return four identical values
        if (die1 == die2) {
            System.out.println("You rolled:");
            System.out.println("Die 1: " + die1);
            System.out.println("Die 2: " + die2);
            System.out.println("You rolled a double!");
            System.out.println("You can roll " + die1 + "-" + die1 + "-" + die1 + "-" + die1);

            result = new ArrayList<>(Arrays.asList(die1, die1, die1, die1));
        } else {
            // Normal roll, just return the two dice values
            System.out.println("You rolled:");
            System.out.println("Die 1: " + die1);
            System.out.println("Die 2: " + die2);

            result = new ArrayList<>(Arrays.asList(die1, die2));
        }
        return result;
    }

    public static void Hint() {
        // Print a helpful list of all allowed commands
        System.out.println("");
        System.out.println("Here are the list of allowed commands:");
        System.out.println("---------------------------------------");
        System.out.println("'quit': Exit the game");
        System.out.println("'roll': Roll the dice");
        System.out.println("'test': Input a .txt file with a list of commands for the game to execute");
        System.out.println("'pip': Reports the pip count for both players");
        System.out.println("'hint': List all allowed commands");
        System.out.println("'dice <int> <int>': Set the dice roll eg: \"dice 2 4\" ");
        System.out.println("'double': The player who owns the double cube can propose to double the stakes of the game");
        System.out.println("'doubleStatus': Displays the name of the player who owns the doubling cube");
        System.out.println("---------------------------------------");
        System.out.println("(Note, these are not case sensitive!)");
        System.out.println("");
    }

    public static void Pips(Board board) {
        // Count the pip totals for both players
        int xpips = 0;
        int opips = 0;
        int invertXpip = 0;

        // Loop through each point on the board and sum up pip counts
        for (int i = 1; i <= 24; i++) {
            for (int j = 0; j <= 4; j++) {
                Checker checker = board.getChecker(i, j);
                if (checker == null) {
                    continue; // If no checker, move on
                }
                if (checker.type == CheckerProperties.X) {
                    invertXpip = (24 - i) + 1;
                    xpips = xpips + invertXpip;
                } else if (checker.type == CheckerProperties.O) {
                    opips = opips + i;
                }
            }
        }
        System.out.println("Number of pips for Player 1: " + opips);
        System.out.println("Number of pips for Player 2: " + xpips);
    }

    public static boolean Double(Player doublingPlayer, Player receivingPlayer) {
        // Handle the action of doubling the stakes if allowed
        if (doublingPlayer.canDouble && !doublingPlayer.hasRolled) {
            System.out.println(doublingPlayer.getPlayerName() + " has offered a double!");
            System.out.println("The current stakes are: " + GameStatus.getMatchStake());
            System.out.println(receivingPlayer.getPlayerName() + ", would you like to accept? (Yes/No)");

            Scanner scanner = new Scanner(System.in);
            String userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("yes")) {
                // They accepted, so double the stakes
                System.out.println("Stakes doubled!");
                GameStatus.doubleMatchStake();

                System.out.println("Current stakes are: " + GameStatus.getMatchStake());
                System.out.println(receivingPlayer.getPlayerName() + ", you are now the owner of the doubling cube!");
                doublingPlayer.setCanDouble(false);
                receivingPlayer.setCanDouble(true);
            } else if (userInput.equalsIgnoreCase("no")) {
                // They did not accept, so game ends with conceding
                System.out.println("Stakes not doubled.");
                System.out.println(receivingPlayer.getPlayerName() + ", you must now concede the game and pay one stake per point");
                doublingPlayer.setPlayerMatchscore(GameStatus.getMatchStake());
                return true;
            }
        } else {
            // Player is not allowed to double at this time
            System.out.println(doublingPlayer.getPlayerName() + ", you are not allowed to double.");
        }
        return false;
    }

    public static void doubleStatus(Player player1, Player player2) {
        // Show which player currently owns the doubling cube
        if (player1.canDouble) {
            System.out.println(player1.getPlayerName() + " owns the doubling cube!");
        } else {
            System.out.println(player2.getPlayerName() + " owns the doubling cube!");
        }
        System.out.println("The current match stake is " + GameStatus.getMatchStake());
    }

    public static ArrayList<Integer> Test() {
        // Handle reading commands from a file
        System.out.println("Please input the filename with the requested commands in the following format:");
        System.out.println("filename.txt");
        Scanner inputScanner = new Scanner(System.in);
        String filePath = inputScanner.nextLine(); // The file to read from
        ArrayList<Integer> fileCommandCodes = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            // For each line in the file, process it as a command
            while ((line = br.readLine()) != null) {
                line = line.trim();
                fileCommandCodes.add(processFileCommands(line));
                System.out.println("Line read: " + line);
            }
        } catch (IOException e) {
            // If there's an error reading the file, let the user know
            System.err.println("Error reading the file: " + e.getMessage());
            System.err.println("Please enter the test command again");
        }
        return fileCommandCodes;
    }

    public static int processFileCommands(String line) {
        // Determine what command code to return for a line read from the file
        if (line.equalsIgnoreCase("quit")) {
            System.out.println("Quitting game..");
            return 1;
        } else if (line.equalsIgnoreCase("roll")) {
            return 4;
        } else if (line.equalsIgnoreCase("hint")) {
            return 2;
        } else if (line.equalsIgnoreCase("pip")) {
            return 3;
        } else if (line.matches("(?i)^dice \\d+ \\d+$")) {
            // Update custom dice if the command matches
            customDiceprocessInput(line);
            return 6;
        } else if (line.equalsIgnoreCase("double")) {
            return 7;
        } else if (line.equalsIgnoreCase("doubleStatus")) {
            return 9;
        } else {
            return 8;
        }
    }

    public static int customDiceprocessInput(String userInput) {
        // Parse and set custom dice if valid
        Pattern pattern = Pattern.compile("^dice (\\d+) (\\d+)$");
        Matcher matcher = pattern.matcher(userInput.toLowerCase());

        if (matcher.find()) {
            try {
                int dice1 = Integer.parseInt(matcher.group(1));
                int dice2 = Integer.parseInt(matcher.group(2));

                // Check if dice values are between 1 and 6
                if (dice1 >= 1 && dice1 <= 6 && dice2 >= 1 && dice2 <= 6) {
                    setCustomDice(dice1, dice2);
                    return 6;
                } else {
                    System.out.println("Custom Dice values must be between 1 and 6. Enter valid command:");
                    return -1;
                }

            } catch (NumberFormatException e) {
                // If numbers are not valid, ask user to try again
                System.out.println("Error: Invalid number format. Please enter valid integers.  \nEnter valid command:");
            }
        }
        return -1;
    }

    public static void setCustomDice(int dice1, int dice2) {
        // Set the custom dice values
        customDiceResult.clear();
        customDiceResult.add(dice1);
        customDiceResult.add(dice2);
        System.out.println("Custom dice set to: " + customDiceResult);
    }

    public static ArrayList<Integer> getCustomDiceResult() {
        // Return the currently set custom dice values
        return customDiceResult;
    }
}
