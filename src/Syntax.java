import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Syntax {

    private static final Set<String> VALID_COMMANDS = new HashSet<>();

    static {
////
        VALID_COMMANDS.add("roll");
        VALID_COMMANDS.add("quit");
        VALID_COMMANDS.add("hint");
        VALID_COMMANDS.add("pip");
        VALID_COMMANDS.add("a");
        VALID_COMMANDS.add("b");
        VALID_COMMANDS.add("c");
    }


    public boolean validateCommand(String command) {
        if (command == null) {
            System.out.println("Error: Command cannot be null.");
            return false;
        }

        // Convert command to lowercase for case-insensitive comparison
        String lowerCaseCommand = command.toLowerCase();

        if (VALID_COMMANDS.contains(lowerCaseCommand)) {
            return true;
        } else {
            System.out.println("Error: Invalid command. Please enter a valid command.");
            return false;
        }
    }


}
