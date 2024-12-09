import java.util.Random;
//
public class DetermineFirstPlayer {

    public static void determineFirstPlayer(Player firstPlayer, Player secondPlayer) {
        Random random = new Random();

        System.out.println("Rolling dice to determine which player goes first...");
        while(true){
        int roll1 = random.nextInt(6) + 1;
        int roll2 = random.nextInt(6) + 1;

        System.out.println(firstPlayer.getPlayerName() + " rolled: " + roll1);
        System.out.println(secondPlayer.getPlayerName() + " rolled: " + roll2);

        if (roll1 > roll2) {
            System.out.println(firstPlayer.getPlayerName() + " goes first! You are Player 1.");
            firstPlayer.playerNumber=1;
            secondPlayer.playerNumber=2;
            break;
        } else if (roll2 > roll1) {
            System.out.println(secondPlayer.getPlayerName() + " goes first! You are Player 1.");
            firstPlayer.playerNumber=2;
            secondPlayer.playerNumber=1;
            break;
        } else {
            System.out.println("It's a tie! Rolling again...");
        }
        }
    }

    //public static void main(String[] args) {
        // Create two Player objects for testing
    //  Player player1 = new Player();
      //  Player player2 = new Player();

        // Determine which player goes first
        //determineFirstPlayer(player1, player2);
    //}

}
