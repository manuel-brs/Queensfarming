package de.dhbw.ase;

import de.dhbw.ase.Kachel.Kachel;
import de.dhbw.ase.Kachel.Scheune;

public class Spielfeld {
    private Kachel[][] spielfeld;
    public Spielfeld(Markt markt) {
        this.spielfeld = new Kachel[5][5];
        this.spielfeld[4][2] = new Scheune(markt.getGemüsearten());
    }
    public int berechneScheunenDistanz(int x, int y) {
        return (int) Math.sqrt(x * x + y * y);
    }

    public void bebaueFeld(Kachel kachel) {

    }
    public Kachel[][] getSpielfeld() {
        return spielfeld;
    }
}