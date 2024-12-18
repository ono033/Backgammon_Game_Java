//package src;
package org.example;
import java.util.Scanner;


public class Player {
    private static int playerCount = 0;
    private String playerName;
    private int score;
    public int playerNumber;
    public boolean canDouble;
    public boolean hasRolled;
    private int playerMatchscore =0;


    public Player() {
        playerCount++;
        this.playerNumber = playerCount;
        this.score = 0; // Initialize score to 0
        this.canDouble=true;
        this.hasRolled=false;
    }
    public static void resetPlayercount(){
        playerCount=0;
    }
    public void addPointsToPlayerMatchscore(int add) {

        this.playerMatchscore += add;
    }
    public int getPlayerMatchscore(){
        return playerMatchscore;
    }

    public void setPlayerMatchscore(int playerMatchscore) {
        this.playerMatchscore = playerMatchscore;
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



}
