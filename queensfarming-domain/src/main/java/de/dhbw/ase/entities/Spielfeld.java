package de.dhbw.ase.entities;

import de.dhbw.ase.entities.Gemüse.GemüseTyp;
import de.dhbw.ase.entities.Kachel.BebaubareKachel;
import de.dhbw.ase.entities.Kachel.Kachel;
import de.dhbw.ase.entities.Kachel.Scheune;

public class Spielfeld {
    private Kachel[][] spielfeld;
    public Spielfeld(GemüseTyp[] gemüseTyps) {
        this.spielfeld = new Kachel[5][5];
        this.spielfeld[4][2] = new Scheune(gemüseTyps);
    }

    public int berechneScheunenDistanz(int x, int y) {
        x = 4-x;
        y -=2;
        return (int) Math.sqrt(x * x + y * y);
    }

    public Kachel[][] getSpielfeld() {
        return spielfeld;
    }

    public void setSpielfeld(Kachel[][] spielfeld) {
        this.spielfeld = spielfeld;
    }

    public Scheune getScheune() {
        return (Scheune) spielfeld[4][2];
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
}