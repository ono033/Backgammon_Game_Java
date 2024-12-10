package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {


    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board();
    }



    @Test
    void testConstructor() {

        // Verify the Board object is created and initialized correctly
        assertNotNull(board, "Board object should not be null");
        assertTrue(board.getPlayerbar(1).isEmpty(), "Player 1's bar should be empty at the start");
        assertTrue(board.getPlayerbar(2).isEmpty(), "Player 2's bar should be empty at the start");

    }


    @Test
    void testInitialiseVariables() {
        // Test that all variables are initialized correctly
        board.initialiseVariables();

        assertNotNull(board.getPlayerbar(1), "Player 1's bar should not be null after initialization");
        assertNotNull(board.getPlayerbar(2), "Player 2's bar should not be null after initialization");
    }



    @Test
    void getPlayerbar() {
        // The bar is empty initially
        assertTrue(board.getPlayerbar(1).isEmpty(), "Player 1's bar should be empty initially");
        assertTrue(board.getPlayerbar(2).isEmpty(), "Player 2's bar should be empty initially");

    }

    @Test
    void printBar() {
        board.addtoBar(new Checker("O"));
        board.addtoBar(new Checker("X"));
        assertDoesNotThrow(() -> board.printBar(), "Printing the bar should not throw exceptions");

    }

    @Test
    void addtoBar() {
        Checker checker = new Checker("O");
        board.addtoBar(checker);
        assertEquals(1, board.getPlayerbar(1).size(), "Player 1's bar should have one checker after adding");
        assertEquals(checker, board.getPlayerbar(1).get(0), "The checker on the bar should be the one that was added");
    }

    @Test
    void isPlayerOnBar() {
        Checker checker = new Checker("O");
        board.addtoBar(checker);
        assertTrue(board.isPlayerOnBar(1), "Player 1 should be on the bar after adding a checker");
        assertFalse(board.isPlayerOnBar(2), "Player 2 should not be on the bar without adding a checker");

    }

    @Test
    void removefromBar() {
        board.addtoBar(new Checker("X"));
        assertNotNull(board.removefromBar(2), "Removing checker from player 2's bar should be successful");
        assertTrue(board.getPlayerbar(2).isEmpty(), "Player 2's bar should be empty after removing the checker");

    }



    @Test
    void getPip() {

        board.addCheckerstoPip("X",1,1);
        assertNotNull(board.getPip(1), "Pip 1 should not be null");

        assertTrue(board.getPip(25) == null, "Pip 25 should be out of bounds and return null");
    }

    @Test
    void getChecker() {
        board.addCheckerstoPip("X",1,1);
        assertNotNull(board.getPip(1), "Pip 1  not be null");
        assertTrue(board.getPip(25) == null, "Pip 25 should be out of bounds and return null");
    }

    @Test
    void addCheckertoPip() {
        Checker checker = new Checker("X");
        board.addCheckertoPip(5, checker);
        assertEquals(checker, board.getPip(5).get(0), "Checker should be added to pip 5");

    }

    @Test
    void addCheckerstoPip() {
        Checker checker = new Checker("X");
        board.addCheckertoPip(5, checker);
        assertEquals(checker, board.getPip(5).get(0), "Checker should be added to pip 5");

    }

    @Test
    void removeCheckerfromPip() {
        board.addCheckertoPip(5, new Checker("X"));
        assertNotNull(board.removeCheckerfromPip(5), "Should successfully remove a checker from pip 5");
        assertTrue(board.getPip(5).isEmpty(), "Pip 5 should be empty after removing the checker");

    }

    @Test
    void setUpBoard() {
        // Board is already set up by @beforeeach
        assertEquals(2, board.getPip(24).size(), "Player 1 initial setup at pip 24 should be correct");
        assertEquals(5, board.getPip(6).size(), "Player 2 initial setup at pip 6 should be correct");

    }

    @Test
    void getPlayerType() {
        assertEquals(CheckerProperties.O, board.getPlayerType(1), "Player 1 should have type O");
        assertEquals(CheckerProperties.X, board.getPlayerType(2), "Player 2 should have type X");

    }

    @Test
    void isPlayerChecker() {
        Checker checker = new Checker("O");
        assertTrue(board.isPlayerChecker(1, checker), "Checker should belong to player 1");

    }

    @Test
    void printBoard() {
        // Board is already set up by @beforeeach
        assertDoesNotThrow(() -> board.printBoard(1), "Printing the board for Player 1 should not throw exceptions");
        assertDoesNotThrow(() -> board.printBoard(2), "Printing the board for Player 2 should not throw exceptions");

    }

    @Test
    void playerDirection() {
        assertEquals(-1, board.playerDirection(1), "Player 1 direction should be -1");
        assertEquals(1, board.playerDirection(2), "Player 2 direction should be 1");
    }

    @Test
    void getTopCheckerfromPip() {
        Checker checker = new Checker("O");
        board.addCheckertoPip(1, checker);
        Checker topChecker = board.getTopCheckerfromPip(1);
        assertEquals(checker.getType(), topChecker.getType(), "Top checker from pip 1 should be the last one added");

    }

    @Test
    void compareCheckers() {
        Checker checker1 = new Checker("O");
        Checker checker2 = new Checker("X");
        Checker checker3 = new Checker("O");

        assertTrue(board.compareCheckers(checker1, checker3), "Checkers of the same type should be equal");
        assertFalse(board.compareCheckers(checker1, checker2), "Checkers of different types should not be equal");

    }

    @Test
    void isLegalreEntry() {

        board.addtoBar(new Checker("O"));
        assertEquals(Board.MoveType.RE_ENTRY, board.isLegalreEntry(1, 25, 1), "Re-entry move should be legal for Player 1");
        board.addtoBar(new Checker("X"));
        assertEquals(Board.MoveType.RE_ENTRY, board.isLegalreEntry(2, 0, 1), "Re-entry move should be legal for Player 2");

    }

    @Test
    void isLegal() {
        board.addCheckertoPip(3, new Checker("O"));
        assertEquals(Board.MoveType.LEGAL, board.isLegal(1, 3, 1), "Move should be legal");

    }

    @Test
    void movePiptoPip() {
        board.addCheckertoPip(2, new Checker("X"));
        board.movePiptoPip(2, 3);
        assertTrue(board.getPip(2).isEmpty(), "Pip 1 should be empty after the move");
        assertFalse(board.getPip(3).isEmpty(), "Pip 3 should contain a checker after the move");

    }

    @Test
    void legalMoves() {
        board.addCheckertoPip(1, new Checker("O"));
        ArrayList<Integer> diceRoll = new ArrayList<>(Arrays.asList(1, 2));
        assertFalse(board.legalMoves(1, diceRoll).isEmpty(), "Should have legal moves available");

    }

    @Test
    void canBearoff() {

        board.addCheckertoPip(24, new Checker("X"));
        board.bearOffChecker(24);
        assertEquals(1, board.getplayerOff(2), "Player 2 should have 1 checker off the board");

    }

    @Test
    void isLegalBearOff() {
        board.addCheckertoPip(6, new Checker("O"));
        assertEquals(Board.MoveType.BEAR_OFF, board.isLegalBearOff(1, 6, 6), "Direct bear off should be legal when dice matches pip number");
        assertEquals(Board.MoveType.ILLEGAL, board.isLegalBearOff(1, 6, 5), "Bear off should be illegal if dice does not match pip number");

    }

    @Test
    void isLegalForceBearOff() {
        board.addCheckertoPip(6, new Checker("O"));
        assertEquals(Board.MoveType.FORCE_BEAR_OFF, board.isLegalForceBearOff(1, 7), "Force bear off should be legal when dice value is higher than any occupied pip");
        assertEquals(Board.MoveType.ILLEGAL, board.isLegalForceBearOff(1, 5), "Force bear off should be illegal if dice value is lower than highest occupied pip");

    }

    @Test
    void bearOffChecker() {

        board.addCheckertoPip(6, new Checker("O"));
        board.bearOffChecker(6);
        assertEquals(1, board.getplayerOff(1), "Player 1 should have one checker off after bearing off");

    }

    @Test
    void getplayerOff() {

        board.addCheckertoPip(6, new Checker("O"));
        board.bearOffChecker(6);
        assertEquals(1, board.getplayerOff(1), "Player 1 off count should match the checkers beared off");
        assertEquals(0, board.getplayerOff(2), "Player 2 off count should be zero without bear off");

    }

    @Test
    void printLegalMoves() {

        board.addCheckertoPip(6, new Checker("O"));
        ArrayList<Integer> diceRoll = new ArrayList<>(Arrays.asList(1));
        ArrayList<ArrayList<Object>> legalMoves = board.legalMoves(1, diceRoll);
        assertDoesNotThrow(() -> board.printLegalMoves(legalMoves, 1), "Printing legal moves should not throw exceptions");

    }

    @Test
    void makeMove() {
        board.addCheckertoPip(1, new Checker("O"));
        ArrayList<Integer> diceRoll = new ArrayList<>(Arrays.asList(1));
        ArrayList<ArrayList<Object>> legalMoves = board.legalMoves(1, diceRoll);
        assertTrue(board.makeMove(legalMoves, 1), "Making a legal move should return true");
        assertFalse(board.getPip(1).isEmpty(), "Pip should not be empty after a legal move");

    }

    @Test
    void moveBartoPip() {

        board.addtoBar(new Checker("O"));
        assertDoesNotThrow(() -> board.moveBartoPip(25, 1), "Moving checker from bar to pip should not throw exception");

    }

    @Test
    void takeTurn() {


        // Set up the board with a checker and a valid dice roll
        board.addCheckertoPip(1, new Checker("O"));
        ArrayList<Integer> diceRoll = new ArrayList<>(Arrays.asList(1));

        // Simulate user input for selecting a move
        String simulatedInput = "1\n"; // Simulates typing "1" and pressing Enter
        InputStream originalIn = System.in;
        ByteArrayInputStream simulatedInputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(simulatedInputStream);

        // Perform the test
        assertDoesNotThrow(() -> board.takeTurn(1, diceRoll), "Taking a turn should not throw exception");

        // Restore the original System.in
        System.setIn(originalIn);


    }

}