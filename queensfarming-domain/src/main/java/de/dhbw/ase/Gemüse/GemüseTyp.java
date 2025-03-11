package de.dhbw.ase.Gemüse;

import java.util.Objects;

public class GemüseTyp {
    final private String gemüsename;
    final private int rundenzumwachsen;
    private int preis;
    private int maxpreis;
    private int minpreis;
    public GemüseTyp(String gemüsename, int rundenzumwachsen, int minpreis, int maxpreis, int preis) {
        this.gemüsename = gemüsename;
        this.rundenzumwachsen = rundenzumwachsen;
        this.minpreis = minpreis;
        this.maxpreis = maxpreis;
        this.preis = preis;
    }

    public String getGemüsename() {
        return gemüsename;
    }

    public int getRundenzumwachsen() {
        return rundenzumwachsen;
    }

    public void erhöhePreis() {
        if (preis < maxpreis) {
            preis ++;
        }
    }

    public void verringerePreis() {
        if (preis > minpreis) {
            preis --;
        }
    }

    public int getPreis() {
        return preis;
    }
}
