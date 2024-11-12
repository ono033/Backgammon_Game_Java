public class GameOver {
    private Player playerA;
    private Player playerB;
    private boolean gameOver;

    public GameOver(Player playerA, Player playerB) {
        this.playerA = playerA;
        this.playerB = playerB;
        this.gameOver = false;
    }
    public boolean hasClearedAllPieces() {
        return false;
    }
///
    public boolean checkGameOver() {
        // Example condition: game is over if one player has cleared all pieces
        if (playerA.hasClearedAllPieces()) {
            gameOver = true;
            announceWinner(playerA);

        } else if (playerB.hasClearedAllPieces()) {
            gameOver = true;
            announceWinner(playerB);
        }
        return gameOver;
    }


    private void announceWinner(Player winner) {
        System.out.println("Game Over! The winner is " + winner.getPlayerName() + "!");
    }

    public boolean isGameOver() {
        return gameOver;
    }
}

