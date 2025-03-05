package de.dhbw.ase;

import de.dhbw.ase.Gemüse.Gemüsename;
import de.dhbw.ase.Kachel.Scheune;

import java.util.ArrayList;
import java.util.List;

public class Spiel {
    List<Spieler> spieler = new ArrayList<>();
    Spieler aktuellerSpieler;
    Kachelstapel stapel;
    Markt markt;
    boolean spielaktiv;
    int spieleramzug = 0;
    int zielGold = 0;
    public Spiel(int zielGold) {
        markt = new Markt();
        this.zielGold = zielGold;
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
        }
        else {
            spieleramzug = 0;
            this.aktuellerSpieler = spieler.get(spieleramzug);
        }
    }

    public void kaufeGemüse(Gemüsename vegetable) {
        try {
            KaufErgebnis kaufErgebnis= markt.kaufeGemüse(vegetable);
            if (spieler.get(spieleramzug).anzahlGold < kaufErgebnis.getPreis()) {
                markt.verkaufeGemüse(vegetable);

            } else {
                spieler.get(spieleramzug).anzahlGold -= kaufErgebnis.getPreis();
                Scheune scheune = (Scheune) spieler.get(spieleramzug).getSpielfeld().getSpielfeld()[4][2];
                scheune.getInventar().put(vegetable, scheune.getInventar().get(vegetable) + 1);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}