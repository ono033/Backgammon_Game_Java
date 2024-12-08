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
    }

    @Test
    void printBar() {
    }

    @Test
    void addtoBar() {
    }

    @Test
    void isPlayerOnBar() {

    }

    @Test
    void removefromBar() {
    }

    @Test
    void getPip() {

    }

    @Test
    void getChecker() {
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