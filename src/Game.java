import java.util.*;


public class Game {
//

    public Game(Player playerOne, Player playerTwo) {

        int playerNumber = 1;
       // Player playerOne = new Player(); initialised in match instead
       playerOne.displayPlayerInfo();

       // Player playerTwo = new Player();
        playerTwo.displayPlayerInfo();

        ArrayList<Integer> commandCode = new ArrayList<>();
        System.out.println("Game Starting...");
        Board gameBoard = new Board();
        gameBoard.printBoard(playerNumber);
        System.out.println("Player " + playerNumber + ", time to roll the dice!");


        boolean gameTerminated = false;
        while (true) {
        if (gameTerminated) {
            break;
        }
        else {
            commandCode = Commands.getCommand(gameBoard);
            for (Integer currentCommandCode : commandCode) {
                if (currentCommandCode == 1) {             // Quit command entered
                    gameTerminated = true;
                    break;
                }
                else if(currentCommandCode == 2) {
                    Commands.Hint();
                }
                else if(currentCommandCode == 3) {
                    Commands.Pips(gameBoard);
                }
                else if (currentCommandCode == 4) {         // Roll command entered


                    ///// ono added taking turn (regular)

                    gameBoard.printBoard(playerNumber);
                    ArrayList<Integer> rollResult = Commands.Roll();
                    //rollResult = new ArrayList<>(Arrays.asList(1, 2, 6));

                    while (true) {

                        gameBoard.printBoard(playerNumber); //ono change back !!
                        gameBoard.printBar();
                        System.out.print("\n\nPlayer " + playerNumber + " Remaining dice: " + rollResult);
                        gameBoard.takeTurn(playerNumber, rollResult);
                        if (rollResult.isEmpty()) break;         //once all dice used up


                    }
                    //////


                    playerNumber++;
                    if (playerNumber == 3) {
                        playerNumber = 1;
                    }
                    //gameBoard.printBoard(playerNumber);

                    for (int value : rollResult) {              //Print contents of rollResult, FOR TESTING PURPOSES
                        System.out.print(value + " ");          //TESTING PURPOSES
                    }                                           //TESTING PURPOSES
                    System.out.println("Player " + playerNumber + ", time to roll the dice!");


                }
            }
            //Game game = new Game();
        }
        }







       /* Player player1 = new Player();
        Player player2 = nw Player();
        Board board = new Board();





        board.setUpBoard();
        board.printBoard(1);
        board.printBoard(2);
*/


    }


}
