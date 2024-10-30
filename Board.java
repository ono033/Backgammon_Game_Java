import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

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
setUpBoard();


    }


    public void initialiseVariables(){
        pips = new ArrayList<>();
        // Initialize each pip with a Stack
        for (int i = 0; i < NUMBEROFPIPS; i++) { // assuming a standard backgammon board with 24 pips
            ArrayList<Checker> pip = new ArrayList<>();
            pips.add(pip);
        }
    }

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

    public void addCheckerstoPip(String type, int pipIndex, int number){

        for(int i=0;i<number;i++){
            Checker checker = new Checker(type);
            addCheckertoPip(pipIndex,checker);
        }


    }



        public void setUpBoard(){
        addCheckerstoPip("X", 1,2);
            addCheckerstoPip("X", 12,5);
            addCheckerstoPip("X", 17,3);
            addCheckerstoPip("X", 20 ,5);

            addCheckerstoPip("O", 24,2);
            addCheckerstoPip("O", 13,5);
            addCheckerstoPip("O", 8,3);
            addCheckerstoPip("O", 6 ,5);
        }






    public void printBoard() {
//addCheckertoPip(13, new Checker("X"));
        System.out.println("13--+---+---+---+---18 BAR  19--+---+---+---+---24  OFF");

        for(int i= 0;i<6;i++) {

            for (int j = NUMBEROFPIPS / 2 + 1; j <= NUMBEROFPIPS; j++) { // Print pips 13-24
                Checker checker = getChecker(j,i);
                if(checker!=null) {
                    System.out.print(checker.toString());
                }
                else{
                    System.out.print("|");
                }
                System.out.print("---");

            }

            System.out.println();
        }
        System.out.println("-+--+---+---+---+---+- ---  -+--+---+---+---+---+- ");

        System.out.println("12--+---+---+---+---07 BAR  06--+---+---+---+---01  OFF");

    }
    public static void main(String[] args) {
       Board board = new Board();
       board.printBoard();

    }


}
