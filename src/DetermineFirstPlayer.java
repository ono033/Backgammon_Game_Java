import java.util.Random;

public class DetermineFirstPlayer {

    public static void determineFirstPlayer(Player player1, Player player2) {
        Random random = new Random();

        System.out.println("Rolling dice to determine which player goes first...");

        int roll1 = random.nextInt(6) + 1;
        int roll2 = random.nextInt(6) + 1;

        System.out.println(player1.getPlayerName() + " rolled: " + roll1);
        System.out.println(player2.getPlayerName() + " rolled: " + roll2);

        if (roll1 > roll2) {
            System.out.println(player1.getPlayerName() + " goes first!");
        } else if (roll2 > roll1) {
            System.out.println(player2.getPlayerName() + " goes first!");
        } else {
            System.out.println("It's a tie! Rolling again...");
            determineFirstPlayer(player1, player2);
        }
    }

    public static void main(String[] args) {
        // Create two Player objects for testing
        Player player1 = new Player();
        Player player2 = new Player();

        // Determine which player goes first
        determineFirstPlayer(player1, player2);
    }
}
