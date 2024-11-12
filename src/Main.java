public class Main {

    public static void main(String[] args) {


        int playerNumber =1;
        Player playerOne = new Player();
        playerOne.displayPlayerInfo();

        Player playerTwo = new Player();
        playerTwo.displayPlayerInfo();

        int currentCommandCode=0;

        System.out.println("Game Starting...");
        Board gameBoard = new Board();
        gameBoard.printBoard(playerNumber);
        System.out.println("Player "+ playerNumber +", time to roll the dice!");

        while(true){

            currentCommandCode=Commands.getCommand();
            if (currentCommandCode==1){             // Quit command entered
                break;
            }
            else if(currentCommandCode==2){         // Roll command entered
                    playerNumber++;
                    if (playerNumber ==3){
                        playerNumber =1;}
                    gameBoard.printBoard(playerNumber);
                    System.out.println("Player " + playerNumber + ", time to roll the dice!");
            }

        }

        //Game game = new Game();
    }


}
