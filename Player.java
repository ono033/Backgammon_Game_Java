import java.util.Scanner;

public class Player {
    private static int playerCount = 0;
    private String playerName;
    private int score;
    private int playerNumber;

    public Player() {
        playerCount++;
        this.playerNumber = playerCount;
        enterPlayerName();
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void enterPlayerName() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Player " + playerNumber + ", please enter your name: ");
        setPlayerName(scanner.nextLine());
    }

    public void displayPlayerInfo() {
        System.out.println("Player: " + getPlayerName() + ", Score: " + getScore());
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of players: ");
        int numPlayers = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Player[] players = new Player[numPlayers];

        for (int i = 0; i < numPlayers; i++) {
            players[i] = new Player();
        }

        System.out.println("\nPlayer Information:");
        for (Player player : players) {
            player.displayPlayerInfo();
        }
    }
}
