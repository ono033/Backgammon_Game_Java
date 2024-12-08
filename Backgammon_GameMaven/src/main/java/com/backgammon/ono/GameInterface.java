package com.backgammon.ono;

import java.util.ArrayList;

public class GameInterface {

    // Legal
    private Board board;


    public GameInterface() {

        Board board = new Board();
        board.setUpBoard();
    }




    void printPlayerBoard(int playerNumber)
    {
        board.printBoard(playerNumber);

    }

    void printAllPlayerBoards() {
        board.printBoard(1); // Print Player 1's board
        board.printBoard(2); // Print Player 2's board
    }


    public static void main(String[] args) {
   GameInterface gameInterface = new GameInterface();
  // gameInterface.printPlayerBoard(1);

    }

    public static class Game {


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
                    else if (currentCommandCode == 4 || currentCommandCode==6) {         // Roll command entered or dice command entered


                        gameBoard.printBoard(playerNumber);
                        ArrayList<Integer> rollResult = new ArrayList<>();


                        if(currentCommandCode ==6){ // if Dice command entered

                            rollResult = Commands.getCustomDiceResult();

                        }


                           else {           // Regular Roll
                            rollResult = Commands.Roll();
                        }

                        while (true) {

                            gameBoard.printBoard(playerNumber);
                            gameBoard.printBar();
                            System.out.print("\n\nPlayer " + playerNumber + " Remaining dice: " + rollResult);
                            gameBoard.takeTurn(playerNumber, rollResult);
                            if (rollResult.isEmpty()) break;         //once all dice used up


                        }


                        playerNumber++;
                        if (playerNumber == 3) {
                            playerNumber = 1;
                        }


                        for (int value : rollResult) {              //Print contents of rollResult, FOR TESTING PURPOSES
                            System.out.print(value + " ");          //TESTING PURPOSES
                        }                                           //TESTING PURPOSES
                        System.out.println("Player " + playerNumber + ", take turn");


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

        public static void main(String[] args) {        //testing
            Player.resetPlayercount();
            Player[] players = new Player[2];

            players[0] = new Player(); // Player One
            players[1] = new Player();   // Player Two







                Game game = new Game(players[0], players[1]);

        }

    }
}
