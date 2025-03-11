package de.dhbw.ase.Gemüse;

public class Gemüse {
    GemüseTyp gemüse;
    int wachstumsstatus = 0;
    boolean reif = false;

    public Gemüse(GemüseTyp gemüseTyp) {
        this.gemüse = gemüseTyp;
    }

    public void wachsen() {
        if (wachstumsstatus < gemüse.getRundenzumwachsen()) {
            wachstumsstatus++;
        }
    }

    public boolean istGewachsen() {
        return wachstumsstatus >= gemüse.getRundenzumwachsen();
    }
}