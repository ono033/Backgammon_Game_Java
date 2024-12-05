import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;
import java.util.Collections;

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

    private int player1Off;
    private int player2Off;

    public enum MoveType {
        LEGAL, ILLEGAL, KNOCKOUT, RE_ENTRY, KNOCKOUT_RE_ENTRY, BEAR_OFF, FORCE_BEAR_OFF
    }

    private CheckerProperties player1Type;
    private CheckerProperties player2Type;

    public Board() {
// initialise

        initialiseVariables();
         setUpBoard();

    }


    public void initialiseVariables() {
        pips = new ArrayList<>();

        for (int i = 0; i < NUMBEROFPIPS; i++) { // assuming a standard backgammon board with 24 pips
            ArrayList<Checker> pip = new ArrayList<>();
            pips.add(pip);
        }

        player1Bar = new ArrayList<>();
        player2Bar = new ArrayList<>();

        player1Off =0;
        player2Off=0;

        this.player1Type = CheckerProperties.O;
        this.player2Type = CheckerProperties.X;
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
        System.out.println("13--+---+---+---+---18   BAR   19--+---+---+---+---24   Player " + opponentPlayerNumber + " OFF: " + getplayerOff(opponentPlayerNumber));

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
        System.out.println("12--+---+---+---+---07   BAR   06--+---+---+---+---01   Player " + playerNumber + " OFF: " + getplayerOff(playerNumber));

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
                boolean doubles = false;

            if (rollResult.size()>2 && rollResult.get(0).equals(rollResult.get(1))){
                   doubles = true;
            }

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
                if(doubles) return legalMoves; // dont got through dice again since its all the same
            }

            // Player can only Bear - off if no more legal moves allowed and all checkers are in homeboard

            // if legal moves empty then you can begin bear off
            if (legalMoves.isEmpty() && canBearoff(playerNumber)) {

                // destination?
                // for regular bear off loop through pips and dice to check if can bear off
                // loop through dice
                //loop through pips/*

                for (int moveCount : rollResult) {// loop through dice
                    //loop through pips
                    for(int i= 1;i<=NUMBEROFPIPS;i++){
                        sourceIndex =i;

                       MoveType moveType = isLegalBearOff(playerNumber, sourceIndex,moveCount);
                       if(moveType == MoveType.BEAR_OFF) {

                           ArrayList<Object> moveEntry = new ArrayList<Object>(Arrays.asList(count, sourceIndex, playerNumber, moveCount, moveType));
                           legalMoves.add(moveEntry);
                           count++;

                       }
                    }

                    // ono check the playernumber and movecount
                    if(doubles) return legalMoves; // dont got through dice again since its all the same

                }




                    if (legalMoves.isEmpty() && canBearoff(playerNumber)) {
                        // if still empty then for force bear off pass in highest dice
                        //loop thru pips not dice
                        int maxDice = Collections.max(rollResult);
                        int highestPip = getHighestOccupiedPipIndex(playerNumber);
                        MoveType moveType = isLegalForceBearOff(playerNumber, maxDice);

                        if (moveType ==MoveType.FORCE_BEAR_OFF) {
                            destinationIndex = -1;
                            ArrayList<Object> moveEntry = new ArrayList<Object>(Arrays.asList(count, highestPip, destinationIndex, maxDice, moveType));

                            legalMoves.add(moveEntry);

                            count++;

                        }

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
    


    public MoveType isLegalBearOff(int playerNumber, int sourceIndex, int moveCount){   //for direct bear off (if pip number = dice number)

        // should this take in roll result and just go thru all or no.. return bearoff or forcebearoff
      //  should be called when looping through pips
        // if pip has checker and is player checker
        ArrayList<Checker> sourcePip = getPip(sourceIndex);

        if (sourcePip == null||sourcePip.isEmpty()) {
            // System.out.println("Source pip is empty or null."); ono remove
            return MoveType.ILLEGAL;
        }

        // Validate checker ownership
        Checker moveChecker = getTopCheckerfromPip(sourceIndex);

        if (moveChecker == null || !isPlayerChecker(playerNumber, moveChecker)) {
            //  System.out.println("No checker to move or checker does not belong to player."); ono remove
            return MoveType.ILLEGAL;
        }

        // For each player source is different
        int playerPipNumber = (playerNumber == 1) ? sourceIndex : (25 - sourceIndex);
        if (playerPipNumber == moveCount){
            return MoveType.BEAR_OFF;
        }

        return MoveType.ILLEGAL;
    }

    public MoveType isLegalForceBearOff(int playerNumber, int moveCount) {
        //pass in largest dice
        // must pass in largest pip number and then check if over the dice roll... if 2 dice?
// int max = Collections.max(diceRoll)
        //if if over highest pip then force off
        //ono add checks

        int highestPipIndex = getHighestOccupiedPipIndex(playerNumber);

        if (playerNumber ==2) highestPipIndex =25-highestPipIndex;      //to convert to check

        if (highestPipIndex==-1)  return MoveType.ILLEGAL;

        if (moveCount>highestPipIndex){
            return MoveType.FORCE_BEAR_OFF;
        }


        return MoveType.ILLEGAL;


    }

    private int getHighestOccupiedPipIndex(int playerNumber) {
        // p1 home: 1- 6 p2 : 24 -19
        int pipIndex = (playerNumber == 1) ? 1 : 24;
        int direction = (playerNumber == 1) ? 1 : -1;

        int highestOccupiedPip = -1;
        ArrayList<Checker> pip;

        for (int i = 1; i <= 6; i++) { // Loop through 6 pips in the home board
             pip = getPip(pipIndex);

            if (!pip.isEmpty()) {
                Checker checker = getTopCheckerfromPip(pipIndex); // Get the top checker
                if (isPlayerChecker(playerNumber, checker)) {
                    highestOccupiedPip = pipIndex; // Update the highest occupied pip
                }
            }

            pipIndex += direction; // Move to the next pip based on direction
        }

        return highestOccupiedPip;
    }







  /*  public void bearOffChecker(int pipIndex, int playerNumber){
        // function that use this must perform necessary checks so this can be used without checks

        // remove top checker from pip
        removeCheckerfromPip(pipIndex);

        // add to player off

        if(playerNumber==1){
            player1Off++;
        }
        else {
        player2Off++;
        }


    } */

    public void bearOffChecker(int pipIndex) {
        // Remove the top checker from the pip
        Checker checker = removeCheckerfromPip(pipIndex);

        if (checker == null) {
            System.out.println("No checker to bear off.");
            return;
        }

        // Determine the player based on the checker's type and increment the respective counter
        if (checker.getType() == player1Type) {
            player1Off++;
        } else  {
            player2Off++;
        }
    }


    public int getplayerOff(int playerNumber){

        if (playerNumber==1){
            return player1Off;
        }

            return player2Off;

}


    public void printLegalMoves(ArrayList<ArrayList<Object>> legalMoves, int playerNumber) {
      // System.out.println("Legal Moves:");
        for (ArrayList<Object> move : legalMoves) {
          //  System.out.println("Move " + move.get(0) + ": From Pip " + move.get(1) + " to Pip " + move.get(2) + ", Dice Value: " + move.get(3) + ", Move Type: " + move.get(4));
         //   System.out.println(move);
           // if(canBearoff(playerDirection()))

            //print source and destination for player 2
            int printSourcePip = (int) move.get(SOURCE_PIP);
            int printDestinationPip = (int) move.get(DESTINATION_PIP);

            if (playerNumber == 2) {

                printSourcePip = 25-printSourcePip;
                printDestinationPip = 25 - printDestinationPip;
            }

            System.out.println("Move " + move.get(MOVE_INDEX) +
                    ": From Pip " + printSourcePip +
                    " to Pip " + printDestinationPip +
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


    if(moveType == MoveType.RE_ENTRY){
        moveBartoPip(sourceIndex, destinationIndex);            //also removes from bar
        System.out.println("Re-Entry Move... Successfull");

    }

    if(moveType == MoveType.KNOCKOUT_RE_ENTRY){
        Checker hitChecker = removeCheckerfromPip(destinationIndex); // removes opposite
        addtoBar(hitChecker);

        moveBartoPip(sourceIndex, destinationIndex);            //also removes from bar
        System.out.println("Re-Entry Move... Successfull");

    }

        if(moveType == MoveType.LEGAL){
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

    if(moveType == MoveType.BEAR_OFF || moveType == MoveType.FORCE_BEAR_OFF){
    // int playerNumber = get
        bearOffChecker(sourceIndex);
        System.out.println("Bear Off... Successfull");

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
    printLegalMoves(legalMoves, playerNumber);

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

    board.addCheckerstoPip("O", 1, 1); // Player 1: Checker on pip 1
    board.addCheckerstoPip("O", 3, 4); // Player 1: Checker on pip 3
    board.addCheckerstoPip("O", 5, 5); // Player 1: Checker on pip 5 (highest for Player 1)
    board.addCheckerstoPip("O", 6, 1);


    board.addCheckerstoPip("X", 24, 1); // Player 2: Checker on pip 24
    board.addCheckerstoPip("X", 22, 4); // Player 2: Checker on pip 22
    board.addCheckerstoPip("X", 20, 4); // Player 2: Checker on pip 20 (highest for Player 2)


    int playerNumber = 1;
while(true) {

   // ArrayList<Integer> rollResult = new ArrayList<>(Arrays.asList(1, 2, 6));
    ArrayList<Integer> rollresult = Commands.Roll();
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




/*

    Board board = new Board();

    // Example setup for Player 1 and Player 2
   board.addCheckerstoPip("O", 1, 1); // Player 1: Checker on pip 1
    board.addCheckerstoPip("O", 3, 1); // Player 1: Checker on pip 3
    board.addCheckerstoPip("O", 5, 5); // Player 1: Checker on pip 5 (highest for Player 1)
   board.addCheckerstoPip("O", 6, 1);


    board.addCheckerstoPip("X", 24, 1); // Player 2: Checker on pip 24
    board.addCheckerstoPip("X", 22, 1); // Player 2: Checker on pip 22
    board.addCheckerstoPip("X", 20, 1); // Player 2: Checker on pip 20 (highest for Player 2)

    // Testing getHighestOccupiedPipIndex
    int highestPipPlayer1 = board.getHighestOccupiedPipIndex(1); // Should return 5
    int highestPipPlayer2 = board.getHighestOccupiedPipIndex(2); // Should return 20

    System.out.println("Highest occupied pip for Player 1: " + highestPipPlayer1);
    System.out.println("Highest occupied pip for Player 2: " + highestPipPlayer2);

    // Additional edge case testing
    // Clear Player 1's home board and test
    board.removeCheckerfromPip(1);
    board.removeCheckerfromPip(3);
    board.removeCheckerfromPip(5);

    highestPipPlayer1 = board.getHighestOccupiedPipIndex(1); // Should return -1
    System.out.println("Highest occupied pip for Player 1 (after clearing): " + highestPipPlayer1);
*/


    }

//GameOver Feature Made By Seun


}

