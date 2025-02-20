package de.dhbw.ase;

import java.util.ArrayList;
import java.util.List;

public class Spiel {
    List<Spieler> spieler = new ArrayList<>();
    Kachelstapel stapel;
    Markt markt;
    boolean spielaktiv;
    int spieleramzug = 0;
    public Spiel() {
        markt = new Markt();
    }
    public void startSpiel() {
        if (!this.spielaktiv) {
            this.spielaktiv = true;
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
        if (spieleramzug < spieler.size()) {
            spieleramzug += 1;
        }
        else {
            spieleramzug = 0;
        }
    }
}
