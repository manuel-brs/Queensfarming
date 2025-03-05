package de.dhbw.ase;

import de.dhbw.ase.Gemüse.Gemüsename;
import de.dhbw.ase.Kachel.Scheune;

import java.util.ArrayList;
import java.util.List;

public class Spiel {
    public static int ZIELGOLD;
    private String message;
    private static Spiel instance;

    private List<IObserver> observers = new ArrayList<>();

    private List<Spieler> spieler = new ArrayList<>();
    private Spieler aktuellerSpieler;
    private Kachelstapel stapel;
    private Markt markt;
    private boolean spielaktiv;
    private int spieleramzug = 0;
    private final int zielGold;
    private Spiel(int zielGold) {
        markt = new Markt();
        this.zielGold = zielGold;
    }

    public static Spiel getInstance() {
        if (instance == null) {
            instance = new Spiel(ZIELGOLD);
        }
        return instance;
    }

    public void startSpiel() {
        if (!this.spielaktiv) {
            this.spielaktiv = true;
            this.aktuellerSpieler = spieler.get(0);
            int anzahlspieler = spieler.size();
            stapel = new Kachelstapel(4*anzahlspieler, 4*anzahlspieler, 2*anzahlspieler, anzahlspieler, 2*anzahlspieler);
        }
    }
    public boolean spielerHinzufügen(Spieler neuerspieler) {
        if(!this.spielaktiv) {
            spieler.add(neuerspieler);
            return true;
        }
        else {
            return false;
        }
    }

    public void beendeZug() {
        if (spieleramzug < spieler.size()-1) {
            spieleramzug += 1;
            this.aktuellerSpieler = spieler.get(spieleramzug);
            SpielController.getInstance().notifyObservers();
        }
        else {
            spieleramzug = 0;
            this.aktuellerSpieler = spieler.get(spieleramzug);
            SpielController.getInstance().notifyObservers();
        }
    }

    public void kaufeGemüse(Gemüsename vegetable) {
        try {
            KaufErgebnis kaufErgebnis= markt.kaufeGemüse(vegetable);
            if (spieler.get(spieleramzug).anzahlGold < kaufErgebnis.getPreis()) {
                markt.verkaufeGemüse(vegetable);
                message = "Du hast nicht genug Geld um das Gemüse zu kaufen.";
                SpielController.getInstance().notifyObservers();
            } else {
                spieler.get(spieleramzug).anzahlGold -= kaufErgebnis.getPreis();
                Scheune scheune = (Scheune) spieler.get(spieleramzug).getSpielfeld().getSpielfeld()[4][2];
                scheune.getInventar().put(vegetable, scheune.getInventar().get(vegetable) + 1);
                SpielController.getInstance().notifyObservers();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void pflanzen(int posX, int posY, Gemüsename salat) {
    }

    public void ernten(int posX, int posY) {
    }

    public void verkaufeGemüse(Gemüsename salat) {
    }

    public void kaufeLand(int posX, int posY) {
    }




    //getter Methoden
    public List<Spieler> getSpieler() {
        return spieler;
    }

    public Spieler getAktuellerSpieler() {
        return aktuellerSpieler;
    }

    public Kachelstapel getStapel() {
        return stapel;
    }

    public Markt getMarkt() {
        return markt;
    }

    public boolean isSpielaktiv() {
        return spielaktiv;
    }

    public int getSpieleramzug() {
        return spieleramzug;
    }

    public int getZielGold() {
        return zielGold;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}