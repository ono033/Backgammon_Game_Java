import java.util.*;

public class Board {
    public static final int NUMBEROFPIPS = 24;


   /* private .. pips
   private pip?
   private middle
   private off/on
   top pips/ bottom pips, left and right?
    */

    private ArrayList<ArrayList<Checker>> pips;




    public Board() {
// initialise
        initialiseVariables();



    }


    public void initialiseVariables(){
        pips = new ArrayList<>();
        // Initialize each pip with a Stack
        for (int i = 0; i < NUMBEROFPIPS; i++) { // assuming a standard backgammon board with 24 pips
            ArrayList<Checker> pip = new ArrayList<>();
            pips.add(pip);
        }
    }

    public void diceRoll() {

        Random random = new Random();
                // Roll two dice
                int die1 = random.nextInt(6) + 1;
                int die2 = random.nextInt(6) + 1;

                // Display the result of each dice
                if (die1==die2){
                System.out.println("You rolled:");
                System.out.println("Die 1: " + die1);
                System.out.println("Die 2: " + die2);
                System.out.println("You rolled a double!");
                System.out.println("You can roll " + die1 +"-"+ die1 +"-"+ die1 +"-"+ die1);
        }
    else{
                    System.out.println("You rolled:");
                    System.out.println("Die 1: " + die1);
                    System.out.println("Die 2: " + die2);
                }}

    public ArrayList<Checker> getPip(int pipIndex) {
        if (pipIndex > 0 && pipIndex <= NUMBEROFPIPS) {
            return pips.get(pipIndex - 1); // Convert 1-based index to 0-based
        } else {
            return null; // Returns null if the pip index is out of bounds
        }
    }



    public Checker getChecker(int pipIndex, int checkerIndex) {
        ArrayList<Checker> pip = getPip(pipIndex);
        if (checkerIndex >= 0 && checkerIndex < pip.size()) {
            return pip.get(checkerIndex);
        } else {
            return null; // Returns null if the index is out of bounds
        }
    }



    // input is the pip index to add to and checker you want to add to the pip
    public void addCheckertoPip(int pipIndex, Checker checker){
        getPip(pipIndex).add(checker);
        // ono add something if full?

    }



    public void printBoard() {

        System.out.println(" 13 +---+---+---+---+ 18 BAR 19 +---+---+---+---+ 24  OFF");

        // for(int i= 0;i<6;i++) {

            //for (int j = NUMBEROFPIPS / 2 + 1; j <= NUMBEROFPIPS; j++) { // Print pips 13-24
              //  Checker checker = getChecker(j,i);
                //if(checker!=null) {
                    //System.out.println(checker.toString());
                //}
                //else{
                  //  System.out.print("|");
                //}
                //System.out.print("---");

            //}
        System.out.println("| O |---|---|---| X |---|   | X |---|---|---|---| O | ");
        System.out.println("| O |---|---|---| X |---|   | X |---|---|---|---| O | ");
        System.out.println("| O |---|---|---|---|---|   | X |---|---|---|---|---| ");
        System.out.println("| O |---|---|---|---|---|   | X |---|---|---|---|---| ");
        System.out.println("| O |---|---|---|---|---|   | X |---|---|---|---|---| ");

        System.out.println();
        System.out.println("| X |---|---|---|---|---|   | O |---|---|---|---|---| ");
        System.out.println("| X |---|---|---|---|---|   | O |---|---|---|---|---| ");
        System.out.println("| X |---|---|---| O |---|   | O |---|---|---|---|---| ");
        System.out.println("| X |---|---|---| O |---|   | O |---|---|---|---| X | ");
        System.out.println("| X |---|---|---| O |---|   | O |---|---|---|---| X | ");

        System.out.println(" 12 +---+---+---+---+ 07 BAR 06 +---+---+---+---+ 01  OFF");
        System.out.println();

    }
   // public static void main(String[] args) {
     //   Board board = new Board();
      //  board.printBoard();

    //}


}