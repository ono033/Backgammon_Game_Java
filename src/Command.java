import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Command {
    // Set of valid commands
    private static final Set<String> VALID_COMMANDS = new HashSet<>();

    static {
//
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

    // Main method for testing
    public static void main(String[] args) {
        Command command = new Command();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a command to test ('quit' to exit):");

        while (true) {
            String input = scanner.nextLine();

            if ("quit".equalsIgnoreCase(input)) {
                System.out.println("Exiting test.");
                break;
            }

            boolean isValid = command.validateCommand(input);
            System.out.println("Command '" + input + "' is " + (isValid ? "valid." : "invalid."));
        }

        scanner.close();
    }
}
