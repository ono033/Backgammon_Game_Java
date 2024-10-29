import java.util.Scanner;

public class Player {
    private static int playerCount = 0;
    private String playerName;
    private int score;
    private int playerNumber;

    public Player() {
        playerCount++;
        this.playerNumber = playerCount;
        this.score = 0; // Initialize score to 0
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
//Example of Main
    //public static void main(String[] args) {
       // Player player = new Player();
       // player.displayPlayerInfo();
    //}


}
