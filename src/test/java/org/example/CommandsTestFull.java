package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CommandsTestFull {}

    class DoubleCommandTest2 {

        private final PrintStream originalOut = System.out;
        private final InputStream originalIn = System.in;
        private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        private ByteArrayInputStream inContent;

        @BeforeEach
        public void setUpStreams() {
            System.setOut(new PrintStream(outContent));
        }

        @AfterEach
        public void restoreStreams() {
            System.setOut(originalOut);
            System.setIn(originalIn);
        }

        private void provideInput(String data) {
            inContent = new ByteArrayInputStream(data.getBytes());
            System.setIn(inContent);
        }

        @Test
        public void testDoubleOfferAccepted() {
            // Prepare input and game state
            provideInput("yes\n");

            Player doublingPlayer = new Player();
            Player receivingPlayer = new Player();

            doublingPlayer.setCanDouble(true);
            receivingPlayer.setCanDouble(false);

            doublingPlayer.setPlayerName("Alice");
            receivingPlayer.setPlayerName("Bob");


            // Run the command
            Commands.Double(doublingPlayer, receivingPlayer);

            // Expected output
            String expectedOutput = String.format(
                    "Alice has offered a double!%s" +
                            "The current stakes are: %d%s" +
                            "Bob, would you like to accept? (Yes/No)%s" +
                            "Stakes doubled!%s" +
                            "Current stakes are: %d%s" +
                            "Bob, you are now the owner of the doubling cube!%s",
                    System.lineSeparator(),
                    1, // Match stake before doubling
                    System.lineSeparator(),
                    System.lineSeparator(),
                    System.lineSeparator(),
                    2, // Match stake after doubling
                    System.lineSeparator(),
                    System.lineSeparator()
            );

            assertEquals(expectedOutput.trim(), outContent.toString().trim());
        }

        @Test
        public void testDoubleStatusPlayer1OwnsCube() {
            // Setup players
            Player player1 = new Player();
            Player player2 = new Player();

            player1.setPlayerName("Alice");
            player2.setPlayerName("Bob");

            player1.setCanDouble(true); // Player 1 owns the doubling cube
            player2.setCanDouble(false);


            // Call the method
            Commands.doubleStatus(player1, player2);

            // Expected output
            String expectedOutput = String.format(
                    "Alice owns the doubling cube!%s" +
                            "The current match stake is 1%s",
                    System.lineSeparator(),
                    System.lineSeparator()
            );

            // Assert
            assertEquals(expectedOutput.trim(), outContent.toString().trim());
        }

    }

    class CustomDiceCommandsTest2 {

        @Test
        public void testCustomDiceProcessInputValidInput() {
            String input = "dice 3 4";

            int result = Commands.customDiceprocessInput(input);

            assertEquals(6, result, "Expected 6 for valid dice command with values between 1 and 6.");
            // Optionally verify if the dice values were set correctly
            // Example: assertEquals(3, getCustomDice1());
            // Example: assertEquals(4, getCustomDice2());
        }

        @Test
        public void testCustomDiceProcessInputInvalidRangeLow() {
            String input = "dice 0 4";

            int result = Commands.customDiceprocessInput(input);

            assertEquals(-1, result, "Expected -1 for dice command with values out of range.");
        }

        @Test
        public void testCustomDiceProcessInputInvalidRangeHigh() {
            String input = "dice 7 2";

            int result = Commands.customDiceprocessInput(input);

            assertEquals(-1, result, "Expected -1 for dice command with values out of range.");
        }

        @Test
        public void testCustomDiceProcessInputInvalidFormatNonNumeric() {
            String input = "dice a b";

            int result = Commands.customDiceprocessInput(input);

            assertEquals(-1, result, "Expected -1 for dice command with non-numeric values.");
        }

        @Test
        public void testCustomDiceProcessInputInvalidCommand() {
            String input = "notdice 3 4";

            int result = Commands.customDiceprocessInput(input);

            assertEquals(-1, result, "Expected -1 for invalid dice command format.");
        }

        @Test
        public void testCustomDiceProcessInputMissingArguments() {
            String input = "dice 3";

            int result = Commands.customDiceprocessInput(input);

            assertEquals(-1, result, "Expected -1 for dice command with missing arguments.");
        }

        @Test
        public void testCustomDiceProcessInputExtraArguments() {
            String input = "dice 3 4 5";

            int result = Commands.customDiceprocessInput(input);

            assertEquals(-1, result, "Expected -1 for dice command with extra arguments.");
        }

        @Test
        public void testSetCustomDice() {
            int dice1 = 3;
            int dice2 = 5;

            Commands.setCustomDice(dice1, dice2);

            // Retrieve the result and validate
            List<Integer> result = Commands.getCustomDiceResult();
            List<Integer> expected = List.of(dice1, dice2);

            assertEquals(expected, result, "Custom dice result should match the values set.");
        }

        @Test
        public void testSetCustomDiceWithBoundaryValues() {
            int dice1 = 1; // Minimum value for dice
            int dice2 = 6; // Maximum value for dice

            Commands.setCustomDice(dice1, dice2);

            // Retrieve the result and validate
            List<Integer> result = Commands.getCustomDiceResult();
            List<Integer> expected = List.of(dice1, dice2);

            assertEquals(expected, result, "Custom dice result should match the boundary values set.");
        }

        @Test
        public void testSetCustomDiceOverwritesPreviousValues() {
            // Set initial dice values
            Commands.setCustomDice(2, 4);

            // Overwrite with new dice values
            int newDice1 = 5;
            int newDice2 = 6;
            Commands.setCustomDice(newDice1, newDice2);

            // Retrieve the result and validate
            List<Integer> result = Commands.getCustomDiceResult();
            List<Integer> expected = List.of(newDice1, newDice2);

            assertEquals(expected, result, "Custom dice result should overwrite the previous values.");
        }
    }

    class FileCommandsTest2 {


        @Test
        public void testProcessFileCommandsQuit() {
            String command = "quit";
            int result = Commands.processFileCommands(command);
            assertEquals(1, result, "Expected to return 1 for 'quit' command.");
        }

        @Test
        public void testProcessFileCommandsRoll() {
            String command = "roll";
            int result = Commands.processFileCommands(command);
            assertEquals(4, result, "Expected to return 4 for 'roll' command.");
        }

        @Test
        public void testProcessFileCommandsHint() {
            String command = "hint";
            int result = Commands.processFileCommands(command);
            assertEquals(2, result, "Expected to return 2 for 'hint' command.");
        }

        @Test
        public void testProcessFileCommandsPip() {
            String command = "pip";
            int result = Commands.processFileCommands(command);
            assertEquals(3, result, "Expected to return 3 for 'pip' command.");
        }

        @Test
        public void testProcessFileCommandsDice() {
            String command = "dice 5 6";
            int result = Commands.processFileCommands(command);
            assertEquals(6, result, "Expected to return 6 for 'dice' command.");
            // Optionally, you can verify if `customDiceprocessInput` was called correctly
        }

        @Test
        public void testProcessFileCommandsDouble() {
            String command = "double";
            int result = Commands.processFileCommands(command);
            assertEquals(7, result, "Expected to return 7 for 'double' command.");
        }

        @Test
        public void testProcessFileCommandsDoubleStatus() {
            String command = "doubleStatus";
            int result = Commands.processFileCommands(command);
            assertEquals(9, result, "Expected to return 9 for 'doubleStatus' command.");
        }

        @Test
        public void testProcessFileCommandsInvalidCommand() {
            String command = "unknownCommand";
            int result = Commands.processFileCommands(command);
            assertEquals(8, result, "Expected to return 8 for unknown commands.");
        }

        @Test
        public void testTestMethodWithValidFile() throws Exception {
            // Simulate the file input
            String simulatedFileContent = "roll\nhint\nquit\n";
            File tempFile = File.createTempFile("testCommands", ".txt");
            tempFile.deleteOnExit();

            try (FileWriter writer = new FileWriter(tempFile)) {
                writer.write(simulatedFileContent);
            }

            // Simulate user input for the file name
            String simulatedUserInput = tempFile.getAbsolutePath() + "\n";
            InputStream stdin = System.in;
            try {
                System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes()));

                // Capture the output for verification
                ByteArrayOutputStream outContent = new ByteArrayOutputStream();
                System.setOut(new PrintStream(outContent));

                // Call the method
                ArrayList<Integer> result = Commands.Test();

                // Verify the result
                List<Integer> expectedCodes = List.of(4, 2, 1); // "roll" -> 4, "hint" -> 2, "quit" -> 1
                assertEquals(expectedCodes, result, "The returned command codes should match the expected values.");

                // Verify the output
                String output = outContent.toString();
                assert (output.contains("Line read: roll"));
                assert (output.contains("Line read: hint"));
                assert (output.contains("Line read: quit"));

            } finally {
                System.setIn(stdin); // Restore original System.in
            }
        }


    }

    class getCommandTest2 {

        private final InputStream systemInBackup = System.in; // backup System.in to restore it later
        private ByteArrayInputStream testIn;

        @BeforeEach
        void setUpInput() {
            // This method will be run before each test to set up the test environment.
        }

        @AfterEach
        void restoreInput() {
            System.setIn(systemInBackup); // Restore original System.in after each test
        }

        private void provideInput(String data) {
            testIn = new ByteArrayInputStream(data.getBytes());
            System.setIn(testIn);
        }

        @Test
        void testGetCommandForQuit() {
            provideInput("quit\n");
            ArrayList<Integer> expected = new ArrayList<>();
            expected.add(1);
            assertEquals(expected, Commands.getCommand(), "Command for 'quit' should return [1]");
        }

        @Test
        void testGetCommandForRoll() {
            provideInput("roll\n");
            ArrayList<Integer> expected = new ArrayList<>();
            expected.add(4);
            assertEquals(expected, Commands.getCommand(), "Command for 'roll' should return [4]");
        }

        @Test
        void testGetCommandForHint() {
            provideInput("hint\n");
            ArrayList<Integer> expected = new ArrayList<>();
            expected.add(2);
            assertEquals(expected, Commands.getCommand(), "Command for 'hint' should return [2]");
        }

        @Test
        void testGetCommandForPip() {
            provideInput("pip\n");
            ArrayList<Integer> expected = new ArrayList<>();
            expected.add(3);
            assertEquals(expected, Commands.getCommand(), "Command for 'pip' should return [3]");
        }

        @Test
        void testGetCommandForDouble() {
            provideInput("double\n");
            ArrayList<Integer> expected = new ArrayList<>();
            expected.add(7);
            assertEquals(expected, Commands.getCommand(), "Command for 'double' should return [7]");
        }

        @Test
        void testGetCommandForDoubleStatus() {
            provideInput("doubleStatus\n");
            ArrayList<Integer> expected = new ArrayList<>();
            expected.add(9);
            assertEquals(expected, Commands.getCommand(), "Command for 'doubleStatus' should return [9]");
        }

        // Add more tests for other commands and cases
    }

    class hintCommandTest {

        private final PrintStream originalOut = System.out;
        private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

        @BeforeEach
        public void setUpStreams() {
            System.setOut(new PrintStream(outContent));
        }

        @AfterEach
        public void restoreStreams() {
            System.setOut(originalOut);
        }

        @Test
        void hintTest() {
            Commands.Hint();
            String newline = System.getProperty("line.separator");

            String expectedOutput = newline +
                    "Here are the list of allowed commands:" + newline +
                    "---------------------------------------" + newline +
                    "'quit': Exit the game" + newline +
                    "'roll': Roll the dice" + newline +
                    "'test': Input a .txt file with a list of commands for the game to execute" + newline +
                    "'pip': Reports the pip count for both players" + newline +
                    "'hint': List all allowed commands" + newline +
                    "'dice <int> <int>': Set the dice roll eg: \"dice 2 4\" " + newline +
                    "'double': The player who owns the double cube can propose to double the stakes of the game" + newline +
                    "'doubleStatus': Displays the name of the player who owns the doubling cube" + newline +
                    "---------------------------------------" + newline +
                    "(Note, these are not case sensitive!)" + newline +
                    newline;

            assertEquals(expectedOutput, outContent.toString());
        }
    }

    class pipsCommandTest2 {

        @Test
        public void testPipsCalculation() {
            // Redirect System.out to capture the output
            ByteArrayOutputStream outContent = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outContent));

            // Create a board and use the provided setup method
            Board board = new Board();
            //board.setUpBoard(); // Assuming setUpBoard properly populates the board

            // Call the method under test
            Commands.Pips(board);

            // The expected output should reflect the pip calculations for the board setup
            String expectedOutput = String.format("Number of pips for Player 1: %d%nNumber of pips for Player 2: %d%n",
                    calculateExpectedPipsForO(), calculateExpectedPipsForX());

            // Assert the output
            assertEquals(expectedOutput, outContent.toString());

            // Reset the standard System.out to its original stream
            System.setOut(System.out);
        }

        private int calculateExpectedPipsForO() {
            // Manually calculate the expected number of pips for X based on setUpBoard configuration
            return 2 * (25 - 1) + 5 * (25 - 12) + 3 * (25 - 17) + 5 * (25 - 19); // Adjust formula based on the actual board and rules
        }

        private int calculateExpectedPipsForX() {
            // Manually calculate the expected number of pips for O based on setUpBoard configuration
            return 2 * (24) + 5 * (13) + 3 * (8) + 5 * (6); // Adjust formula based on the actual board and rules
        }
    }

    class rollTest {

        @Test
        public void testRollOutput() {
            ArrayList<Integer> result = Commands.Roll();
            boolean allMatch = true;
            int firstValue = result.isEmpty() ? -1 : result.get(0);

            // Check if all elements are the same (in case of a double)
            for (int num : result) {
                if (num != firstValue) {
                    allMatch = false;
                    break;
                }
            }

            if (result.size() == 4) {
                // If four items, they must all be the same and must be between 1 and 6
                assertTrue(allMatch, "All four numbers should be the same in case of a double");
                assertTrue(firstValue >= 1 && firstValue <= 6, "Dice roll out of range");
            } else if (result.size() == 2) {
                // If two items, each must be between 1 and 6
                assertTrue(result.get(0) >= 1 && result.get(0) <= 6, "First dice roll out of range");
                assertTrue(result.get(1) >= 1 && result.get(1) <= 6, "Second dice roll out of range");
            } else {
                assertTrue(false, "Unexpected number of dice results");
            }
        }
    }

