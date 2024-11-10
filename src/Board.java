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


            public Checker getChecker(int pipIndex, int checkerIndex) {  // takes 0 indexing into account, so use pip number displayed on board
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





/*
    public void printBoard( int playerNumber) {
//addCheckertoPip(13, new Checker("X"));
        int PIPOFFSET = 24;


        System.out.println("13--+---+---+---+---18   BAR   19--+---+---+---+---24  OFF");

        for(int i= 0;i<5;i++) {
           // int start;
            //int end;

           int start = (NUMBEROFPIPS / 2 + 1);
           int end =NUMBEROFPIPS;

            if (playerNumber ==2){
            start = 24 - start;
            end = 24 - end;
            }

            // if player 2   

            for (int j = (NUMBEROFPIPS / 2 + 1); j <= NUMBEROFPIPS; j++) { // Print pips 13-24

                if(j==19){  // space for bar
                    System.out.print("    ---" );
                }

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
        System.out.println("-+--+---+---+---+---+- -    -  -+--+---+---+---+---+- ");


        int start =NUMBEROFPIPS / 2;
        int end = 0;

        if (playerNumber ==2){
            start = 25 - start;
            end = 25 - end;
        }


        for(int i= 0;i<5;i++) {

            for (int j = start ; j > end; j--) { // Print pips 13-24

                if(j==6){
                    System.out.print("    ---" );
                }
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
        System.out.println("12--+---+---+---+---07   BAR   06--+---+---+---+---01  OFF");

    }
*/

    public void printBoard( int playerNumber) {

        System.out.println("Player" + playerNumber + "'s Board");
        System.out.println("13--+---+---+---+---18   BAR   19--+---+---+---+---24  OFF");

        for(int i= 0;i<5;i++) {

            int start = (NUMBEROFPIPS / 2 + 1);// Start at pip 13
            int end =NUMBEROFPIPS; // End at pip 24
            int increment =1;

            if (playerNumber ==2){
                start = 12;    //24 - start;
                end = 1;        //24 - end;
                increment = -1;
            }


            for (int j = start; (playerNumber == 2) ? j >= end : j <= end; j += increment) { // Print pips 13-24

                if(j==19 ||j==6){  // space for bar
                    System.out.print("    ---" );
                }

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
        System.out.println("-+--+---+---+---+---+- -    -  -+--+---+---+---+---+- ");


        int start = NUMBEROFPIPS / 2;
        int end = 1;
        int increment = -1;

        if (playerNumber ==2){
            start = 13; //25 - start;
            end = 24;   //25 - end;
            increment = 1;
        }


        for(int i= 0;i<5;i++) {

            for (int j = start; (playerNumber == 2) ? j <= end : j >= end; j += increment){ // Print pips 13-24

                if(j==6 ||j==19){
                    System.out.print("    ---" );
                }
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
        System.out.println("12--+---+---+---+---07   BAR   06--+---+---+---+---01  OFF");

    }

    public static void main(String[] args) {
       Board board = new Board();
       board.printBoard(1);

        board.printBoard(2);

    }


}
