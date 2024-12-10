package org.example;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;
import java.util.List;

public class TurnManagerTest{

    private TurnManager turnManager;
    private Player playerOne;
    private Player playerTwo;

    @Before
    public void setUp() {
        playerOne = new Player();
        playerOne.setPlayerName("Alice");
        playerTwo = new Player();
        playerTwo.setPlayerName("Bob");
        List<Player> players = Arrays.asList(playerOne, playerTwo);
        turnManager = new TurnManager(players);
    }

    @Test
    public void testInitialCurrentPlayer() {
        assertSame("Initial current player should be the first player", playerOne, turnManager.getCurrentPlayer());
    }

    @Test
    public void testGetOtherPlayer() {
        assertSame("Other player should be the second player", playerTwo, turnManager.getOtherPlayer());
    }

    @Test
    public void testNextTurnCyclesThroughPlayers() {
        turnManager.nextTurn();
        assertSame("After one turn, current player should be the second player", playerTwo, turnManager.getCurrentPlayer());
        turnManager.nextTurn();
        assertSame("After two turns, current player should cycle back to the first player", playerOne, turnManager.getCurrentPlayer());
    }
}
