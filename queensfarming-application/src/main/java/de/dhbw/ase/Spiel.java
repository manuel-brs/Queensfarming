package de.dhbw.ase;

import de.dhbw.ase.Gemüse.GemüseTyp;
import de.dhbw.ase.Kachel.BebaubareKachel;
import de.dhbw.ase.Kachel.Kachel;
import de.dhbw.ase.Kachel.Scheune;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Spiel {

    private final SpielController gameController;
    public static int ZIELGOLD;
    private String message = "";
    private List<Spieler> spieler = new ArrayList<>();
    private Spieler aktuellerSpieler;
    private Kachelstapel stapel;
    private Markt markt;
    private boolean spielaktiv;
    private int spieleramzug = 0;
    private final int zielGold;
    private int aktionszähler = 0;

    public Spiel(SpielController controller, int zielGold) {
        this.gameController = controller;
        markt = new Markt();
        this.zielGold = zielGold;
    }
    public void startSpiel() {
        if (!this.spielaktiv) {
            this.spielaktiv = true;
            this.aktuellerSpieler = spieler.get(0);
            int anzahlspieler = spieler.size();
            stapel = markt.getKachelstapel();
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
            gameController.notifyObservers();
        }
        else {
            System.out.println("new round");
            spieleramzug = 0;
            //alle Pflanzen müssen wachsen
            spieler.forEach(spieler -> {
                spieler.getSpielfeld().wachsen();
            });
            this.aktuellerSpieler = spieler.get(spieleramzug);
            gameController.notifyObservers();
        }
        aktionszähler = 0;
    }

    public boolean kaufeGemüse(GemüseTyp gemüsename) {
        try {
            KaufErgebnis kaufErgebnis = markt.kaufeGemüse(gemüsename);
            if (spieler.get(spieleramzug).anzahlGold < kaufErgebnis.getPreis()) {
                markt.verkaufeGemüse(gemüsename);
                message = "Du hast nicht genug Geld um das Gemüse zu kaufen.";
                gameController.notifyObservers();
            } else {
                spieler.get(spieleramzug).anzahlGold -= kaufErgebnis.getPreis();
                Scheune scheune = (Scheune) spieler.get(spieleramzug).getSpielfeld().getSpielfeld()[4][2];
                scheune.getInventar().put(gemüsename, scheune.getInventar().get(gemüsename) + 1);
                gameController.notifyObservers();
                return false;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean pflanzen(int posX, int posY, GemüseTyp gemüse) {
        posX = 4 - posX;
        posY += 2;
        Kachel kachel = spieler.get(spieleramzug).getSpielfeld().getSpielfeld()[posX][posY];
        if (kachel == null) {
            message = "Dieses Feld hat noch keine Kachel!";
            gameController.notifyObservers();
            return false;
        }
        if (kachel instanceof Scheune) {
            message = "In die Scheune kann nichts angebaut werden!";
            gameController.notifyObservers();
            return false;
        }
        if (((BebaubareKachel) kachel).getAngebaut() == null) {
            boolean check = ((BebaubareKachel)kachel).baueGemüseAn(gemüse);
            gameController.notifyObservers();
            if (!check) {
                message = "Gemüse konnte nicht gepflanzt werden!";
            }
            return check;
        }
        return false;
    }

    public void ernten(int posX, int posY) {
    }

    public boolean verkaufeGemüse(GemüseTyp gemüsename) {
        try {
            KaufErgebnis kaufErgebnis = markt.verkaufeGemüse(gemüsename);
            Scheune scheune = (Scheune) spieler.get(spieleramzug).getSpielfeld().getSpielfeld()[4][2];
            int anzahlGemüse = scheune.getInventar().get(gemüsename);
            if (anzahlGemüse < 1) {
                markt.kaufeGemüse(gemüsename);
                message = "Du hast kein Gemüsezum verkaufen.";
                gameController.notifyObservers();
            } else {
                scheune.getInventar().put(gemüsename, anzahlGemüse-1);
                spieler.get(spieleramzug).anzahlGold += kaufErgebnis.getPreis();
                gameController.notifyObservers();
                return true;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean kaufeLand(int posX, int posY) {
        Spielfeld spielfeld = spieler.get(spieleramzug).getSpielfeld();
        int distanz = spielfeld.berechneScheunenDistanz(posX, posY);
        posX = 4 - posX;
        posY += 2;
        if(spielfeld.getSpielfeld()[posX][posY] != null) {
            message = "Dieses Feld ist bereits wurde bereits bebaut!";
            gameController.notifyObservers();
            return false;
        }
        KachelPreis kachelPreis = markt.kaufeLand(distanz);
        if(spieler.get(spieleramzug).anzahlGold >= kachelPreis.getPreis()) {
            spieler.get(spieleramzug).anzahlGold -= kachelPreis.getPreis();
            spielfeld.getSpielfeld()[posX][posY] = kachelPreis.getKachel();
        }
        return false;
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

    public int getAktionszähler() {
        return aktionszähler;
    }

    public void inkrementAktionszähler() {
        this.aktionszähler ++;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}