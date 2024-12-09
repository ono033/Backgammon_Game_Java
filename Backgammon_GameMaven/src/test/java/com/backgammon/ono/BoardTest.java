package com.backgammon.ono;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {


    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board();
    }


    /*
    void noramlSetUp() {
        board.setUpBoard();
    }
*/

    @Test
    void testConstructor() {
        // Verify the Board object is created and initialized correctly
        assertNotNull(board, "Board object should not be null");
        assertEquals(24, board.getPip(1).size() + board.getPip(24).size(), "Initial pip setup should be correct");
        assertNull(board.getPlayerbar(1), "Player 1's bar should  be null at start");
        assertNull(board.getPlayerbar(2), "Player 2's bar should  be null at start");
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

    }

    @Test
    void removefromBar() {
        board.addtoBar(new Checker("X"));
        assertNotNull(board.removefromBar(2), "Removing checker from player 2's bar should be successful");
        assertTrue(board.getPlayerbar(2).isEmpty(), "Player 2's bar should be empty after removing the checker");

    }

    /*
     // Set up a pip with some checkers
        board.addCheckerstoPip("O", 1, 3);  // Adding 3 checkers to pip 1
        board.addCheckerstoPip("X", 1, 2);  // Adding 2 additional checkers to the same pip
        Checker expectedChecker = new Checker("X");
        Checker topChecker = board.getTopCheckerfromPip(1);
        assertEquals(expectedChecker.getType(), topChecker.getType(), "The top checker from pip should be the last one added");

    */



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

    }

    @Test
    void addCheckerstoPip() {

    }

    @Test
    void removeCheckerfromPip() {

    }

    @Test
    void setUpBoard() {
    }

    @Test
    void getPlayerType() {
    }

    @Test
    void isPlayerChecker() {
    }

    @Test
    void printBoard() {
    }

    @Test
    void playerDirection() {
    }

    @Test
    void getTopCheckerfromPip() {
    }

    @Test
    void compareCheckers() {
    }

    @Test
    void isLegalreEntry() {
    }

    @Test
    void isLegal() {
    }

    @Test
    void movePiptoPip() {
    }

    @Test
    void legalMoves() {
    }

    @Test
    void canBearoff() {
    }

    @Test
    void isLegalBearOff() {
    }

    @Test
    void isLegalForceBearOff() {
    }

    @Test
    void bearOffChecker() {
    }

    @Test
    void getplayerOff() {
    }

    @Test
    void printLegalMoves() {
    }

    @Test
    void makeMove() {
    }

    @Test
    void moveBartoPip() {
    }

    @Test
    void takeTurn() {
    }

    @Test
    void main() {
    }
}