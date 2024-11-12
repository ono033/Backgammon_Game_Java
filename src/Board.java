import java.util.ArrayList;


public class Board {
    public static final int NUMBEROFPIPS = 24;


   /* private .. pips
   private pip?
   private middle
   private off/on
   top pips/ bottom pips, left and right?
    */

    private ArrayList<ArrayList<Checker>> pips;


    public enum MoveType {
        LEGAL, ILLEGAL, KNOCKOUT
    }

    private CheckerProperties player1Type;
    private CheckerProperties player2Type;

    public Board() {
// initialise
        this.player1Type = CheckerProperties.O;  // Example: Player 1 uses 'X'
        this.player2Type = CheckerProperties.X;  // Example: Player 2 uses 'O'
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


    public CheckerProperties getPlayerType(int playerNumber) {
        if (playerNumber == 1) {
            return player1Type;
        } else if (playerNumber == 2) {
            return player2Type;
        } else {
            System.out.println("Invalid player number");
            return null;
        }
    }

    public boolean isPlayerChecker(int playerNumber, Checker checker) {
        CheckerProperties playerType = getPlayerType(playerNumber);
        return checker != null && checker.getType() == playerType;
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

public int playerDirection(int playerNumber){
        if(playerNumber==1) return -1;

        if (playerNumber == 2){
            return 1;
        }
        return 0;
}

    public Checker getTopCheckerfromPip(int pipIndex) {

   int size = getPip(pipIndex).size();
        Checker topChecker = getChecker(pipIndex,size-1);

        if(topChecker!=null)System.out.print("topchecker:"+topChecker.toString());

        return topChecker;
    }

    public boolean compareCheckers(Checker checker1, Checker checker2) {
        if (checker1 == null || checker2 == null) {
            System.out.println("One or both checkers are null, cannot compare.");
            return false;
        }
        // Return true if both checkers are of the same type
        return checker1.getType() == checker2.getType();
    }


// can go tru all pips with this
    public MoveType isLegal(int playerNumber, int sourcePipindex, int numberofMoves) {



        //ono add error check for wrong colour checker
        int playerDirection = playerDirection(playerNumber);
        int destinationPipindex = sourcePipindex+ (playerDirection * numberofMoves);
        System.out.println("dest:" + destinationPipindex);

        //error check ono for ourtside of 0 and 24

        if (destinationPipindex < 1|| destinationPipindex > NUMBEROFPIPS){ // ono add something for beargn off
            return MoveType.ILLEGAL;
        }

        ArrayList<Checker> sourcePip = getPip(sourcePipindex);
        ArrayList<Checker> destinationPip = getPip(destinationPipindex);

        // ono add check foir empky
        Checker moveChecker = getTopCheckerfromPip(sourcePipindex);
        System.out.println("movechecker" + moveChecker.toString());
        boolean rightCheckertype = isPlayerChecker(playerNumber, moveChecker);
//if no checker or not players checker on spot
        if (moveChecker==null || !rightCheckertype ){
            System.out.println("moveChecker==null || !rightCheckertype");
            return MoveType.ILLEGAL;
        }
        // move checker is player's
        else{

            int destinationSize = destinationPip.size();
            System.out.println("wefwef" + destinationSize);

            Checker destinationTopChecker = getTopCheckerfromPip(destinationPipindex);
            int numberofCheckersonDestination;
            boolean sameChecker =compareCheckers(destinationTopChecker, moveChecker);


            //destination emptpy
            if(destinationSize==0){
                return MoveType.LEGAL;
            }
            //destination has 1 of other persons checkers
            if(destinationSize==1 && !sameChecker){
                return MoveType.KNOCKOUT;
            }

            //if sametype of checker
            if(destinationSize>0 && sameChecker){
                return MoveType.LEGAL;
            }


        }


        return MoveType.ILLEGAL;

        //direction of player




    }

    /*public ArrayList<ArrayList<Integer>> legalMoves(int numberOfMoves, int playerNumber) {

        ArrayList<ArrayList<Integer>> validMoves = new ArrayList<>();
        int direction = playerDirection(playerNumber); //?

        for (int startPip = 1; startPip <= NUMBEROFPIPS; startPip++) {

            int destinationPip = startPip + direction * numberOfMoves; //?

            //loop thru pips
            MoveType result = isLegal(playerNumber, startPip, numberOfMoves);

            if (result == MoveType.LEGAL || result == MoveType.KNOCKOUT) {
                ArrayList<Integer> move = new ArrayList<>();
                move.add(startPip);         // Store the starting pip
                move.add(destinationPip);   // Store the destination pip
                move.add(result == MoveType.LEGAL ? 1 : 2);  // 1 for LEGAL, 2 for KNOCKOUT
                validMoves.add(move);
            }
        }

        return validMoves;

        }

  */

/*
    public static void main(String[] args) {
        Board board = new Board();
        board.setUpBoard();  // Ensure the board is correctly set up for the game

        int playerNumber = 1;  // Specify the player number you want to check moves for
        int numberOfMoves = 4;  // Specify the number of steps you want to check for each move
        ArrayList<ArrayList<Integer>> moves = board.legalMoves(numberOfMoves, playerNumber);
        printValidMoves(moves);
    }






    public static void main(String[] args) {
        Board board = new Board();
        board.setUpBoard(); // Make sure the board is set up with initial checkers

        // Test move legality
        // Assuming player 1 is moving checker from pip 1 to pip 5
        System.out.println("Testing Move from Pip 24 to Pip 2 for Player 1:");
        MoveType move1 = board.isLegal(1, 24, 1);
        System.out.println("Move 1: " + move1);

        // Testing move that should be illegal if no checker exists in source
        System.out.println("Testing Move from Pip 8 to Pip 6 for Player 1 (no checker expected at source):");
        MoveType move2 = board.isLegal(1, 8, 6);
        System.out.println("Move 2: " + move2);

        // Testing a move that results in a knockout
        System.out.println("Testing Move from Pip 12 to Pip 13 for Player 2 (should be a knockout if enemy checker present):");
        MoveType move3 = board.isLegal(2, 12,2 );
        System.out.println("Move 3: " + move3);

        // Testing a move that is out of bounds
        System.out.println("Testing Move from Pip 24 to Pip 28 for Player 2 (out of bounds):");
        MoveType move4 = board.isLegal(2, 24, 4);
        System.out.println("Move 4: " + move4);

        // Testing another legal move
        System.out.println("Testing Move from Pip 17 to Pip 13 for Player 1:");
        MoveType move5 = board.isLegal(1, 17, -4);
        System.out.println("Move 5: " + move5);
    }

    /*public static void main(String[] args) {
       Board board = new Board();
       board.printBoard(1);

        //board.printBoard(2);
   Checker check = board.getTopCheckerfromPip(13);


       Checker check2 = board.getTopCheckerfromPip(18);


        Checker check62 = board.getTopCheckerfromPip(17);

//check.printChecker();
       // check2.printChecker();
      //  check62.printChecker();



    } */


}
