package de.dhbw.ase;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Starter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Markt markt = new Markt();

        // ASCII Art Banner
        System.out.println("    _.-^-._    .--.");
        System.out.println("  .'         `._|__|");
        System.out.println(" /               | |");
        System.out.println(" /|     _____     | |");
        System.out.println(" | |--|     |--| | |");
        System.out.println(" | |==|==|==|==| | |");
        System.out.println(" |--|--|--|--|--| | |");
        System.out.println(" |--|--|--|--|--| | |");
        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
        System.out.println("    ^^^^^^^^^^^^^^^ QUEENS FARMING ^^^^^^^^^^^^^^^");
        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^\n");

        // Spieleranzahl abfragen
        System.out.print("Wie viele Spieler\n> ");
        int playerCount = scanner.nextInt();
        scanner.nextLine();

        // Startgold abfragen
        System.out.print("Mit wie viel Geld sollte ein Spieler starten?\n> ");
        int startGold = scanner.nextInt();
        scanner.nextLine();

        // Zielgold abfragen
        System.out.print("Mit wie viel Gold gewinnt ein Spieler?\n> ");
        int goalGold = scanner.nextInt();
        Spiel.ZIELGOLD = goalGold;
        scanner.nextLine();


        // Spielernamen abfragen
        List<Spieler> players = new ArrayList<>();
        for (int i = 0; i < playerCount; i++) {
            System.out.print("Eingabe des Spieler Namen's " + i + ":\n> ");
            String nextLine = scanner.nextLine();
            players.add(new Spieler(i, nextLine, startGold, markt));
        }

        // Bestätigung der Eingaben
        System.out.println("\nGame Setup:");
        System.out.println("Spieler: " + players);
        System.out.println("Start Gold: " + startGold);
        System.out.println("Ziel Gold: " + goalGold);

        scanner.close();

        SpielController controller = new SpielController();

        Spiel spiel = new Spiel(controller, goalGold);
        players.forEach(spiel::spielerHinzufügen);
        spiel.startSpiel();
        new GUI(controller, markt, spiel);
    }
}