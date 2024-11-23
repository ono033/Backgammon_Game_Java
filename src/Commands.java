import java.util.Random;
import java.util.Scanner;

public class Commands {

    public static int getCommand(Board board) {
        while(true) {

            Scanner scanner = new Scanner(System.in);
            String userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("quit")) {
                System.out.println("Quitting game..");
                return 1;
            } else if (userInput.equalsIgnoreCase("roll")) {
                return 2;
            }
         else if (userInput.equalsIgnoreCase("hint")) {
            Commands.Hint();
        }
            else if (userInput.equalsIgnoreCase("pip")) {
                Commands.Pips(board);
            }
            else {
                System.out.println("Invalid input. Type 'roll' to roll the dice!");
            }
        }
    }
    public static int[] Roll() {

        Random random = new Random();
        // Roll two dice
        int die1 = random.nextInt(6) + 1;
        int die2 = random.nextInt(6) + 1;
        int[] result;

        // Display the result of each dice
        if (die1==die2){
            System.out.println("You rolled:");
            System.out.println("Die 1: " + die1);
            System.out.println("Die 2: " + die2);
            System.out.println("You rolled a double!");
            System.out.println("You can roll " + die1 +"-"+ die1 +"-"+ die1 +"-"+ die1);
            result = new int[] {die1, 2 * die1, 3 * die1, 4 * die1};
        }
        else{
            System.out.println("You rolled:");
            System.out.println("Die 1: " + die1);
            System.out.println("Die 2: " + die2);
            result = new int[] {die1, die2, die1 + die2};
        }
    return result;
    }

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

        public static void Pips(Board board) {
        int xpips=0;
        int opips=0;
        int invertXpip=0;
        for(int i=1; i<=24;i++){
            for(int j=0;j<=4;j++){
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


}
