package de.dhbw.ase;

import de.dhbw.ase.Kachel.Kachel;
import de.dhbw.ase.Kachel.Scheune;

public class Spielfeld {
    Kachel[][] spielfeld;
    public Spielfeld() {
        this.spielfeld = new Kachel[10][5];
        this.spielfeld[5][0] = new Scheune();
    }

    public Integer berechnePreisFürFeld(int x, int y) {
        int new_x = 0;
        int new_y = 0;
        return 0;
    }
}