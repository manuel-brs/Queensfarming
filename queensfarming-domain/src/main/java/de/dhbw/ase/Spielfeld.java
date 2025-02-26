package de.dhbw.ase;

import de.dhbw.ase.Kachel.Kachel;
import de.dhbw.ase.Kachel.Scheune;

public class Spielfeld {
    Kachel[][] spielfeld;
    public Spielfeld() {
        this.spielfeld = new Kachel[5][5];
        this.spielfeld[4][2] = new Scheune();
    }
}