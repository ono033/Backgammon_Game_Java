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
}
