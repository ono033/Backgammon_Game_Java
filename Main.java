import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {


        int counter=1;
        Player playerOne = new Player();
        playerOne.displayPlayerInfo();

        Player playerTwo = new Player();
        playerTwo.displayPlayerInfo();

        System.out.println("Game Starting...");
        Board gameBoard = new Board();
        gameBoard.printBoard();
        System.out.println("Player "+counter+", time to roll the dice!");

        while(true){
            Scanner scanner = new Scanner(System.in);
            // Create a Random object for generating random numbers
            Random random = new Random();
            String userInput = scanner.nextLine();
                if (userInput.equalsIgnoreCase("quit")) {
                    System.out.println("Quitting game..");
                    break;
            }
                else if (userInput.equalsIgnoreCase("roll")){
                    gameBoard.diceRoll();
                    counter++;
                    if (counter==3){counter=1;}
                    gameBoard.printBoard();
                    System.out.println("Player " + counter + ", time to roll the dice!");

                }
                else{
                    System.out.println("Invalid input. Type 'roll' to roll the dice!");
                }

        }

        //Game game = new Game();
    }


}
