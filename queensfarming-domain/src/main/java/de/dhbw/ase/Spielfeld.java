package de.dhbw.ase;

import de.dhbw.ase.Kachel.BebaubareKachel;
import de.dhbw.ase.Kachel.Kachel;
import de.dhbw.ase.Kachel.Scheune;

import java.util.Arrays;

public class Spielfeld {
    private Kachel[][] spielfeld;
    public Spielfeld(Markt markt) {
        this.spielfeld = new Kachel[5][5];
        this.spielfeld[4][2] = new Scheune(markt.getGemüsearten());
    }
    public int berechneScheunenDistanz(int x, int y) {
        System.out.println("x: " + x + " y: " + y);
        x = 4-x;
        y -=2;
        System.out.println("x: " + x + " y: " + y);
        return (int) Math.sqrt(x * x + y * y);
    }

    public void wachsen() {
        for (Kachel[] kachelarray : spielfeld) {
            for (Kachel kachel : kachelarray) {
                if (!(kachel instanceof Scheune) && kachel != null) {
                    ((BebaubareKachel) kachel).wachsen();
                }
            }
        }
    }
    public Kachel[][] getSpielfeld() {
        return spielfeld;
    }
}