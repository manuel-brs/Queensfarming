package de.dhbw.ase.Gemüse;

public class Gemüse {
    Gemüsename gemüse;
    int wachstumsstatus = 0;
    boolean reif = false;

    public Gemüse(Gemüsename name) {
        this.gemüse = name;
    }

    public int getRundenzumwachsen() {
        return gemüse.rundenzumwachsen;
    }

    public int getWachstumsstatus() {
        return wachstumsstatus;
    }

    public void setWachstumsstatus(int status) {
        wachstumsstatus = status;
    }

    public void wachsen() {
        if (wachstumsstatus < gemüse.rundenzumwachsen) {
            wachstumsstatus++;
        }
    }

    public boolean istGewachsen() {
        return wachstumsstatus >= gemüse.rundenzumwachsen;
    }
}