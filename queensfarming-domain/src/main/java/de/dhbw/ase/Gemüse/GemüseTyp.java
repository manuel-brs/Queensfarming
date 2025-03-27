package de.dhbw.ase.Gemüse;

import java.util.Objects;

public class GemüseTyp {
    final private String gemüsename;
    private int preis;
    private int maxpreis;
    private int minpreis;
    public GemüseTyp(String gemüsename, int minpreis, int maxpreis, int preis) {
        this.gemüsename = gemüsename;
        this.minpreis = minpreis;
        this.maxpreis = maxpreis;
        this.preis = preis;
    }

    public String getGemüsename() {
        return gemüsename;
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
