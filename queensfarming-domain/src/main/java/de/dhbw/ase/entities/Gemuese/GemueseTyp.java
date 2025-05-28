package de.dhbw.ase.entities.Gemuese;

public class GemueseTyp {
    final private String gemüsename;
    private int preis;
    private int maxpreis;
    private int minpreis;
    public GemueseTyp(String gemüsename, int minpreis, int maxpreis, int preis) {
        this.gemüsename = gemüsename;
        this.minpreis = minpreis;
        this.maxpreis = maxpreis;
        this.preis = preis;
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

    public String getGemüsename() {
        return gemüsename;
    }

    public int getPreis() {
        return preis;
    }

    public void setPreis(int preis) {
        this.preis = preis;
    }

    public int getMaxpreis() {
        return maxpreis;
    }

    public void setMaxpreis(int maxpreis) {
        this.maxpreis = maxpreis;
    }

    public int getMinpreis() {
        return minpreis;
    }

    public void setMinpreis(int minpreis) {
        this.minpreis = minpreis;
    }
}
