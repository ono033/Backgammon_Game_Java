package org.example;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class DetermineFirstPlayerTest {
    private Player firstPlayer;
    private Player secondPlayer;
    private int firstPlayerWins;
    private int secondPlayerWins;
    private int trials;

    @Before
    public void setUp() {
        firstPlayer = new Player();
        secondPlayer = new Player();
        firstPlayer.setPlayerName("First Player");
        secondPlayer.setPlayerName("Second Player");
        firstPlayerWins = 0;
        secondPlayerWins = 0;
        trials = 100; // Run a thousand trials to check outcomes
    }

    @Test
    public void testRandomOutcome() {
        for (int i = 0; i < trials; i++) {
            DetermineFirstPlayer.determineFirstPlayer(firstPlayer, secondPlayer);
            if (firstPlayer.getPlayerNumber() == 1) {
                firstPlayerWins++;
            } else if (secondPlayer.getPlayerNumber() == 1) {
                secondPlayerWins++;
            }
            resetPlayers();
        }

        System.out.println("First player won " + firstPlayerWins + " times.");
        System.out.println("Second player won " + secondPlayerWins + " times.");

        assertTrue("First player should win sometimes", firstPlayerWins > 0);
        assertTrue("Second player should win sometimes", secondPlayerWins > 0);
    }

    private void resetPlayers() {
        firstPlayer.playerNumber=0;
        secondPlayer.playerNumber=0;
    }
}
