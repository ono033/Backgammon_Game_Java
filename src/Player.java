//package src;

import java.util.Scanner;


public class Player {
    private static int playerCount = 0;
    private String playerName;
    private int score;
    private int playerNumber;
    public boolean canDouble;
    public boolean hasRolled;


    public Player(int playerNumber) {
        this.playerNumber = playerNumber;
        this.score = 0; // Initialize score to 0
        this.canDouble=true;
        this.hasRolled=false;
        enterPlayerName();
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void enterPlayerName() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Player " + this.playerNumber + ", please enter your name: ");
        setPlayerName(scanner.nextLine());
    }

    public void setCanDouble(boolean status) {
        this.canDouble = status;

    }

    public void setHasRolled(boolean status) {
        this.hasRolled = status;
    }
    public void displayPlayerInfo() {
        System.out.println("Player: " + getPlayerName() + ", Score: " + getScore());
    }



    //public static void main(String[] args) {
      // Player player = new Player();
       //player.displayPlayerInfo();
    //}
    //doubleStatus(int doublingPlayer, receivingPlayer){

    //}

}
