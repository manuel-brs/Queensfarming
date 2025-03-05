package de.dhbw.ase;

import de.dhbw.ase.Gemüse.Gemüse;

public class KaufErgebnis {
    private Gemüse gemüse;
    private int preis;

    public KaufErgebnis(Gemüse gemüse, int preis) {
        this.gemüse = gemüse;
        this.preis = preis;
    }

    public Gemüse getGemüse() {
        return gemüse;
    }

    public int getPreis() {
        return preis;
    }
}
