package org.example;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;
import java.util.Collections;


public class Board {
    public static final int NUMBEROFPIPS = 24; // The standard backgammon board has 24 points (pips)

    // Constants to reference indices in moveEntry arrays
    private static final int MOVE_INDEX = 0;
    private static final int SOURCE_PIP = 1;
    private static final int DESTINATION_PIP = 2;
    private static final int DICE_VALUE = 3;
    private static final int MOVE_TYPE = 4;

    // Each element in pips represents a single pip (point), holding a list of checkers
    private ArrayList<ArrayList<Checker>> pips;
    // Bars for each player, where knocked off checkers go before re-entry
    private ArrayList<Checker> player1Bar;
    private ArrayList<Checker> player2Bar;

    // Count of how many checkers each player has borne off
    private int player1Off;
    private int player2Off;

    // Enum for different move results
    public enum MoveType {
        LEGAL, ILLEGAL, KNOCKOUT, RE_ENTRY, KNOCKOUT_RE_ENTRY, BEAR_OFF, FORCE_BEAR_OFF
    }

    // Checker types assigned to each player
    private CheckerProperties player1Type;
    private CheckerProperties player2Type;

    public Board() {
        // Initialize variables and set up the board with starting positions
        initialiseVariables();
        setUpBoard();
    }

    public void initialiseVariables() {
        // Create the data structure for 24 pips
        pips = new ArrayList<>();
        for (int i = 0; i < NUMBEROFPIPS; i++) {
            ArrayList<Checker> pip = new ArrayList<>();
            pips.add(pip);
        }

        // Initialize bars for both players
        player1Bar = new ArrayList<>();
        player2Bar = new ArrayList<>();

        // Initially, no checkers have been borne off
        player1Off =0;
        player2Off=0;

        // Assign checker types to players
        this.player1Type = CheckerProperties.O;
        this.player2Type = CheckerProperties.X;
    }

    public ArrayList<Checker> getPlayerbar(int playerNumber){
        // Return the bar corresponding to the given player number
        if(playerNumber == 1){
            return player1Bar;
        }
        else
            return player2Bar;
    }

    public void printBar(){
        // Print all checkers currently on the bar for both players
        for(Checker checker: player1Bar){
            System.out.print(checker + ",    "  );
        }

        for(Checker checker: player2Bar){
            System.out.print(checker + ",    "  );
        }
    }

    public void addtoBar(Checker checker){
        // Add a knocked out checker to its respective player's bar
        int playerNumber = checker.getPlayerNumber();

        if(playerNumber == 1){
            player1Bar.add(checker);
        }
        else if(playerNumber == 2){
            player2Bar.add(checker);
        }
    }

    public boolean isPlayerOnBar(int playerNumber){
        // Check if the given player has any checkers on the bar
        ArrayList<Checker> bar = getPlayerbar(playerNumber);
        if (bar.isEmpty()) return false;
        Checker checker = bar.getLast();
        if(isPlayerChecker(playerNumber, checker)) return true;

        return false;
    }

    public Checker removefromBar(int playerNumber) {
        // Remove one checker from the player's bar and return it
        ArrayList<Checker> bar = getPlayerbar(playerNumber);

        if (bar.isEmpty()) {
            System.out.println("No checkers on the bar for Player " + playerNumber);
            return null;
        }

        return bar.remove(bar.size() - 1);
    }

    public ArrayList<Checker> getPip(int pipIndex) {
        // Retrieve the list of checkers on a specified pip (1-based indexing)
        if (pipIndex > 0 && pipIndex <= NUMBEROFPIPS) {
            return pips.get(pipIndex - 1);
        } else {
            return null;
        }
    }

    public Checker getChecker(int pipIndex, int checkerIndex) {
        // Get a specific checker from a pip by indices
        ArrayList<Checker> pip = getPip(pipIndex);
        if (checkerIndex >= 0 && pip != null && checkerIndex < pip.size()) {
            return pip.get(checkerIndex);
        } else {
            return null;
        }
    }

    public void addCheckertoPip(int pipIndex, Checker checker) {
        // Place a checker onto a specified pip
        getPip(pipIndex).add(checker);
    }

