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

    // Class variables for managing board state
    private ArrayList<ArrayList<Checker>> pips;
    private ArrayList<Checker> player1Bar;
    private ArrayList<Checker> player2Bar;

    private int player1Off;
    private int player2Off;

    public enum MoveType {
        LEGAL, ILLEGAL, KNOCKOUT, RE_ENTRY, KNOCKOUT_RE_ENTRY, BEAR_OFF, FORCE_BEAR_OFF
    }

    //Player Checker Types
    private CheckerProperties player1Type;
    private CheckerProperties player2Type;


    // Constructor to initialize board and set up pieces
    public Board() {
        initialiseVariables();
        setUpBoard();
    }

    // Initializes pips, bars, and player checker types
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

    // Returns the bar for a player (1 or 2)
    public ArrayList<Checker> getPlayerbar(int playerNumber){
        if(playerNumber == 1){
            return player1Bar;
        }
        else return player2Bar;
    }

    // Prints all checkers on both bars
    public void printBar(){

        for(Checker checker: player1Bar){
            System.out.print(checker + ",    "  );
        }

        for(Checker checker: player2Bar){
            System.out.print(checker + ",    "  );
        }
    }


    // Adds a checker to a player's bar
    public void addtoBar(Checker checker){
        int playerNumber = checker.getPlayerNumber();

        if(playerNumber == 1){
            player1Bar.add(checker);
        }
        else if(playerNumber == 2){
            player2Bar.add(checker);
        }
    }

    // Checks if a player has checkers on their bar
    public boolean isPlayerOnBar(int playerNumber){
        ArrayList<Checker> bar = getPlayerbar(playerNumber);
        if (bar.isEmpty()) return false;
        Checker checker = bar.getLast();
        if(isPlayerChecker(playerNumber, checker)) return true;

        return false;
    }

    // Removes and returns the top checker from a player's bar
    public Checker removefromBar(int playerNumber) {
        ArrayList<Checker> bar = getPlayerbar(playerNumber);

        if (bar.isEmpty()) {
            System.out.println("No checkers on the bar for Player " + playerNumber);
            return null;
        }

        // Remove and return the top checker
        return bar.remove(bar.size() - 1);
    }


    // Returns the pip at the given index
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


    // Adds a checker to a specific pip
    public void addCheckertoPip(int pipIndex, Checker checker) {
        getPip(pipIndex).add(checker);
    }

    // Adds multiple checkers to a pip
    public void addCheckerstoPip(String type, int pipIndex, int number) {

        for (int i = 0; i < number; i++) {
            Checker checker = new Checker(type);
            addCheckertoPip(pipIndex, checker);
        }


    }


    // Removes and returns the top checker from a pip
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

    // Sets up the board with initial checkers
    public void setUpBoard() {
        addCheckerstoPip("X", 1, 2);
        addCheckerstoPip("X", 12, 5);
        addCheckerstoPip("X", 17, 3);
        addCheckerstoPip("X", 19, 5);

        addCheckerstoPip("O", 24, 2);
        addCheckerstoPip("O", 13, 5);
        addCheckerstoPip("O", 8, 3);
        addCheckerstoPip("O", 6, 5);
    }


    // Returns the type of a player (1 or 2)
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

    // Checks if a checker belongs to a player
    public boolean isPlayerChecker(int playerNumber, Checker checker) {
        CheckerProperties playerType = getPlayerType(playerNumber);
        return checker != null && checker.getType() == playerType;
    }


    // Prints the current board state
    public void printBoard(int playerNumber) {

        int opponentPlayerNumber = (playerNumber == 1) ? 2 : 1;

        ArrayList<Checker> activePlayerBar = getPlayerbar(playerNumber);
        ArrayList<Checker> opponentPlayerBar = getPlayerbar(opponentPlayerNumber);

        String playerType = getPlayerType(playerNumber).name();

        System.out.println("Player" + playerNumber + "'s Board" + "("+ playerType +")");
        System.out.println("13--+---+---+---+---18   BAR   19--+---+---+---+---24   Player " + opponentPlayerNumber + " OFF: " + getplayerOff(opponentPlayerNumber));

        for (int i = 0; i < 5; i++) {

            int start = (NUMBEROFPIPS / 2 + 1);     // Start at pip 13
            int end = NUMBEROFPIPS;                 // End at pip 24
            int increment = 1;

            if (playerNumber == 2) {
                start = 12;
                end = 1;
                increment = -1;
            }


            for (int j = start; (playerNumber == 2) ? j >= end : j <= end; j += increment) { // Print pips 13-24

                if (j == 19 || j == 6) {  // space for bar

                    if (!activePlayerBar.isEmpty() && i >= 0 && i < activePlayerBar.size() && activePlayerBar.get(i) != null) {

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


    // Determines the direction of movement for a player
    public int playerDirection(int playerNumber) {
        if (playerNumber == 1) return -1;

        if (playerNumber == 2) {
            return 1;
        }
        return 0;
    }

    // Returns the top checker from a pip
    public Checker getTopCheckerfromPip(int pipIndex) {
        int size = getPip(pipIndex).size();
        Checker topChecker = getChecker(pipIndex, size - 1);

        return topChecker;
    }

    // Compares two checkers to see if they belong to the same player
    public boolean compareCheckers(Checker checker1, Checker checker2) {
        if (checker1 == null || checker2 == null) {
            return false;
        }

        return checker1.getType() == checker2.getType();
    }


    // Determines if re-entry is legal for a player
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

        //if same type of checker
        if (destinationSize > 0 && sameChecker) {
            return MoveType.RE_ENTRY;
        }

        return MoveType.ILLEGAL;

    }


    // Checks if a move is legal
    public MoveType isLegal(int playerNumber, int sourcePipindex, int numberofMoves) {

        if(sourcePipindex ==0 || sourcePipindex==25){// Check if reentry is legal
            return isLegalreEntry(playerNumber, sourcePipindex, numberofMoves);
        }

        int playerDirection = playerDirection(playerNumber);
        int destinationPipindex = sourcePipindex + (playerDirection * numberofMoves);


        if (destinationPipindex < 1 || destinationPipindex > NUMBEROFPIPS) { // accounts for bearing off too      ono add something for beargn off
            return MoveType.ILLEGAL;
        }


        ArrayList<Checker> sourcePip = getPip(sourcePipindex);


        if (sourcePip == null||sourcePip.isEmpty()) {
            return MoveType.ILLEGAL;
        }

        ArrayList<Checker> destinationPip = getPip(destinationPipindex);
        Checker moveChecker = getTopCheckerfromPip(sourcePipindex);

        // Validate checker ownership
        if (moveChecker == null || !isPlayerChecker(playerNumber, moveChecker)) {
            return MoveType.ILLEGAL;
        }

        boolean rightCheckertype = isPlayerChecker(playerNumber, moveChecker);

        //if no checker or not player's checker on the spot
        if (moveChecker == null || !rightCheckertype) {
            System.out.println("moveChecker==null || !rightCheckertype");
            return MoveType.ILLEGAL;
        }

        // If move checker is player's
        else {

            int destinationSize = destinationPip.size();

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

    // Moves a checker from one pip to another
    public void movePiptoPip(int sourceIndex, int destinationIndex){

        Checker checker = removeCheckerfromPip(sourceIndex);
        if (checker == null) {
            System.out.println("No checker to move");
        }
        addCheckertoPip(destinationIndex, checker);
    }


    // Generates all legal moves for a player
    public ArrayList<ArrayList<Object>> legalMoves(int playerNumber,  ArrayList<Integer>  rollResult) {

        ArrayList<ArrayList<Object>> legalMoves = new ArrayList<>();
        int sourceIndex;
        int destinationIndex;

        // Logic to generate moves based on dice rolls
        boolean doubles = false;
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

            if (rollResult.size()>2 && rollResult.get(0).equals(rollResult.get(1))){
                doubles = true;
            }
            // go through dice
            for (int moveCount : rollResult) {
                sourceIndex = 1; // loop from 1 - 24 ono

                while (sourceIndex < NUMBEROFPIPS + 1) {    //go through checkers

                    MoveType moveType = isLegal(playerNumber, sourceIndex, moveCount);
                    destinationIndex = sourceIndex + (moveCount * playerDirection(playerNumber));

                    if (moveType != MoveType.ILLEGAL) { //if (moveType == MoveType.LEGAL || moveType == MoveType.KNOCKOUT) {

                        ArrayList<Object> moveEntry = new ArrayList<Object>(Arrays.asList(count, sourceIndex, destinationIndex, moveCount, moveType));
                        legalMoves.add(moveEntry);
                        count++;

                    }

                    sourceIndex++;
                }
                if(doubles) return legalMoves; // dont go through dice again since they are all the same
            }

            // Player can only Bear - off if no more legal moves allowed and all checkers are in homeboard
            // if legal moves empty then you can begin bear off
            if (legalMoves.isEmpty() && canBearoff(playerNumber)) {

                for (int moveCount : rollResult) {// loop through dice
                    //Loop through pips
                    for(int i= 1;i<=NUMBEROFPIPS;i++){
                        sourceIndex =i;

                        MoveType moveType = isLegalBearOff(playerNumber, sourceIndex,moveCount);
                        if(moveType == MoveType.BEAR_OFF) {

                            ArrayList<Object> moveEntry = new ArrayList<Object>(Arrays.asList(count, sourceIndex, playerNumber, moveCount, moveType));
                            legalMoves.add(moveEntry);
                            count++;

                        }
                    }

                    if(doubles) return legalMoves; // Dont got through dice again since its all the same

                }

                // If still empty then for force bear off with the highest dice if possible
                if (legalMoves.isEmpty() && canBearoff(playerNumber)) {

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

        // Once Legal moves has been created, Check if move limits the highest dice from being used
        if(!doubles) {
            int maxDice = Collections.max(rollResult);
            int maxDicecount = 0;
            int maxDiceSourcePipIndex = 0;

            //Check if highest dice only used once
            for (ArrayList <Object> move:legalMoves) {

                //If yes then find lower dice move that's from same sourcepip and remove it from legalmoves
                int diceValue = (int) move.get(DICE_VALUE);
                if (diceValue == maxDice) {
                    maxDiceSourcePipIndex = (int) move.get(SOURCE_PIP);
                    maxDicecount++;
                }
            }

            if (maxDicecount == 1) {
                ArrayList<ArrayList<Object>> movesToRemove = new ArrayList<>();
                //Find lower dice move that's from same sourcepip and remove it from legalmoves, only if its from another dice too
                for (ArrayList <Object> move:legalMoves) {
                    int sourcePipIndex = (int)move.get(SOURCE_PIP);
                    int diceValue = (int)move.get(DICE_VALUE);
                    if (sourcePipIndex == maxDiceSourcePipIndex && diceValue != maxDice) {
                        movesToRemove.add(move);
                    }
                }
                legalMoves.removeAll(movesToRemove);
            }
        }

        return legalMoves;

    }

    // Checks if a player can bear off
    public boolean canBearoff(int playerNumber){

        // Validates  if all of player's checkers are on home board and that no checkers are on bar
        if (isPlayerOnBar(playerNumber)) {  //player cannot bear off if they are on Bar
            return false;
        }

        int direction = playerDirection(playerNumber);

        int startInvalidRange = (playerNumber == 1) ? 24 : 1;   // Player 1 starts at pip 7
        int pipIndex = startInvalidRange;
        ArrayList<Checker> pip;

           for (int i = 1; i <= 18; i++) {
            //checks player's non home board pips

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


    // Checks if a direct bear off is legal
    public MoveType isLegalBearOff(int playerNumber, int sourceIndex, int moveCount){   //for direct bear off (if pip number = dice number)

        ArrayList<Checker> sourcePip = getPip(sourceIndex);

        if (sourcePip == null||sourcePip.isEmpty()) {
            return MoveType.ILLEGAL;
        }

        // Validate checker ownership
        Checker moveChecker = getTopCheckerfromPip(sourceIndex);

        if (moveChecker == null || !isPlayerChecker(playerNumber, moveChecker)) {
            return MoveType.ILLEGAL;
        }

        // For each player source is different
        int playerPipNumber = (playerNumber == 1) ? sourceIndex : (25 - sourceIndex);
        if (playerPipNumber == moveCount){
            return MoveType.BEAR_OFF;
        }

        return MoveType.ILLEGAL;
    }
    // Checks if a forced bear off is legal (max dice is passed in
    public MoveType isLegalForceBearOff(int playerNumber, int moveCount) {

        int highestPipIndex = getHighestOccupiedPipIndex(playerNumber);

        if (playerNumber ==2) highestPipIndex =25-highestPipIndex;      //to convert to check

        if (highestPipIndex==-1)  return MoveType.ILLEGAL;

        if (moveCount>highestPipIndex){
            return MoveType.FORCE_BEAR_OFF;
        }

        return MoveType.ILLEGAL;

    }

    // Returns the highest occupied pip index in the home board
    private int getHighestOccupiedPipIndex(int playerNumber) {
        // p1 home: 1- 6 p2 home: 24 -19
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

    // Bears off a checker from the board
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

    // Returns the number of checkers a player has borne off
    public int getplayerOff(int playerNumber){

        if (playerNumber==1){
            return player1Off;
        }

        return player2Off;

    }

    // Prints all legal moves for a player
    public void printLegalMoves(ArrayList<ArrayList<Object>> legalMoves, int playerNumber) {
        // System.out.println("Legal Moves:");
        int moveNumber = 0;
        for (ArrayList<Object> move : legalMoves) {

            moveNumber++;
            //print source and destination for player 2
            int printSourcePip = (int) move.get(SOURCE_PIP);
            int printDestinationPip = (int) move.get(DESTINATION_PIP);

            if (playerNumber == 2) {

                printSourcePip = 25-printSourcePip;
                printDestinationPip = 25 - printDestinationPip;
            }
            /*
            if (printDestinationPip<1||printDestinationPip>24){
                printDestinationPip
            }
*/
            System.out.println("Move " + moveNumber +
                    ": From Pip " + printSourcePip +
                    " to Pip " + printDestinationPip +
                    "         Dice Value: " + move.get(DICE_VALUE) +
                    ", Move Type: " + move.get(MOVE_TYPE) );

        }
    }

    // Executes a move if it's valid
    public boolean makeMove(ArrayList<ArrayList<Object>> legalMoves, int moveNumber){

        if(moveNumber<1 || moveNumber>legalMoves.size()) {
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

            bearOffChecker(sourceIndex);
            System.out.println("Bear Off... Successfull");

        }

        return true;
    }

    // Moves a checker from a bar to a pip
    public void moveBartoPip(int sourceIndex, int destinationIndex){
        int playerNumber;

        if(sourceIndex ==25) {
            playerNumber =  1;
        }
        else if (sourceIndex ==0) {
            playerNumber =  2;
        }

        else return;

        Checker checker = removefromBar(playerNumber);

        if (checker == null) {
            System.out.println("No checker to move");
        }
        addCheckertoPip(destinationIndex, checker);
    }

    // Handles a player's turn
    public void takeTurn(int playerNumber,  ArrayList<Integer>  diceRoll){//ono what do I add?

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

        boolean moveSuccess = makeMove(legalMoves, moveNumber);
        // remove dice
        if(moveSuccess) {
            int diceValue = (int) legalMoves.get(moveNumber - 1).get(DICE_VALUE);
            diceRoll.remove(Integer.valueOf(diceValue));
        }

        else System.out.println("Move Failed");
    }

}