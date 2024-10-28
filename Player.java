import java.util.Scanner;

public class Player {
    private String Player1;
    private String Player2;

    public String getPlayer1() {
        return Player1;
    }

    public void setPlayer1(String Player1) {
        this.Player1 = Player1;
    }

    public String getPlayer2() {
        return Player2;
    }

    public void setPlayer2(String Player2) {
        this.Player2 = Player2;
    }

    public void enterPlayerNames() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Player 1, please enter your name: ");
        setPlayer1(scanner.nextLine());

        System.out.print("Player 2, please enter your name: ");
        setPlayer2(scanner.nextLine());
    }

    public void displayPlayerNames() {
        System.out.println("Player 1: " + getPlayer1());
        System.out.println("Player 2: " + getPlayer2());
    }
//Example of how it will be implemented in main code
   // public static void main(String[] args) {
    //    Player player = new Player();
    //    player.enterPlayerNames();
     //   player.displayPlayerNames();
   // }
}
