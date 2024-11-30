import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;

public class Board {
    public static final int NUMBEROFPIPS = 24;

    // Constants for accessing move details in ArrayList moveEntry
    private static final int MOVE_INDEX = 0;
    private static final int SOURCE_PIP = 1;
    private static final int DESTINATION_PIP = 2;
    private static final int DICE_VALUE = 3;
    private static final int MOVE_TYPE = 4;


    private ArrayList<ArrayList<Checker>> pips;
    private ArrayList<Checker> player1Bar;
    private ArrayList<Checker> player2Bar;

    public enum MoveType {
        LEGAL, ILLEGAL, KNOCKOUT, RE_ENTRY, KNOCKOUT_RE_ENTRY
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


    public void initialiseVariables() {
        pips = new ArrayList<>();
        // Initialize each pip with a Stack
        for (int i = 0; i < NUMBEROFPIPS; i++) { // assuming a standard backgammon board with 24 pips
            ArrayList<Checker> pip = new ArrayList<>();
            pips.add(pip);
        }

        player1Bar = new ArrayList<>();
        player2Bar = new ArrayList<>();
    }

    public ArrayList<Checker> getPlayerbar(int playerNumber){

        if(playerNumber == 1){
            return player1Bar;
        }
        else
            return player2Bar;

    }

    public void printBar(){
        System.out.print("Bar:");
        for(Checker checker: player1Bar){
            System.out.print(checker + ",    "  );
        }

        for(Checker checker: player2Bar){
            System.out.print(checker + ",    "  );
        }
    }



    public void addtoBar(Checker checker){
        int playerNumber = checker.getPlayerNumber();

            if(playerNumber == 1){
                player1Bar.add(checker);
            }
            else if(playerNumber == 2){
                player2Bar.add(checker);
            }
    }

    public boolean isPlayerOnBar(int playerNumber){
        ArrayList<Checker> bar = getPlayerbar(playerNumber);
        if (bar.isEmpty()) return false;
        Checker checker = bar.getLast();
        if(isPlayerChecker(playerNumber, checker)) return true;

        return false;

    }

