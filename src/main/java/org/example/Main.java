package org.example;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        System.out.println("Welcome to Backgammon");

        while(true) {

            Scanner scanner = new Scanner(System.in);
            System.out.println(" Would you like to start a new match? (y/n)");
            String userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("y")) {


                Match match = new Match();

               if (match.isMatchOver()){  //ono check this line not sure if ok
                    System.out.println("Match Over");
                    continue;
                }


            }

            else if (userInput.equalsIgnoreCase("n")) {
                System.out.println(" Thanks for playing! ");
                break;
            }
            else {
                System.out.println("Invalid input");
            }

        }

    }

}