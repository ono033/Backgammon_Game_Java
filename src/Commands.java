import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Commands {



 public static ArrayList<Integer> getCommand(Board gameBoard) {


     ArrayList<Integer> commandCode = new ArrayList<>();


    Scanner scanner = new Scanner(System.in);
    String userInput = scanner.nextLine();



    if (userInput.equalsIgnoreCase("quit")) {
        System.out.println("Quitting game..");
        commandCode.add(1);
        return commandCode;
    } else if (userInput.equalsIgnoreCase("roll")) {
        commandCode.add(4);
        return commandCode;
    } else if (userInput.equalsIgnoreCase("test")) {
        commandCode = Commands.Test();
        return commandCode;
    } else if (userInput.equalsIgnoreCase("hint")) {
        commandCode.add(2);
        return commandCode;

    } else if (userInput.equalsIgnoreCase("pip")) {
        commandCode.add(3);
        return commandCode;
    } else if (userInput.equalsIgnoreCase("double")) {
         commandCode.add(7);
         return commandCode;
     }
     else if (userInput.equalsIgnoreCase("doubleStatus")) {
         commandCode.add(9);
         return commandCode;
     }
     else {
         commandCode.add(8);
        return commandCode;
    }

 }


    public static ArrayList<Integer> Roll() {


        Random random = new Random();
        // Roll two dice
        int die1 = random.nextInt(6) + 1;
        int die2 = random.nextInt(6) + 1;

        ArrayList<Integer> result;


        // Display the result of each dice
        if (die1==die2){
            System.out.println("You rolled:");
            System.out.println("Die 1: " + die1);
            System.out.println("Die 2: " + die2);
            System.out.println("You rolled a double!");
            System.out.println("You can roll " + die1 +"-"+ die1 +"-"+ die1 +"-"+ die1);

            result = new ArrayList<>(Arrays.asList(die1, die1, die1, die1));
            //result = new int[] {die1, 2 * die1, 3 * die1, 4 * die1};

        }
        else{
            System.out.println("You rolled:");
            System.out.println("Die 1: " + die1);
            System.out.println("Die 2: " + die2);

            result = new ArrayList<>(Arrays.asList(die1, die2));
            //result = new int[] {die1, die2, die1 + die2};

        }
    return result;
    }

    public static void Hint() {
            System.out.println("");
            System.out.println("Here are the list of allowed commands:");
            System.out.println("---------------------------------------");
            System.out.println("'quit': Exit the game");
            System.out.println("'roll': Roll the dice");
            System.out.println("'test': Input a .txt file with a list of commands for the game to execute");
            System.out.println("'pip': Reports the pip count for both players");
            System.out.println("'hint': List all allowed commands");
            System.out.println("'double': The player who owns the double cube can propose to double the stakes of the game");
            System.out.println("'doubleStatus': Displays the name of the player who owns the doubling cube");
            System.out.println("---------------------------------------");
            System.out.println("(Note, these are not case sensitive!)");
            System.out.println("");

        }

        public static void Pips(Board board) {
        int xpips=0;
        int opips=0;
        int invertXpip=0;
        for(int i=1; i<=24;i++){
            for(int j=0;j<=5;j++){
                Checker checker = board.getChecker(i, j);
                if (checker == null) {
                    continue; // Skip to the next iteration if there's no checker or type is null
                }
                if(checker.type==CheckerProperties.X){
                    invertXpip=(24-i)+1;
                    xpips=xpips+invertXpip;
                }
                else if(checker.type==CheckerProperties.O){
                    opips=opips+i;
                }
            }
            }
        System.out.println("Number of pips for Player 1: "+ opips);
        System.out.println("Number of pips for Player 2: "+ xpips);
        }

        public static void Double(Player doublingPlayer, Player receivingPlayer){
        if (doublingPlayer.canDouble && !doublingPlayer.hasRolled ){
            System.out.println(doublingPlayer.getPlayerName()+ " has offered a double!");
            System.out.println("The current stakes are:");
            System.out.println(receivingPlayer.getPlayerName()+ ", would you like to accept? (Yes/No)");

            Scanner scanner = new Scanner(System.in);
            String userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("yes")) {
                System.out.println("Stakes doubled!");
                System.out.println("Current stakes are:");
                System.out.println(receivingPlayer.getPlayerName()+ ", you are now the owner of the doubling cube!");
                doublingPlayer.setCanDouble(false);
                receivingPlayer.setCanDouble(true);
            }
            else if (userInput.equalsIgnoreCase("no")) {
                System.out.println("Stakes not doubled.");
                System.out.println(receivingPlayer.getPlayerName()+ ", you must now concede the game and pay one point");

            }
        }
        else {
            System.out.println(doublingPlayer.getPlayerName()+ ", you are not allowed to double.");
        }

        }
        public static void doubleStatus(Player player1, Player player2){
        if (player1.canDouble){
            System.out.println(player1.getPlayerName() + " owns the doubling cube!" );
        }
        else{
            System.out.println(player2.getPlayerName() + " owns the doubling cube!" );
        }

        }

        public static ArrayList<Integer> Test() {
            System.out.println("Please input the filename with the requested commands in the following format:");
            System.out.println("filename.txt");
            Scanner inputScanner = new Scanner(System.in);
            String filePath = inputScanner.nextLine(); // Read file name from the user
            ArrayList<Integer> fileCommandCodes = new ArrayList<>();

            try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = br.readLine()) != null) {
                    line=line.trim();
                    fileCommandCodes.add(processFileCommands(line));
                    System.out.println("Line read: " + line);
                }
            } catch (IOException e) {
                System.err.println("Error reading the file: " + e.getMessage());
            }
            return fileCommandCodes;
        }

    public static int processFileCommands(String line) {


            if (line.equalsIgnoreCase("quit")) {
                System.out.println("Quitting game..");
                return 1;
            } else if (line.equalsIgnoreCase("roll")) {
                return 4;
            } else if (line.equalsIgnoreCase("hint")) {
                return 2;

            } else if (line.equalsIgnoreCase("pip")) {
                return 3;

            }
             else if (line.equalsIgnoreCase("double")) {
                return 7;
            }
            else if (line.equalsIgnoreCase("doubleStatus")) {
                return 9;
            }
            else {
                return 8;
            }
        }


}


