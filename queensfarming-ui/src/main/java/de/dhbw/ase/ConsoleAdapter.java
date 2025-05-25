package de.dhbw.ase;

import de.dhbw.ase.entities.*;
import de.dhbw.ase.entities.Gemüse.GemüseTyp;
import de.dhbw.ase.entities.Kachel.BebaubareKachel;
import de.dhbw.ase.entities.Kachel.Kachel;
import de.dhbw.ase.entities.Kachel.Scheune;
import de.dhbw.ase.exceptions.BaseDomainException;
import de.dhbw.ase.exceptions.GameNotFoundException;
import de.dhbw.ase.exceptions.MarktNotFoundException;
import de.dhbw.ase.exceptions.TooManyActions;
import de.dhbw.ase.repositories.*;
import de.dhbw.ase.usecases.*;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ConsoleAdapter {
    private final SpielRepository spielRepository;
    private final MarktRepository marktRepository;
    private final SpielerManagerRepository spielerManagerRepository;
    private final SpielfeldRepository spielfeldRepository;
    private final Scanner scanner;
    private final AgrarModul agrarModul;
    private final HandelGemüse handelGemüse;
    private final KaufeLand kaufeLand;
    private final FabrikRepository fabrikRepository;

    public ConsoleAdapter(SpielRepository spielRepository, MarktRepository marktRepository, SpielerManagerRepository spielerManagerRepository, SpielfeldRepository spielfeldRepository, AgrarModul agrarModul, HandelGemüse handelGemüse, KaufeLand kaufeLand, FabrikRepository fabrikRepository, Scanner scanner) {
        this.spielRepository = spielRepository;
        this.marktRepository = marktRepository;
        this.spielerManagerRepository = spielerManagerRepository;
        this.spielfeldRepository = spielfeldRepository;
        this.agrarModul = agrarModul;
        this.handelGemüse = handelGemüse;
        this.kaufeLand = kaufeLand;
        this.fabrikRepository = fabrikRepository;
        this.scanner = scanner;
    }

    public void start(List<String> spieler, int startGold, int zielGold) {

        // start game
        try {
            spielRepository.save(new Spiel(spieler, zielGold));
            marktRepository.save(new Markt());
            for (int i = 0; i < spieler.size(); i++) {
                Spieler spieler1 = new Spieler(i, spieler.get(i), startGold);
                spielerManagerRepository.speicherSpieler(i, spieler1);
                spielfeldRepository.speicherSpielfeld(spieler1, new Spielfeld(marktRepository.get().getGemüsearten()));
                fabrikRepository.ladeFabrik(spieler1);
            }
        } catch (MarktNotFoundException e) {
            System.out.println("Failed to initialize Game " + e.getMessage());
            return;
        }

        // game routine
        boolean running = true;
        while (running) {
            try {
                String choice = getIntInput("Choose an option: ");
                running = handleOptionen(choice);
            } catch (BaseDomainException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                // In einem realen Beispiel sollte man hier loggen.
                System.out.println("An unexpected error occurred. Try again or contact support.");
                System.out.println(e);
            }
        }
    }

    protected void zeigeAktionen() {
        System.out.println("\n--- Queensfarming Aktionen: ---");
        System.out.println("show current player");
        System.out.println("show barn");
        System.out.println("show fabrik");
        System.out.println("show market");
        System.out.println("show field");
        System.out.println("buy land");
        System.out.println("buy vegetable");
        System.out.println("sell vegetable");
        System.out.println("plant");
        System.out.println("end turn");
        System.out.println("harvest");
        System.out.println("show actions");
    }

    private boolean handleOptionen(String choice) throws Exception {
        switch (choice) {
            case "show actions":
                zeigeAktionen();
                break;
            case "show current player":
                zeigeAktuellenSpieler();
                break;
            case "show barn":
                zeigeScheune();
                break;
            case "show fabrik":
                zeigeFabrik();
                break;
            case "show market":
                zeigeMarkt();
                break;
            case "show field":
                zeigeSpielfeld();
                break;
            case "buy land":
                if (checkAktionsCounter()) {
                    kaufeLand();
                } else {
                    throw new TooManyActions();
                }
                break;
            case "buy vegetable":
                if (checkAktionsCounter()) {
                    kaufeGemüse();
                } else {
                    throw new TooManyActions();
                }
                break;
            case "sell vegetable":
                if (checkAktionsCounter()) {
                    verkaufeGemüse();
                } else {
                    throw new TooManyActions();
                }
                break;
            case "harvest":
                if (checkAktionsCounter()) {
                    ernteGemüse();
                } else {
                    throw new TooManyActions();
                }
                break;
            case "plant":
                if (checkAktionsCounter()) {
                    pflanzeGemüse();
                } else {
                    throw new TooManyActions();
                }
                break;
            case "end turn":
                beendeZug();
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
        return true;
    }

    private void zeigeFabrik() {
        try {
            int spielerId = spielRepository.get().getSpielerAmZug();
            Spieler spieler = spielerManagerRepository.ladeSpieler(spielerId);
            Fabrik fabrik = fabrikRepository.ladeFabrik(spieler);
            System.out.println("Fabrik von Spieler " + spieler.getName() + ":");
            System.out.println("Anzahl freier Mitarbeiter: "+fabrik.getAnzahlArbeiter());
            System.out.println("Kosten für Update: " + fabrik.getKostenupdate());
            System.out.println("Produkte in Bearbeitung: "+fabrik.getProdukteinbearbeitung().toString());
        } catch (Exception e) {
            System.out.println("Fehler beim Laden der Fabrik: " + e.getMessage());
        }
    }

    private boolean checkAktionsCounter() throws GameNotFoundException {
        Spiel spiel = spielRepository.get();
        if (spiel.getAktionsZähler() < 4) {
            return true;
        }
        return false;
    }

    private void beendeZug() throws GameNotFoundException {
        int spielerId = spielRepository.get().getSpielerAmZug();
        Spieler spieler = spielerManagerRepository.ladeSpieler(spielerId);

        // überprüfen, ob das Spiel zu Ende ist
        if (spielerManagerRepository.ladeSpieler(spielRepository.get().getSpielerAmZug()).getAnzahlGold() >= spielRepository.get().getZielGold()) {
            System.out.println("Spiel beendet! Spieler " + spielerManagerRepository.ladeSpieler(spielRepository.get().getSpielerAmZug()).getName() + " hat gewonnen!");
            System.exit(0);
        }

        spielRepository.get().setAktionsZähler(0);
        int spielerAmZug = (spielerId + 1) % spielerManagerRepository.ladeAlleSpieler().size();
        spielRepository.get().setSpielerAmZug(spielerAmZug);
        if (spielerAmZug == 0) {
            try {
                for ( Spielfeld spielfeld : spielfeldRepository.ladeAlleSpielfelder()) {
                    spielfeld.wachsen();
                }
            } catch (Exception e) {
                System.out.println("Fehler beim Wachsen des Gemüses: " + e.getMessage());
            }
        }

        System.out.println("Zug beendet für Spieler: " + spieler.getName());
    }

    private void pflanzeGemüse() {
        try {
            System.out.println("Gemüse Typ auswählen:");
            String gemüseName = scanner.nextLine();
            System.out.println("Koordinate x (Scheune = 0:0):");
            int x = Integer.parseInt(scanner.nextLine());
            System.out.println("Koordinate y:");
            int y = Integer.parseInt(scanner.nextLine());
            GemüseTyp gemüseTyp = Arrays.stream(marktRepository.get().getGemüsearten())
                    .filter(gemüse -> gemüse.getGemüsename().equalsIgnoreCase(gemüseName))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Gemüse nicht gefunden"));
            agrarModul.anbauenGemüse(x,y, gemüseTyp);
            System.out.println("Gemüse gepflanzt.");
            spielRepository.get().setAktionsZähler(spielRepository.get().getAktionsZähler() + 1);
        } catch (Exception e) {
            System.out.println("Fehler beim Pflanzen des Gemüses: " + e.getMessage());
        }
    }

    private void ernteGemüse() {
        try {
            System.out.println("Koordinate x (Scheune = 0:0):");
            int x = Integer.parseInt(scanner.nextLine());
            System.out.println("Koordinate y:");
            int y = Integer.parseInt(scanner.nextLine());
            agrarModul.ernteGemüse(x,y);
            System.out.println("Gemüse geerntet.");
            spielRepository.get().setAktionsZähler(spielRepository.get().getAktionsZähler() + 1);
        } catch (Exception e) {
            System.out.println("Fehler beim Ernten des Gemüses: " + e.getMessage());
        }
    }

    private void verkaufeGemüse() {
        try {
            System.out.println("Gemüse Typ auswählen:");
            for (GemüseTyp gemüseTyp : marktRepository.get().getGemüsearten()) {
                System.out.println(gemüseTyp.getGemüsename() + " - Preis: " + gemüseTyp.getPreis());
            }
            String gemüseName = scanner.nextLine();
            GemüseTyp gemüseTyp = Arrays.stream(marktRepository.get().getGemüsearten())
                    .filter(gemüse -> gemüse.getGemüsename().equalsIgnoreCase(gemüseName))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Gemüse nicht gefunden"));
            handelGemüse.verkaufeGemüse(gemüseTyp);
            System.out.println("Gemüse verkauft.");
            spielRepository.get().setAktionsZähler(spielRepository.get().getAktionsZähler() + 1);
        } catch (Exception e) {
            System.out.println("Fehler beim Verkauf des Gemüses: " + e.getMessage());
        }
    }

    private void kaufeGemüse() {
        try {
            System.out.println("Gemüse Typ auswählen:");
            for (GemüseTyp gemüseTyp : marktRepository.get().getGemüsearten()) {
                System.out.println(gemüseTyp.getGemüsename() + " - Preis: " + gemüseTyp.getPreis());
            }
            String gemüseName = scanner.nextLine();
            GemüseTyp gemüseTyp = Arrays.stream(marktRepository.get().getGemüsearten())
                    .filter(gemüse -> gemüse.getGemüsename().equalsIgnoreCase(gemüseName))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Gemüse nicht gefunden"));
            handelGemüse.kaufeGemüse(gemüseTyp);
            System.out.println("Gemüse gekauft.");
            spielRepository.get().setAktionsZähler(spielRepository.get().getAktionsZähler() + 1);
        } catch (Exception e) {
            System.out.println("Fehler beim Kauf des Gemüses: " + e.getMessage());
        }
    }

    private void kaufeLand()  {
        try {
            System.out.println("Koordinate x (Scheune = 0:0):");
            int x = Integer.parseInt(scanner.nextLine());
            System.out.println("Koordinate y:");
            int y = Integer.parseInt(scanner.nextLine());
            kaufeLand.kaufeLand(x,y);
            System.out.println("Land gekauft.");
            spielRepository.get().setAktionsZähler(spielRepository.get().getAktionsZähler() + 1);
        } catch (Exception e) {
            System.out.println("Fehler beim Kauf des Landes: " + e.getMessage());
        }
    }

    private void zeigeSpielfeld() throws GameNotFoundException {
        int spielerId = spielRepository.get().getSpielerAmZug();
        Spieler spieler = spielerManagerRepository.ladeSpieler(spielerId);
        Spielfeld spielfeld = spielfeldRepository.ladeSpielfeld(spieler);

        System.out.println("> show field");

        int rows = spielfeld.getSpielfeld().length;
        int cols = spielfeld.getSpielfeld()[0].length;

        String spaltenFormat = "| %-13s";

        for (int y = 0; y < rows; y++) {
            // Trennlinie
            for (int i = 0; i < cols; i++) {
                System.out.print("---------------");
            }
            System.out.println("-");

            // Erste Zeile: Name
            for (int x = 0; x < cols; x++) {
                Kachel kachel = spielfeld.getSpielfeld()[y][x];
                try {
                    BebaubareKachel bebaubareKachel = (BebaubareKachel) kachel;
                    if (bebaubareKachel != null) {
                        String name = bebaubareKachel.getName();
                        System.out.printf(spaltenFormat, name);
                    } else {
                        System.out.printf(spaltenFormat, "");
                    }
                } catch (Exception e) {
                    if (kachel != null) {
                        System.out.printf(spaltenFormat, kachel.getName());
                    } else {
                        System.out.printf(spaltenFormat, "");
                    }
                }
            }
            System.out.println("|");

            // Zweite Zeile: Zusatzinfo
            for (int x = 0; x < cols; x++) {
                Kachel kachel = spielfeld.getSpielfeld()[y][x];

                try {
                    BebaubareKachel bebaubareKachel = (BebaubareKachel) kachel;
                    if (kachel != null) {
                        String info = bebaubareKachel.getSymbol();
                        System.out.printf(spaltenFormat, info);
                    } else {
                        System.out.printf(spaltenFormat, "");
                    }
                } catch (Exception e) {
                    System.out.printf(spaltenFormat, "");
                }
            }
            System.out.println("|");

            // Dritte Zeile: Belegung/Kapazität
            for (int x = 0; x < cols; x++) {
                Kachel kachel = spielfeld.getSpielfeld()[y][x];
                try {
                    BebaubareKachel bebaubareKachel = (BebaubareKachel) kachel;
                    if (bebaubareKachel != null) {
                        int belegt = bebaubareKachel.getWachstumsstatus();
                        int max = bebaubareKachel.getKapazität();
                        String belegung = belegt + "/" + max;
                        System.out.printf(spaltenFormat, belegung);
                    } else {
                        System.out.printf(spaltenFormat, "");
                    }
                } catch (Exception e) {
                    System.out.printf(spaltenFormat, "");
                }
            }
            System.out.println("|");
        }

        // Abschlusslinie
        for (int i = 0; i < cols; i++) {
            System.out.print("-------");
        }
        System.out.println("-");
    }


    private void zeigeMarkt() throws MarktNotFoundException, GameNotFoundException {
        System.out.println("Market:");
        for ( GemüseTyp gemüseTyp : marktRepository.get().getGemüsearten()) {
            System.out.println(gemüseTyp.getGemüsename() + " - Preis: " + gemüseTyp.getPreis());
        }
        System.out.println(marktRepository.get().toString());
    }

    private void zeigeScheune() throws GameNotFoundException, MarktNotFoundException {
        int spielerId = spielRepository.get().getSpielerAmZug();
        Spieler spieler = spielerManagerRepository.ladeSpieler(spielerId);
        Spielfeld spielfeld = spielfeldRepository.ladeSpielfeld(spieler);
        Markt markt = marktRepository.get();
        Scheune scheune = spielfeld.getScheune();

        System.out.println("Scheune von Spieler " + spieler.getName() + ":");
        System.out.println("Gemüse: ");
        for (GemüseTyp gemüse : markt.getGemüsearten()) {
            System.out.println(gemüse.getGemüsename() + ": " + scheune.getInventar().get(gemüse) + " Stück");
        }
        System.out.println("Gold: " + spieler.getAnzahlGold() + " Gold");
    }

    private void zeigeAktuellenSpieler() throws GameNotFoundException {
        int spielerId = spielRepository.get().getSpielerAmZug();
        System.out.println("Aktueller Spieler: " + spielerManagerRepository.ladeSpieler(spielerId).getName());
        System.out.println("Aktueller Spieler Gold: " + spielerManagerRepository.ladeSpieler(spielerId).getAnzahlGold());
    }

    protected String getIntInput(String prompt) {
        System.out.print(prompt);
        String input = scanner.nextLine();
        return input;
    }
}