    public void addCheckerstoPip(String type, int pipIndex, int number) {
        // Add multiple checkers of a given type to a pip for initial setup
        for (int i = 0; i < number; i++) {
            Checker checker = new Checker(type);
            addCheckertoPip(pipIndex, checker);
        }
    }

    public Checker removeCheckerfromPip(int pipIndex) {
        // Remove the top checker from a pip
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
        // Set the initial standard backgammon configuration
        addCheckerstoPip("X", 1, 2);
        addCheckerstoPip("X", 12, 5);
        addCheckerstoPip("X", 17, 3);
        addCheckerstoPip("X", 19, 5);

        addCheckerstoPip("O", 24, 2);
        addCheckerstoPip("O", 13, 5);
        addCheckerstoPip("O", 8, 3);
        addCheckerstoPip("O", 6, 5);
    }

    public CheckerProperties getPlayerType(int playerNumber) {
        // Return the checker type (O or X) for the given player number
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
        // Check if a given checker belongs to a specified player
        CheckerProperties playerType = getPlayerType(playerNumber);
        return checker != null && checker.getType() == playerType;
    }

    public void printBoard(int playerNumber) {
        // Print the board from a given player's perspective
        int opponentPlayerNumber = (playerNumber == 1) ? 2 : 1;

        // Get bars for active player and opponent
        ArrayList<Checker> activePlayerBar = getPlayerbar(playerNumber);
        ArrayList<Checker> opponentPlayerBar = getPlayerbar(opponentPlayerNumber);

        System.out.println("Player" + playerNumber + "'s Board");
        System.out.println("13--+---+---+---+---18   BAR   19--+---+---+---+---24   Player " + opponentPlayerNumber + " OFF: " + getplayerOff(opponentPlayerNumber));

        // Top half of the board
        for (int i = 0; i < 5; i++) {

            int start = (NUMBEROFPIPS / 2 + 1); // Start at pip 13 for Player 1
            int end = NUMBEROFPIPS; // End at pip 24
            int increment = 1;

            if (playerNumber == 2) {
                start = 12;
                end = 1;
                increment = -1;
            }

            // Printing pips 13-24 or reversed for Player 2
            for (int j = start; (playerNumber == 2) ? j >= end : j <= end; j += increment) {

                // Print bar area if pip matches certain positions
                if (j == 19 || j == 6) {
                    if (!activePlayerBar.isEmpty() && i >= 0 && i < activePlayerBar.size() && activePlayerBar.get(i) != null) {
                        Checker checker = activePlayerBar.get(i);
                        System.out.print("  ");
                        System.out.print(checker.toString());
                        System.out.print(" ---");
                    } else {
                        System.out.print("    ---");
                    }
                }

                // Print checker or pipe symbol for empty pip
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

        // Bottom half of the board
        int start = NUMBEROFPIPS / 2;
        int end = 1;
        int increment = -1;

        if (playerNumber == 2) {
            start = 13;
            end = 24;
            increment = 1;
        }

        for (int i = 0; i < 5; i++) {
            for (int j = start; (playerNumber == 2) ? j <= end : j >= end; j += increment) {
                // Space for the bar on bottom half
                if (j == 6 || j == 19) {
                    if (!opponentPlayerBar.isEmpty() && i >= 0 && i < opponentPlayerBar.size() && opponentPlayerBar.get(i) != null) {
                        Checker checker = opponentPlayerBar.get(i);
                        System.out.print("  ");
                        System.out.print(checker.toString());
                        System.out.print(" ---");
                    } else {
                        System.out.print("    ---");
                    }
                }

                // Print checker or pipe symbol
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
        // Player 1 moves downwards (decreasing pip index), Player 2 moves upwards (increasing pip index)
        if (playerNumber == 1) return -1;
        if (playerNumber == 2) return 1;
        return 0;
    }

    public Checker getTopCheckerfromPip(int pipIndex) {
        // Get the topmost checker on a pip
        int size = getPip(pipIndex).size();
        Checker topChecker = getChecker(pipIndex, size - 1);
        return topChecker;
    }

    public boolean compareCheckers(Checker checker1, Checker checker2) {
        // Check if two checkers are of the same type
        if (checker1 == null || checker2 == null) {
            return false;
        }
        return checker1.getType() == checker2.getType();
    }

    public MoveType isLegalreEntry(int playerNumber, int sourceIndex, int numberofMoves){
        // Check if re-entry from the bar is legal
        int playerDirection = playerDirection(playerNumber);
        int destinationPipindex = sourceIndex + (playerDirection * numberofMoves);

        int destinationSize = getPip(destinationPipindex).size();
        Checker moveChecker = new Checker(playerNumber);
        Checker destinationTopChecker = getTopCheckerfromPip(destinationPipindex);
        boolean sameChecker = compareCheckers(destinationTopChecker, moveChecker);

        if (destinationSize == 0) {
            return MoveType.RE_ENTRY;
        }
        if (destinationSize == 1 && !sameChecker) {
            return MoveType.KNOCKOUT_RE_ENTRY;
        }
        if (destinationSize > 0 && sameChecker) {
            return MoveType.RE_ENTRY;
        }

        return MoveType.ILLEGAL;
    }

    public MoveType isLegal(int playerNumber, int sourcePipindex, int numberofMoves) {
        // Determine if a move from one pip to another is legal
        if(sourcePipindex ==0 || sourcePipindex==25){// If trying to re-enter from the bar
            return isLegalreEntry(playerNumber, sourcePipindex, numberofMoves);
        }

        int playerDirection = playerDirection(playerNumber);
        int destinationPipindex = sourcePipindex + (playerDirection * numberofMoves);

        if (destinationPipindex < 1 || destinationPipindex > NUMBEROFPIPS) {
            // Out of board range
            return MoveType.ILLEGAL;
        }

        ArrayList<Checker> sourcePip = getPip(sourcePipindex);
        if (sourcePip == null||sourcePip.isEmpty()) {
            return MoveType.ILLEGAL;
        }

        ArrayList<Checker> destinationPip = getPip(destinationPipindex);
        Checker moveChecker = getTopCheckerfromPip(sourcePipindex);

        if (moveChecker == null || !isPlayerChecker(playerNumber, moveChecker)) {
            return MoveType.ILLEGAL;
        }

        boolean rightCheckertype = isPlayerChecker(playerNumber, moveChecker);

        if (moveChecker == null || !rightCheckertype) {
            return MoveType.ILLEGAL;
        } else {
            int destinationSize = destinationPip.size();
            Checker destinationTopChecker = getTopCheckerfromPip(destinationPipindex);
            boolean sameChecker = compareCheckers(destinationTopChecker, moveChecker);

            if (destinationSize == 0) {
                return MoveType.LEGAL;
            }
            if (destinationSize == 1 && !sameChecker) {
                return MoveType.KNOCKOUT;
            }
            if (destinationSize > 0 && sameChecker) {
                return MoveType.LEGAL;
            }
        }

        return MoveType.ILLEGAL;
    }

    public void movePiptoPip(int sourceIndex, int destinationIndex){
        // Move a checker from one pip to another
        Checker checker = removeCheckerfromPip(sourceIndex);
        if (checker == null) {
            System.out.println("No checker to move");
        }
        addCheckertoPip(destinationIndex, checker);
    }

    public ArrayList<ArrayList<Object>> legalMoves(int playerNumber,  ArrayList<Integer>  rollResult) {
        // Determine all legal moves for a player given the dice roll
        ArrayList<ArrayList<Object>> legalMoves = new ArrayList<>();
        int sourceIndex;
        int destinationIndex;

        int count = 1;

        // If the player has a checker on the bar, must re-enter first
        if(isPlayerOnBar(playerNumber)) {
            if(playerNumber ==1){
                sourceIndex = 25;
            }
            else sourceIndex = 0;

            for (int moveCount : rollResult) {
                destinationIndex = sourceIndex + (moveCount * playerDirection(playerNumber));
                MoveType moveType = isLegal(playerNumber, sourceIndex, moveCount);

                if (moveType != MoveType.ILLEGAL) {
                    ArrayList<Object> moveEntry = new ArrayList<Object>(Arrays.asList(count, sourceIndex, destinationIndex, moveCount, moveType));
                    legalMoves.add(moveEntry);
                }

                count++;
            }

        } else {
            // Normal moves
            boolean doubles = false;

            if (rollResult.size()>2 && rollResult.get(0).equals(rollResult.get(1))){
                doubles = true;
            }

            for (int moveCount : rollResult) {
                sourceIndex = 1;
                while (sourceIndex < NUMBEROFPIPS + 1) {
                    MoveType moveType = isLegal(playerNumber, sourceIndex, moveCount);
                    destinationIndex = sourceIndex + (moveCount * playerDirection(playerNumber));

                    if (moveType != MoveType.ILLEGAL) {
                        ArrayList<Object> moveEntry = new ArrayList<Object>(Arrays.asList(count, sourceIndex, destinationIndex, moveCount, moveType));
                        legalMoves.add(moveEntry);
                        count++;
                    }

                    sourceIndex++;
                }
                if(doubles && !legalMoves.isEmpty() ) return legalMoves;
            }

            // If no legal standard moves, try bearing off if allowed
            if (legalMoves.isEmpty() && canBearoff(playerNumber)) {
                for (int moveCount : rollResult) {
                    for(int i= 1;i<=NUMBEROFPIPS;i++){
                        sourceIndex =i;
                        MoveType moveType = isLegalBearOff(playerNumber, sourceIndex,moveCount);
                        if(moveType == MoveType.BEAR_OFF) {
                            ArrayList<Object> moveEntry = new ArrayList<Object>(Arrays.asList(count, sourceIndex, playerNumber, moveCount, moveType));
                            legalMoves.add(moveEntry);
                            count++;
                        }
                    }
                    if(doubles) return legalMoves;
                }

                // If still empty, check forced bear off
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

        return legalMoves;
    }

    public boolean canBearoff(int playerNumber){
        // Check if a player can begin bearing off their checkers
        if (isPlayerOnBar(playerNumber)) {
            return false;
        }

        int direction = playerDirection(playerNumber);
        int startInvalidRange = (playerNumber == 1) ? 24 : 1;
        int pipIndex = startInvalidRange;
        ArrayList<Checker> pip;

        // Check all outside the home board for opposing player checkers
        for (int i = 1; i <= 18; i++) {
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

    public MoveType isLegalBearOff(int playerNumber, int sourceIndex, int moveCount){
        // Check if bearing off directly is legal (if pip number matches dice number)
        ArrayList<Checker> sourcePip = getPip(sourceIndex);

        if (sourcePip == null||sourcePip.isEmpty()) {
            return MoveType.ILLEGAL;
        }

        Checker moveChecker = getTopCheckerfromPip(sourceIndex);
        if (moveChecker == null || !isPlayerChecker(playerNumber, moveChecker)) {
            return MoveType.ILLEGAL;
        }

        int playerPipNumber = (playerNumber == 1) ? sourceIndex : (25 - sourceIndex);
        if (playerPipNumber == moveCount){
            return MoveType.BEAR_OFF;
        }

        return MoveType.ILLEGAL;
    }

    public MoveType isLegalForceBearOff(int playerNumber, int moveCount) {
        // Check if force bearing off is allowed when dice doesn't match exactly
        int highestPipIndex = getHighestOccupiedPipIndex(playerNumber);

        if (playerNumber ==2) highestPipIndex =25-highestPipIndex;

        if (highestPipIndex==-1)  return MoveType.ILLEGAL;

        if (moveCount>highestPipIndex){
            return MoveType.FORCE_BEAR_OFF;
        }

        return MoveType.ILLEGAL;
    }

    private int getHighestOccupiedPipIndex(int playerNumber) {
        // Find the highest pip occupied by the player's checker in their home board
        int pipIndex = (playerNumber == 1) ? 1 : 24;
        int direction = (playerNumber == 1) ? 1 : -1;

        int highestOccupiedPip = -1;
        ArrayList<Checker> pip;

        for (int i = 1; i <= 6; i++) {
            pip = getPip(pipIndex);
            if (!pip.isEmpty()) {
                Checker checker = getTopCheckerfromPip(pipIndex);
                if (isPlayerChecker(playerNumber, checker)) {
                    highestOccupiedPip = pipIndex;
                }
            }
            pipIndex += direction;
        }

        return highestOccupiedPip;
    }

    public void bearOffChecker(int pipIndex) {
        // Move a checker from a pip to the off board area (borne off)
        Checker checker = removeCheckerfromPip(pipIndex);

        if (checker == null) {
            System.out.println("No checker to bear off.");
            return;
        }

        if (checker.getType() == player1Type) {
            player1Off++;
        } else {
            player2Off++;
        }
    }

    public int getplayerOff(int playerNumber){
        // Get how many checkers a given player has borne off
        if (playerNumber==1){
            return player1Off;
        }

        return player2Off;
    }

    public void printLegalMoves(ArrayList<ArrayList<Object>> legalMoves, int playerNumber) {
        // Print out all legal moves for the player
        for (ArrayList<Object> move : legalMoves) {
            int printSourcePip = (int) move.get(SOURCE_PIP);
            int printDestinationPip = (int) move.get(DESTINATION_PIP);

            // Adjust pip numbering for player 2's perspective
            if (playerNumber == 2) {
                printSourcePip = 25-printSourcePip;
                printDestinationPip = 25 - printDestinationPip;
            }
            String destinationmessage = " to Pip " + printDestinationPip;
            if(printDestinationPip <1 || printDestinationPip > 24){
                destinationmessage = " to OFF";
            }

            System.out.println("Move " + move.get(MOVE_INDEX) +
                    ": From Pip " + printSourcePip +
                    destinationmessage +
                    "         Dice Value: " + move.get(DICE_VALUE) +
                    ", Move Type: " + move.get(MOVE_TYPE) );
        }
    }

    public boolean makeMove(ArrayList<ArrayList<Object>> legalMoves, int moveNumber){
        // Execute a chosen legal move
        if(moveNumber<1 || moveNumber>legalMoves.size()) {
            System.out.println("Illegal Move");
            return false;
        }

        ArrayList<Object> move = legalMoves.get(moveNumber-1);
        MoveType moveType =  (MoveType)move.get(MOVE_TYPE);
        int sourceIndex = (int) move.get(SOURCE_PIP);
        int destinationIndex = (int) move.get(DESTINATION_PIP);

        if(moveType ==MoveType.ILLEGAL){
            System.out.println("Illegal Move");
            return false;
        }

        if(moveType == MoveType.RE_ENTRY){
            moveBartoPip(sourceIndex, destinationIndex);
            System.out.println("Re-Entry Move... Successfull");
        }

        if(moveType == MoveType.KNOCKOUT_RE_ENTRY){
            Checker hitChecker = removeCheckerfromPip(destinationIndex);
            addtoBar(hitChecker);
            moveBartoPip(sourceIndex, destinationIndex);
            System.out.println("Re-Entry Move... Successfull");
        }

        if(moveType == MoveType.LEGAL){
            movePiptoPip(sourceIndex, destinationIndex);
            System.out.println("Legal Move... Successfull");
        }

        if(moveType ==MoveType.KNOCKOUT){
            Checker hitChecker = removeCheckerfromPip(destinationIndex);
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

    public void moveBartoPip(int sourceIndex, int destinationIndex){
        // Move a checker from the bar back onto a pip
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

    public void takeTurn(int playerNumber,  ArrayList<Integer>  diceRoll){
        // Handle a full turn for the given player with the provided dice roll
        ArrayList<ArrayList<Object>> legalMoves = legalMoves(playerNumber, diceRoll);
        System.out.println("\nSelect Move: " );
        printLegalMoves(legalMoves, playerNumber);

        if (legalMoves.isEmpty()) {
            System.out.println("No legal moves available for Player " + playerNumber + ". Skipping turn.");
            diceRoll.clear();
            return;
        }

        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a valid number.");
            return;
        }
        int moveNumber = scanner.nextInt();

        boolean moveSuccess = makeMove(legalMoves, moveNumber);
        if(moveSuccess) {
            int diceValue = (int) legalMoves.get(moveNumber - 1).get(DICE_VALUE);
            diceRoll.remove(Integer.valueOf(diceValue));
        } else {
            System.out.println("Move Failed");
        }
    }

}
