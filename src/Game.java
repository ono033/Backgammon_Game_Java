import java.util.*;


public class Game {
//

    public Game() {

        int playerNumber = 1;
        Player playerOne = new Player(1);
        playerOne.displayPlayerInfo();

        Player playerTwo = new Player(2);
        playerTwo.displayPlayerInfo();

        // Add players to a list
        List<Player> players = new ArrayList<>();
        players.add(playerOne);
        players.add(playerTwo);

        // Initialize TurnManager with the players list
        TurnManager turnManager = new TurnManager(players);
        Player currentPlayer = turnManager.getCurrentPlayer();


        ArrayList<Integer> commandCode = new ArrayList<>();
        System.out.println("Game Starting...");
        Board gameBoard = new Board();
        gameBoard.printBoard(playerNumber);


        boolean gameTerminated = false;
        while (true) {
        if (gameTerminated) {
            break;
        }
        else {
            System.out.println("Player " + currentPlayer.getPlayerNumber() + ", time to roll the dice!");
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
                else if(currentCommandCode == 7) {
                    Commands.Double(currentPlayer,turnManager.getOtherPlayer());
                }
                else if(currentCommandCode == 9) {
                    Commands.doubleStatus(currentPlayer,turnManager.getOtherPlayer());
                }
                else if(currentCommandCode == 8) {
                    System.out.println("Invalid input. Type 'hint' to see the list of available commands!");
                }

                else if (currentCommandCode == 4) {         // Roll command entered


                    ///// ono added taking turn (regular)
                    currentPlayer.setHasRolled(true);
                    gameBoard.printBoard(playerNumber);
                    ArrayList<Integer> rollResult = Commands.Roll();
                    //rollResult = new ArrayList<>(Arrays.asList(1, 2, 6));

                    while (true) {

                        gameBoard.printBoard(playerNumber); //ono change back !!
                        gameBoard.printBar();
                        System.out.print("\n\nPlayer " + playerNumber + " Remaining dice: " + rollResult);
                        gameBoard.takeTurn(playerNumber, rollResult);
                        if (rollResult.isEmpty()) break;         //once all dice used up
                        turnManager.nextTurn();
                        currentPlayer = turnManager.getCurrentPlayer();

                    }


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
