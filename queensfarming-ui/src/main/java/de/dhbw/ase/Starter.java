package de.dhbw.ase;

import de.dhbw.ase.entities.Markt;
import de.dhbw.ase.exceptions.GameNotFoundException;
import de.dhbw.ase.repositories.*;
import de.dhbw.ase.repository.MarktRepositoryImpl;
import de.dhbw.ase.repository.SpielRepositoryImpl;
import de.dhbw.ase.repository.SpielerManagerRepositoryImpl;
import de.dhbw.ase.repository.SpielfeldRepositoryImpl;
import de.dhbw.ase.repository.FabrikRepositoryImpl;
import de.dhbw.ase.usecases.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Starter {
    public static void main(String[] args) throws GameNotFoundException {
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
        int playerCount;
        do {
            System.out.print("Wie viele Spieler (mindestens 2)?\n> ");
            playerCount = scanner.nextInt();
            scanner.nextLine();
        } while (playerCount < 2);

        // Startgold abfragen
        System.out.print("Mit wie viel Geld sollte ein Spieler starten?\n> ");
        int startGold = scanner.nextInt();
        scanner.nextLine();

        // Zielgold abfragen
        System.out.print("Mit wie viel Gold gewinnt ein Spieler?\n> ");
        int goalGold = scanner.nextInt();
        scanner.nextLine();


        // Spielernamen abfragen
        List<String> players = new ArrayList<>();
        for (int i = 0; i < playerCount; i++) {
            System.out.print("Eingabe des Spieler Namen's " + i + ":\n> ");
            String nextLine = scanner.nextLine();
            players.add(nextLine);
        }

        // Bestätigung der Eingaben
        System.out.println("\nGame Setup:");
        System.out.println("Spieler: " + players);
        System.out.println("Start Gold: " + startGold);
        System.out.println("Ziel Gold: " + goalGold);
        System.out.println("enter \"show actions\" to see all actions");

        // Spiel starten
        SpielRepository spielRepository = new SpielRepositoryImpl();
        MarktRepository marktRepository = new MarktRepositoryImpl();
        SpielerManagerRepository spielerManagerRepository = new SpielerManagerRepositoryImpl();
        SpielfeldRepository spielfeldRepository = new SpielfeldRepositoryImpl();
        FabrikRepository fabrikRepository = new FabrikRepositoryImpl();

        // usecases
        AgrarModulImpl agrarModul = new AgrarModulImpl(
                spielRepository,
                spielfeldRepository,
                marktRepository,
                spielerManagerRepository
        );

        HandelGemüse handelGemüse = new HandelGemüseImpl(
                spielRepository,
                spielfeldRepository,
                marktRepository,
                spielerManagerRepository
        );

        KaufeLandImpl kaufeLand = new KaufeLandImpl(
                spielRepository,
                spielfeldRepository,
                marktRepository,
                spielerManagerRepository
        );

        FabrikModulImpl fabrikModul = new FabrikModulImpl(
                fabrikRepository,
                spielerManagerRepository,
                spielRepository
        );

        ConsoleAdapter consoleAdapter = new ConsoleAdapter(
                spielRepository,
                marktRepository,
                spielerManagerRepository,
                spielfeldRepository,
                agrarModul,
                handelGemüse,
                kaufeLand,
                fabrikRepository,
                fabrikModul,
                scanner
        );
        consoleAdapter.start(players, startGold, goalGold);
    }
}