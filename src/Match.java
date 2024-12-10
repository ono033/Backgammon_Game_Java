import java.util.Scanner;

public class Match{
    // Play multiple games in a match

    boolean matchOver = false;
    boolean gameOver = false;

    private Player[] players; // Array to hold players

    private int matchLength = 0;


    public Match(){

        Player.resetPlayercount();
        players = new Player[2];

        players[0] = new Player(); // Player One
        players[1] = new Player();   // Player Two

        setMatchLength();


        while(!matchOver){


            printMatchScore();
            Game game = new Game(players[0], players[1]);
            System.out.println("Game over .. ");
            updateMatchOver(); // updates matchover
            }

        }



        //Called at end of match
    public void announceMatchwinner(Player winningPlayer){

    System.out.println("Congratulations, the winner is ......" + winningPlayer.getPlayerName() + "\n with a score of " + winningPlayer.getPlayerMatchscore()  );


}
    //Allows user to select how many points to play to
    public void setMatchLength() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.println("Enter Match Length (positive integer): ");
                matchLength = scanner.nextInt();

                if (matchLength > 0) {
                    break; // Exit loop if valid input
                } else {
                    System.out.println("Match length must be over 0. Try again.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a positive integer.");
                scanner.next(); // Clear invalid input
            }
        }

        System.out.println("Match length set to: " + matchLength);

    }



    public void printMatchScore() {
        System.out.println("\n--- Current Match Scores ---");
        for (Player player : players) {
            System.out.println(player.getPlayerName() + ": " + player.getPlayerMatchscore() + " points");
        }
        System.out.println("-----------------------------");
    }


    public void updateMatchOver() {
        
    for(Player player:players){
            if (player.getPlayerMatchscore() >=matchLength){
                Player winningPlayer = player;
                matchOver = true;
                announceMatchwinner(player);

            }
        }
    }

    public boolean isMatchOver(){
    return matchOver;
    }


}


