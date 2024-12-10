package org.example;
import java.util.ArrayList;
import java.util.List;

public class TurnManager {
    private List<Player> players;

    private static int currentPlayerIndex;

    public TurnManager(List<Player> players) {
        this.players = players;
        this.currentPlayerIndex = 0; // Start with the first player
    }

    public Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }
    public Player getOtherPlayer() {
        return players.get((currentPlayerIndex+1)%players.size());
    }

    public void nextTurn() {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
    }


    // Additional methods for handling doubles, skipping players, etc.
}
