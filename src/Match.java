import java.util.Scanner;

public class Match{
    // Play multiple games in a match

    boolean matchOver = false;
    boolean gameOver = false;

    private Player[] players; // Array to hold players


    private int matchLength = 0;

   // Player playerOne = new Player();
      //  playerOne.displayPlayerInfo();

   // Player playerTwo = new Player();
     //   playerTwo.displayPlayerInfo();



    public Match(){

        players = new Player[2];
        players[0] = new Player(); // Player One
        players[1] = new Player();   // Player Two

        setMatchLength();




        while(true){

            Game game = new Game(players[0], players[1]);
// if game over then update match score depending on type of win

        }



    }

/*
public void setMatchLength(){
        System.out.println("Enter Match Length: ");
    Scanner scanner = new Scanner(System.in);
    String matchLength = scanner.nextInt();
}
*/

public void announceMatchwinner(Player winningPlayer){

    System.out.println("Congratulations, the winner is ......" + winningPlayer.getPlayerName() + "\n with a score of " + winningPlayer.getPlayerMatchscore()  );


}

    public void setMatchLength() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.println("Enter Match Length (positive integer): ");
                matchLength = scanner.nextInt();

                if (matchLength > 0) {
                    break; // Exit loop if valid input
                } else {
                    System.out.println("Match length must be a positive integer. Try again.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a positive integer.");
                scanner.next(); // Clear invalid input
            }
        }

        System.out.println("Match length set to: " + matchLength);

    }



// for testing purposes of match
public void addPoint(Player player){
//ono
  //  player.addToPlayerMatchscore();

}

    public void printMatchScore() {
        System.out.println("\n--- Current Match Scores ---");
        for (Player player : players) {
            System.out.println(player.getPlayerName() + ": " + player.getPlayerMatchscore() + " points");
        }
        System.out.println("-----------------------------");
    }

    public void isMatchOver() {
        
    for(Player player:players){
            if (player.getPlayerMatchscore() >=matchLength){
                Player winningPlayer = player;
                matchOver = true;
                announceMatchwinner(player);

            }
        }
    }

    public static void main(String[] args) {        //testing
    Match match = new Match();
    //printMatchScore();  ono

    }


}