    public Checker removefromBar(int playerNumber) {
        // Get the bar for the specified player
        ArrayList<Checker> bar = getPlayerbar(playerNumber);

        // Check if the bar is empty
        if (bar.isEmpty()) {
            System.out.println("No checkers on the bar for Player " + playerNumber);
            return null;
        }

        // Remove and return the top (last) checker
        return bar.remove(bar.size() - 1);
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
    public void addCheckertoPip(int pipIndex, Checker checker) {
        getPip(pipIndex).add(checker);
        // ono add something if full?

    }

    public void addCheckerstoPip(String type, int pipIndex, int number) {

        for (int i = 0; i < number; i++) {
            Checker checker = new Checker(type);
            addCheckertoPip(pipIndex, checker);
        }


    }

    public Checker removeCheckerfromPip(int pipIndex) {
        if (pipIndex < 1 || pipIndex > NUMBEROFPIPS) {
            System.out.println("Invalid pip index: " + pipIndex);
            return null;
        }

        ArrayList<Checker> pip = getPip(pipIndex);

        if (pip == null || pip.isEmpty()) {
            System.out.println("No checkers to remove from pip: " + pipIndex);
            return null;
        }

        return pip.remove(pip.size() - 1);
    }


    public void setUpBoard() {
        addCheckerstoPip("X", 1, 2);
        addCheckerstoPip("X", 12, 5);
        addCheckerstoPip("X", 17, 3);
        addCheckerstoPip("X", 20, 5);

        addCheckerstoPip("O", 24, 2);
        addCheckerstoPip("O", 13, 5);
        addCheckerstoPip("O", 8, 3);
        addCheckerstoPip("O", 6, 5);
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



    public void printBoard(int playerNumber) {
    // between 18 and 19 print current players bar
        //
        int opponentPlayerNumber = (playerNumber == 1) ? 2 : 1;

        ArrayList<Checker> activePlayerBar = getPlayerbar(playerNumber);
        ArrayList<Checker> opponentPlayerBar = getPlayerbar(opponentPlayerNumber);


        System.out.println("Player" + playerNumber + "'s Board");
        System.out.println("13--+---+---+---+---18   BAR   19--+---+---+---+---24  OFF");

        for (int i = 0; i < 5; i++) {

            int start = (NUMBEROFPIPS / 2 + 1);// Start at pip 13
            int end = NUMBEROFPIPS; // End at pip 24
            int increment = 1;

            if (playerNumber == 2) {
                start = 12;    //24 - start;
                end = 1;        //24 - end;
                increment = -1;
            }


            for (int j = start; (playerNumber == 2) ? j >= end : j <= end; j += increment) { // Print pips 13-24

                if (j == 19 || j == 6) {  // space for bar

                    if (!activePlayerBar.isEmpty() && i >= 0 && i < activePlayerBar.size() && activePlayerBar.get(i) != null) {
                        // Safe to access activePlayerBar.get(i)
                       // System.out.println("Index i(shoukd be 0)" + i);
                        Checker checker = activePlayerBar.get(i);

                        System.out.print("  ");
                        System.out.print(checker.toString());
                        System.out.print(" ---");
                    }


                    else {

                        System.out.print("    ---");
                    }
                }


                Checker checker = getChecker(j, i);
                if (checker != null) {

                    System.out.print(checker.toString());
                } else {
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

        if (playerNumber == 2) {
            start = 13; //25 - start;
            end = 24;   //25 - end;
            increment = 1;
        }


        for (int i = 0; i < 5; i++) {

            for (int j = start; (playerNumber == 2) ? j <= end : j >= end; j += increment) { // Print pips 13-24

                if (j == 6 || j == 19) {        // Space for bar


                    if (!opponentPlayerBar.isEmpty() && i >= 0 && i < opponentPlayerBar.size() && opponentPlayerBar.get(i) != null) {
                        // Safe to access activePlayerBar.get(i)
                        // System.out.println("Index i(shoukd be 0)" + i);
                        Checker checker = opponentPlayerBar.get(i);

                        System.out.print("  ");
                        System.out.print(checker.toString());
                        System.out.print(" ---");
                    }


                    else {

                        System.out.print("    ---");
                    }


                }

                Checker checker = getChecker(j, i);
                if (checker != null) {
                    System.out.print(checker.toString());
                } else {
                    System.out.print("|");
                }
                System.out.print("---");

            }

            System.out.println();
        }
        System.out.println("12--+---+---+---+---07   BAR   06--+---+---+---+---01  OFF");

        System.out.println("Can bear off: " + canBearoff(playerNumber));


    }

    public int playerDirection(int playerNumber) {
        if (playerNumber == 1) return -1;

        if (playerNumber == 2) {
            return 1;
        }
        return 0;
    }





    public Checker getTopCheckerfromPip(int pipIndex) {

        int size = getPip(pipIndex).size();
        Checker topChecker = getChecker(pipIndex, size - 1);

       // if (topChecker != null) System.out.print("topchecker:" + topChecker.toString()); ono remove

        return topChecker;
    }

    public boolean compareCheckers(Checker checker1, Checker checker2) {
        if (checker1 == null || checker2 == null) {
         //   System.out.println("One or both checkers are null, cannot compare."); ono remove
            return false;
        }
        // Return true if both checkers are of the same type
        return checker1.getType() == checker2.getType();
    }


    public MoveType isLegalreEntry(int playerNumber, int sourceIndex, int numberofMoves){
        int playerDirection = playerDirection(playerNumber);
        int destinationPipindex = sourceIndex + (playerDirection * numberofMoves);
        //System.out.println("reentry pip index! destinatioin" + destinationPipindex);
        int destinationSize = getPip(destinationPipindex).size();

        Checker moveChecker = new Checker(playerNumber);
        Checker destinationTopChecker = getTopCheckerfromPip(destinationPipindex);

        boolean sameChecker = compareCheckers(destinationTopChecker, moveChecker);

        //destination empty
        if (destinationSize == 0) {
            return MoveType.RE_ENTRY;
        }
        //destination has 1 of other persons checkers
        if (destinationSize == 1 && !sameChecker) {
            return MoveType.KNOCKOUT_RE_ENTRY;
        }

        //if sametype of checker
        if (destinationSize > 0 && sameChecker) {
          //  System.out.println("reentry 2 : ");
            return MoveType.RE_ENTRY;
        }

return MoveType.ILLEGAL;

    }








    // can go tru all pips with this
    public MoveType isLegal(int playerNumber, int sourcePipindex, int numberofMoves) {

        if(sourcePipindex ==0 || sourcePipindex==25){// Check if reentry is legal
            return isLegalreEntry(playerNumber, sourcePipindex, numberofMoves);
        }

        // add if all in right quadrate  bear off possible? call function


        //ono add error check for wrong colour checker
        int playerDirection = playerDirection(playerNumber);
        int destinationPipindex = sourcePipindex + (playerDirection * numberofMoves);
       // System.out.println("dest:" + destinationPipindex); ono remove

        //error check ono for ourtside of 0 and 24

        if (destinationPipindex < 1 || destinationPipindex > NUMBEROFPIPS) { // accounts for bearing off too      ono add something for beargn off
            return MoveType.ILLEGAL;
        }


        ArrayList<Checker> sourcePip = getPip(sourcePipindex);

        // ono rn
        if (sourcePip == null||sourcePip.isEmpty()) {
           // System.out.println("Source pip is empty or null."); ono remove
            return MoveType.ILLEGAL;
        }

        ArrayList<Checker> destinationPip = getPip(destinationPipindex);
        Checker moveChecker = getTopCheckerfromPip(sourcePipindex);

        // Validate checker ownership
        if (moveChecker == null || !isPlayerChecker(playerNumber, moveChecker)) {
          //  System.out.println("No checker to move or checker does not belong to player."); ono remove
            return MoveType.ILLEGAL;
        }


       // System.out.println("movechecker" + moveChecker.toString()); ono remove
        boolean rightCheckertype = isPlayerChecker(playerNumber, moveChecker);
//if no checker or not players checker on spot

        if (moveChecker == null || !rightCheckertype) {
            System.out.println("moveChecker==null || !rightCheckertype");
            return MoveType.ILLEGAL;
        }

        // move checker is player's
        else {

            int destinationSize = destinationPip.size();
           // System.out.println("dest size" + destinationSize);

            Checker destinationTopChecker = getTopCheckerfromPip(destinationPipindex);
            int numberofCheckersonDestination;
            boolean sameChecker = compareCheckers(destinationTopChecker, moveChecker);


            //destination empty
            if (destinationSize == 0) {
                return MoveType.LEGAL;
            }
            //destination has 1 of other persons checkers
            if (destinationSize == 1 && !sameChecker) {
                return MoveType.KNOCKOUT;
            }

            //if sametype of checker
            if (destinationSize > 0 && sameChecker) {
                return MoveType.LEGAL;
            }


        }

        return MoveType.ILLEGAL;

    }

    public void movePiptoPip(int sourceIndex, int destinationIndex){

        Checker checker = removeCheckerfromPip(sourceIndex);
        if (checker == null) {
            System.out.println("No checker to move");
        }
        addCheckertoPip(destinationIndex, checker);

    }



    public ArrayList<ArrayList<Object>> legalMoves(int playerNumber,  ArrayList<Integer>  rollResult) {

        ArrayList<ArrayList<Object>> legalMoves = new ArrayList<>();
        int sourceIndex;
        int destinationIndex;

        int count = 1;
        if(isPlayerOnBar(playerNumber)) {


            //if player has checker on bar
            if(playerNumber ==1){
                sourceIndex = 25;
            }
            else sourceIndex = 0;

            //source = 25 for player 1, 0 for player2
            for (int moveCount : rollResult) {

                destinationIndex = sourceIndex + (moveCount * playerDirection(playerNumber));
                MoveType moveType = isLegal(playerNumber, sourceIndex, moveCount);

                if (moveType != MoveType.ILLEGAL) {
                    ArrayList<Object> moveEntry = new ArrayList<Object>(Arrays.asList(count, sourceIndex, destinationIndex, moveCount, moveType));
                    legalMoves.add(moveEntry);
                }

                count++;
            }

        }

        else{

            //int count = 1;
            // legalMoves[ index, source, destination, no of moves, movetype ]



            for (int moveCount : rollResult) {
                sourceIndex = 1; // loop from 1 - 24 ono
                while (sourceIndex < NUMBEROFPIPS + 1) {
                    //go through checkers

                    // go through dice combos
                    //int moveCount = 0;
                    //moveCount = rollResult[0]; // ono remove

                    MoveType moveType = isLegal(playerNumber, sourceIndex, moveCount);
                    destinationIndex = sourceIndex + (moveCount * playerDirection(playerNumber));
                    //      System.out.println("Dice Moves:" + moveCount + "\nFrom" + sourceIndex + " to " + destinationIndex + " Is moveType:" + moveType); ono take out

                    if (moveType != MoveType.ILLEGAL) { //if (moveType == MoveType.LEGAL || moveType == MoveType.KNOCKOUT) {


                        ArrayList<Object> moveEntry = new ArrayList<Object>(Arrays.asList(count, sourceIndex, destinationIndex, moveCount, moveType));

                        legalMoves.add(moveEntry);

                        count++;
                    }


                    sourceIndex++;
                }
            }
        }

        return legalMoves;

    }

    public boolean canBearoff(int playerNumber){
        //Checks if all of player's checkers are on home board
        // - Validates that no checkers are on bar and no checkers are outside of home board

        if (isPlayerOnBar(playerNumber)) {  //player cannot bear off if they are on Bar
            return false;
        }


        int direction = playerDirection(playerNumber);

        int startInvalidRange = (playerNumber == 1) ? 24 : 1;   // Player 1 starts at pip 7
        int pipIndex = startInvalidRange;
        //int endInvalidRange = (playerNumber == 1) ? 24 : 18;   // Player 1 ends at pip 24
        ArrayList<Checker> pip;

       // for(int i = startInvalidRange; i <= endInvalidRange; i++) {}

        for (int i = 1; i <= 18; i++) {
            //checks players non home board pips

            pip = getPip(pipIndex);

            if (!pip.isEmpty()){
                Checker checker =pip.getLast();

                if(isPlayerChecker(playerNumber, checker)){
                    return false;
                }
            }

            pipIndex += direction;

        }
        return true;

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


    public MoveType isLegalBearOff(int playerNumber){
        return MoveType.ILLEGAL;
    }






    public void printLegalMoves(ArrayList<ArrayList<Object>> legalMoves) {
      // System.out.println("Legal Moves:");
        for (ArrayList<Object> move : legalMoves) {
          //  System.out.println("Move " + move.get(0) + ": From Pip " + move.get(1) + " to Pip " + move.get(2) + ", Dice Value: " + move.get(3) + ", Move Type: " + move.get(4));
         //   System.out.println(move);
           // if(canBearoff(playerDirection()))


            System.out.println("Move " + move.get(MOVE_INDEX) +
                    ": From Pip " + move.get(SOURCE_PIP) +
                    " to Pip " + move.get(DESTINATION_PIP) +
                    "         Dice Value: " + move.get(DICE_VALUE) +
                    ", Move Type: " + move.get(MOVE_TYPE) );


        }
}

public boolean makeMove(ArrayList<ArrayList<Object>> legalMoves, int moveNumber){

        if(moveNumber<1 || moveNumber>legalMoves.size()) {      //ono check if this line is right
            System.out.println("Illegal Move");
            return false;
        }

        ArrayList<Object> move = legalMoves.get(moveNumber-1);
        MoveType moveType =  (MoveType)move.get(MOVE_TYPE);
        int sourceIndex = (int) move.get(SOURCE_PIP);
        int destinationIndex = (int) move.get(DESTINATION_PIP);
        if(moveType ==MoveType.ILLEGAL){ // Return if move is illegal
            System.out.println("Illegal Move");
            return false;
        }


    if(moveType ==MoveType.RE_ENTRY){
        moveBartoPip(sourceIndex, destinationIndex);            //also removes from bar
        System.out.println("Re-Entry Move... Successfull");

    }

    if(moveType ==MoveType.KNOCKOUT_RE_ENTRY){
        Checker hitChecker = removeCheckerfromPip(destinationIndex); // removes opposite
        addtoBar(hitChecker);

        moveBartoPip(sourceIndex, destinationIndex);            //also removes from bar
        System.out.println("Re-Entry Move... Successfull");

    }

        if(moveType ==MoveType.LEGAL){
            movePiptoPip(sourceIndex, destinationIndex);
            System.out.println("Legal Move... Successfull");

        }

        if(moveType ==MoveType.KNOCKOUT){
            Checker hitChecker = removeCheckerfromPip(destinationIndex); // removes opposite
            // ono add to bar
            addtoBar(hitChecker);
            movePiptoPip(sourceIndex, destinationIndex);
            System.out.println("Knockout Move... Successfull");


        }

return true;
}

    public void moveBartoPip(int sourceIndex, int destinationIndex){
        int playerNumber;

        if(sourceIndex ==25) {
            playerNumber =  1;
        }
        else if (sourceIndex ==0) {
            playerNumber =  2; //
        }

        else return;

        Checker checker = removefromBar(playerNumber);

        //Checker checker = removeCheckerfromPip(sourceIndex);
        if (checker == null) {
            System.out.println("No checker to move");
        }
        addCheckertoPip(destinationIndex, checker);
    }


public void takeTurn(int playerNumber,  ArrayList<Integer>  diceRoll){//ono what do I add?
//
    ArrayList<ArrayList<Object>> legalMoves = legalMoves(playerNumber, diceRoll);
    System.out.println("\nSelect Move: " );
    printLegalMoves(legalMoves);

    if (legalMoves.isEmpty()) {
        System.out.println("No legal moves available for Player " + playerNumber + ". Skipping turn.");
        diceRoll.clear();
        return; // Exit this turn
    }

    Scanner scanner = new Scanner(System.in);
    if (!scanner.hasNextInt()) {
        System.out.println("Invalid input. Please enter a valid number.");
        return;
    }
    int moveNumber = scanner.nextInt();

//if no more legal moves

    boolean moveSuccess = makeMove(legalMoves, moveNumber);
// remove dice
    if(moveSuccess) {
        int diceValue = (int) legalMoves.get(moveNumber - 1).get(DICE_VALUE);
        diceRoll.remove(Integer.valueOf(diceValue));
    }
    else System.out.println("Move Failed");
}


public static void main(String[] args) {

    Board board = new Board();


    int playerNumber = 1;
while(true) {

    ArrayList<Integer> rollresult = new ArrayList<>(Arrays.asList(1, 2, 6));

while(true) {
   // board.printBoard(1); //ono change back !!
    board.printBoard(playerNumber);
    board.printBar();
   // System.out.println("Player turn:" + playerNumber);
    System.out.print("\nPlayer " + playerNumber + " Remaining dice: " + rollresult);
    board.takeTurn(playerNumber, rollresult);
    if(rollresult.isEmpty()) break;



}
    if (playerNumber == 1) playerNumber++;


    else playerNumber =1;

}


    }
}

