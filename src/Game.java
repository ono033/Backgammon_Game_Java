import java.util.*;


public class Game {

    public boolean quit=false;

    // GameStatus gameStatus;
    public Game(Player playerOne, Player playerTwo) {

        int playerNumber = 1;

        playerOne.displayPlayerInfo();


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
        //gameBoard.printBoard(currentPlayer.playerNumber);


        boolean gameTerminated = false;
        while (true) {
        if (gameTerminated) {
            quit = true;
            break;
        }
        else {
            gameBoard.printBoard(currentPlayer.playerNumber);
            System.out.println("Player " + currentPlayer.getPlayerNumber() + ", time to roll the dice! type \"hint\" for all commands");
            commandCode = Commands.getCommand();
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


                else if (currentCommandCode == 4 || currentCommandCode==6) {         // Roll command entered or dice command entered


                    gameBoard.printBoard(currentPlayer.playerNumber);
                    ArrayList<Integer> rollResult = new ArrayList<>();


                    if(currentCommandCode ==6){ // if Dice command entered
                        rollResult = Commands.getCustomDiceResult();

                    }
                    else {           // Regular Roll
                        rollResult = Commands.Roll();
                    }
                    while (true) {

                        gameBoard.printBoard(currentPlayer.playerNumber);
                        gameBoard.printBar();
                        System.out.print("\n\nPlayer " + currentPlayer.playerNumber + " Remaining dice: " + rollResult);
                        gameBoard.takeTurn(currentPlayer.playerNumber, rollResult);
                        if (rollResult.isEmpty()) break;         //once all dice used up
                        turnManager.nextTurn();
                        currentPlayer = turnManager.getCurrentPlayer();

                    }


                    if (GameStatus.checkGameEnd(gameBoard, currentPlayer, turnManager.getOtherPlayer())) {
                        System.out.println("Game Over:");
                        System.out.println("Winner: " + GameStatus.getWinner().getPlayerName());
                        System.out.println("Winning Status: " + GameStatus.getStatus());
                        System.out.println("Points Awarded: " + GameStatus.getPointsAwarded());

                        //Terminate current game and sets up new board
                        gameBoard.initialiseVariables(); // Reset the board
                        gameBoard.setUpBoard();          // Set up for a new game
                        gameTerminated = true;           // End the game loop


                    }

                }
            }

        }

        }

    }

}
