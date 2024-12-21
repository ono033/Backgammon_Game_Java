package org.example;
import java.util.*;



public class Game {
    // Indicates if the user wants to quit completely
    public boolean quit=false;
    // Indicates if the current game has ended
    public boolean endCurrentGame=false;

    public Game(Player playerOne, Player playerTwo) {

        // Start the game setup
        int playerNumber = 1;

        // Add players to a list in the correct order
        List<Player> players = new ArrayList<>();
        if(playerOne.getPlayerNumber()==1) {
            players.add(playerOne);
            players.add(playerTwo);
        }
        else {
            players.add(playerTwo);
            players.add(playerOne);
        }

        // TurnManager manages whose turn it is
        TurnManager turnManager = new TurnManager(players);
        Player currentPlayer = turnManager.getCurrentPlayer();

        // commandCode will store results of user commands
        ArrayList<Integer> commandCode = new ArrayList<>();
        System.out.println("-------------------------------------------\n");
        System.out.println("New Game Starting...");
        System.out.println("-------------------------------------------\n");

        // Create a new board for the game
        Board gameBoard = new Board();

        boolean gameTerminated = false;
        // Main game loop
        while (true) {
            if (gameTerminated) {
                // If the game is terminated, set quit to true and break out
                quit = true;
                break;
            }
            else if(endCurrentGame) {
                // If the current game ended, just break out of the loop
                break;
            }
            else {
                // Prompt the current player to roll or choose another command
                System.out.println("Player " + currentPlayer.getPlayerNumber() + ", time to roll the dice! (type 'hint' to see other commands)");
                commandCode = Commands.getCommand();

                // Process each command entered by the user
                for (Integer currentCommandCode : commandCode) {
                    if (currentCommandCode == 1) {
                        // If 'quit' is entered, terminate the game
                        gameTerminated = true;
                        break;
                    }
                    else if(currentCommandCode == 2) {
                        // 'hint' command to show available commands
                        Commands.Hint();
                    }
                    else if(currentCommandCode == 3) {
                        // 'pip' command to display pip counts
                        Commands.Pips(gameBoard);
                    }
                    else if(currentCommandCode == 7) {
                        // 'double' command to propose doubling the stakes
                        endCurrentGame=Commands.Double(currentPlayer,turnManager.getOtherPlayer());
                    }
                    else if(currentCommandCode == 9) {
                        // 'doubleStatus' command to check who owns the doubling cube
                        Commands.doubleStatus(currentPlayer,turnManager.getOtherPlayer());
                    }
                    else if(currentCommandCode == 8) {
                        // If user enters an invalid command
                        System.out.println("Invalid input. Type 'hint' to see the list of available commands!");
                    }

                    else if (currentCommandCode == 4 || currentCommandCode==6) {
                        // 'roll' or 'dice' commands
                        ArrayList<Integer> rollResult = new ArrayList<>();

                        if(currentCommandCode ==6){
                            // If user sets custom dice values
                            rollResult = Commands.getCustomDiceResult();
                        }
                        else {
                            // Normal dice roll
                            rollResult = Commands.Roll();
                        }

                        // Keep taking turns until all dice are used
                        while (true) {
                            // Print the board and bar for the current player
                            gameBoard.printBoard(currentPlayer.playerNumber);
                            gameBoard.printBar();
                            System.out.print("\n\nPlayer " + currentPlayer.playerNumber + " Remaining dice: " + rollResult);

                            // Current player makes their moves using the available dice
                            gameBoard.takeTurn(currentPlayer.playerNumber, rollResult);

                            // Once all dice are used, move on to the next player
                            if (rollResult.isEmpty()) {
                                gameBoard.printBoard(currentPlayer.playerNumber);
                                turnManager.nextTurn();
                                currentPlayer = turnManager.getCurrentPlayer();
                                break;
                            }
                        }

                        // After completing the turn, check if the game has ended
                        if (GameStatus.checkGameEnd(gameBoard, currentPlayer, turnManager.getOtherPlayer())) {
                            // Reset board and end this game to start a new one possibly
                            gameBoard.initialiseVariables();
                            gameBoard.setUpBoard();
                            endCurrentGame = true;
                        }
                    }
                }
            }
        }

        // Update the match scores at the end of the game
        playerOne.setScore(playerOne.getScore()+playerOne.getPlayerMatchscore());
        playerTwo.setScore(playerTwo.getScore()+playerTwo.getPlayerMatchscore());

    }

}
