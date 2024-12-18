/*


package org.example;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class MatchTest {
    private Match match;
    private Player[] players;

    @Before
    public void setUp() {
        // Setup the match with direct manipulation to avoid user inputs
        match = new Match();
        match.setMatchLength(3); // Pretend we can directly set the match length

       // players = new Player[]{new Player2(), new Player2};
        match.setPlayers(players); // Pretend we can directly set players for the match
    }

    @Test
    public void testMatchScorePrinting() {
        // This test would check the output of printMatchScore by capturing System.out output
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        match.printMatchScore();

        String expectedOutput = "\n--- Current Match Scores ---\nAlice: 0 points\nBob: 0 points\n-----------------------------\n";
        assertEquals("Output should match expected match score output", expectedOutput, outContent.toString());

        System.setOut(System.out);
    }

    @Test
    public void testUpdateMatchOver() {
        players[0].setPlayerMatchscore(3); // Assuming such a setter exists
        match.updateMatchOver();
        assertTrue("Match should be over when player reaches the match length score", match.isMatchOver());
    }

    @Test
    public void testAnnounceMatchWinner() {
        // Capture System.out for asserting the function's output
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        players[0].setPlayerMatchscore(3);
        match.updateMatchOver(); // This would trigger the winner announcement

        String expectedOutput = "Congratulations, the winner is ......Alice\n with a score of 3\n";
        assertTrue("Output should contain the winner's announcement", outContent.toString().contains(expectedOutput));

        System.setOut(System.out);
    }
}
*/