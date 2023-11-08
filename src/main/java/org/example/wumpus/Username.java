package org.example.wumpus;
import java.util.Scanner;

public class Username {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your username: ");
        String playerName = scanner.nextLine();

        if (isValidPlayerName(playerName)) {
            System.out.println("Username: " + playerName);
        } else {
            System.out.println("Invalid name");
        }

        scanner.close();
    }
    private static boolean isValidPlayerName(String playerName) {
        // This method checks if the player name contains only 0-9 and A-Z characters.
        return playerName.matches("[0-9A-Z]+");
    }
}

