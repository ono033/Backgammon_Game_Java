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



    }


    public void initialiseVariables(){
        pips = new ArrayList<>();
        // Initialize each pip with a Stack
        for (int i = 0; i < NUMBEROFPIPS; i++) { // assuming a standard backgammon board with 24 pips
            ArrayList<Checker> pip = new ArrayList<>();
            pips.add(pip);
        }
    }


    public ArrayList<Checker> getPip(int pipIndex){
        return pips.get(pipIndex -1);
       // return pips.get(pipIndex-1); // due to zero indexing
    }


    // input is the pip index to add to and checker you want to add to the pip
    public void addCheckertoPip(int pipIndex, Checker checker){
    getPip(pipIndex).add(checker);
        // ono add something if full?

    }



    public void printBoard() {

        System.out.println("13--+---+---+---+---18 BAR  19--+---+---+---+---24  OFF");
        System.out.println("12--+---+---+---+---07 BAR  06--+---+---+---+---01  OFF");

    }
    public static void main(String[] args) {
       Board board = new Board();
       board.printBoard();

    }


}
