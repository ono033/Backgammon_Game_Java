package org.example;
import java.util.ArrayList;
import java.util.List;

public class TurnManager {
    // Holds the list of players in the game
    private List<Player> players;

    // Keeps track of which player's turn it currently is
    private static int currentPlayerIndex;

    public TurnManager(List<Player> players) {
        // Set up the players and start with the first player in the list
        this.players = players;
        this.currentPlayerIndex = 0;
    }

    public Player getCurrentPlayer() {
        // Return the player whose turn it currently is
        return players.get(currentPlayerIndex);
    }

    public Player getOtherPlayer() {
        // Return the other player, which is not the current one
        return players.get((currentPlayerIndex+1)%2);
    }

    public void nextTurn() {
        // Move to the next player's turn
        currentPlayerIndex = (currentPlayerIndex + 1) % 2;
    }

}
