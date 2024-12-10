package org.example;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
}
class EnterPlayerNameTest {

    @Test
    public void testEnterPlayerName() {
        // Simulate user input for the player's name

        // Create a new Player instance
        Player player = new Player();
        player.setPlayerName("NewTestPlayer");
        // Verify the name was set correctly during initialization
        assertEquals("NewTestPlayer", player.getPlayerName(), "Player name should be 'NewTestPlayer'.");
    }
}

class GetPlayerNumberTest {

    @Test
    public void testGetPlayerNumber() {
        // Simulate user input for the player's name
        //String simulatedInput = "TestPlayerNumber\n";
        //System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        // Create a new Player instance
        Player player = new Player();

        // Verify that the player number is correctly assigned
        assertEquals(1, player.getPlayerNumber(), "The first player's number should be 1.");

        // Simulate input for a second player
        //String simulatedInput2 = "TestPlayerNumber2\n";
        //System.setIn(new ByteArrayInputStream(simulatedInput2.getBytes()));

        // Create a second Player instance
        Player player2 = new Player();

        // Verify that the second player's number is correctly assigned
        assertEquals(2, player2.getPlayerNumber(), "The second player's number should be 2.");
    }
}

class SetPlayerName {

    @Test
    public void testSetPlayerNameWithSimulatedInput() {

        // Create a new Player instance (this triggers user input for player name)
        Player player = new Player();
        player.setPlayerName("PlayerName");
        // Verify that the constructor sets the name using user input
        assertEquals("PlayerName", player.getPlayerName(), "Player name should be set to the simulated input.");

        // Manually set a new name using setPlayerName
        player.setPlayerName("AltPlayerName");

        // Verify that the setPlayerName method overrides the previous name
        assertEquals("AltPlayerName", player.getPlayerName(), "Player name should be updated to 'AltPlayerName' using setPlayerName.");
    }
}
class AddPointsToPlayerMatchScore {

    @Test
    public void testAddPointsToPlayerMatchscore() {

        // Create a new Player instance
        Player player = new Player();

        // Set an initial match score
        int initialMatchScore = 20;
        player.setPlayerMatchscore(initialMatchScore);

        // Add points to the match score
        int pointsToAdd = 5;
        player.addPointsToPlayerMatchscore(pointsToAdd);

        // Verify that the match score was updated correctly
        int expectedMatchScore = initialMatchScore + pointsToAdd;
        assertEquals(expectedMatchScore, player.getPlayerMatchscore(), "The player's match score should be updated with the added points.");
    }
}

class DisplayPlayerInfoTest {

    @Test
    public void testDisplayPlayerInfo() {
        // Simulate user input for the player's name

        // System.out to capture the output of displayPlayerInfo
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Create a new Player instance
        Player player = new Player();
        player.setPlayerName("InfoTestPlayer");
        // Set the player's score
        player.setScore(10);

        // Call displayPlayerInfo to print the player's information
        player.displayPlayerInfo();

        // Capture and verify the printed output
        String expectedOutput = "Player: InfoTestPlayer, Score: 10";
        assertEquals(expectedOutput, outputStream.toString().trim(), "Output of displayPlayerInfo should match the expected string.");
    }
}

class GetPlayerMatchScoreTest {

    @Test
    public void testGetPlayerMatchscore() {

        // Create a new Player instance
        Player player = new Player();
        // Set match score to 1 (since the default is 0 in the class)
        player.setPlayerMatchscore(1);


        // Verify the initial match score (should be 0 by default)
        assertEquals(1, player.getPlayerMatchscore(), "The player's match score should initially be 0.");
    }
}

class GetPlayerNameTest {

    @Test
    public void testGetPlayerName() {

        // Create a new Player instance
        Player player = new Player();
        player.setPlayerName("TestPlayer");
        // Assert that the player's name matches the simulated input
        assertEquals("TestPlayer", player.getPlayerName(), "Player name should match the simulated input.");
    }
}

class GetScoreTest {

    @Test
    public void testGetScore() {
        // Simulated user input for the player's name
        // Create a new Player instance
        Player player = new Player();
        player.setPlayerName("ass");
        // Set a score for the player
        player.setScore(50);

        // Verify that getScore retrieves the correct score
        assertEquals(50, player.getScore(), "The score should be 50.");

        // Update the score
        player.setScore(75);

        // Verify that getScore retrieves the updated score
        assertEquals(75, player.getScore(), "The score should be updated to 75.");
    }
}
class SetPlayerMatchScoreTest {

    @Test
    public void testSetPlayerMatchscore() {
        // Simulate user input for the player's name

        // Create a new Player instance
        Player player = new Player();

        // Set match score to a specific value
        int newMatchScore = 10;
        player.setPlayerMatchscore(newMatchScore);

        // Verify that the match score was set correctly
        assertEquals(newMatchScore, player.getPlayerMatchscore(), "The player's match score should be set to the new value.");
    }
}

class SetScoreTest {

    @Test
    public void testSetScore() {

        // Create a new Player instance
        Player player = new Player();
        player.setPlayerName("TestPlayer");
        // Verify the name was set correctly during initialization
        assertEquals("TestPlayer", player.getPlayerName(), "Player name should be 'TestPlayer'.");

        // Set the player's score
        player.setScore(10);

        // Verify that the score is set correctly
        assertEquals(10, player.getScore(), "The score should be 10.");

        // Update the player's score
        player.setScore(20);

        // Verify the updated score
        assertEquals(20, player.getScore(), "The score should be updated to 20.");
    }
}