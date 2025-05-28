package de.dhbw.ase.entities.Kachel;

import de.dhbw.ase.entities.Gemuese.GemueseTyp;
import de.dhbw.ase.valueobjects.ErntePreis;

abstract public class BebaubareKachel extends Kachel {
    private GemueseTyp angebaut;
    private String symbol;
    private int wachstumsstatus = 0;
    private int kapazität;

    public BebaubareKachel(int kapazität, GemueseTyp[] anbaubaresgemüse, String name, String symbol) {
        super(anbaubaresgemüse, name);
        this.kapazität = kapazität;
        this.symbol = symbol;
    }

    public boolean baueGemüseAn(GemueseTyp gemüse) {
        for (GemueseTyp gemueseTyp : this.anbaubaresgemüse) {
            if (gemueseTyp.getGemüsename().equals(gemueseTyp.getGemüsename())) {
                if (this.angebaut == null) {
                    this.angebaut = gemüse;
                    this.wachstumsstatus ++;
                    return true;
                }
            }
        }
        return false;
    }

    public void wachsen() {
        if (angebaut != null && this.wachstumsstatus < this.kapazität) {
            this.wachstumsstatus = this.wachstumsstatus * 2;
            if (this.wachstumsstatus > this.kapazität) {
                this.wachstumsstatus = this.kapazität;
            }
        }
    }

    public ErntePreis ernteKachel() {
        GemueseTyp gemüse = this.angebaut;
        this.angebaut = null;
        return new ErntePreis(gemüse, this.wachstumsstatus);
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void resetWachstumsstatus() {
        this.wachstumsstatus = 0;
    }

    public GemueseTyp getAngebaut() {
        return angebaut;
    }

    public void setAngebaut(GemueseTyp angebaut) {
        this.angebaut = angebaut;
    }

    public int getWachstumsstatus() {
        return wachstumsstatus;
    }

    public void setWachstumsstatus(int wachstumsstatus) {
        this.wachstumsstatus = wachstumsstatus;
    }

    public int getKapazität() {
        return kapazität;
    }

    public void setKapazität(int kapazität) {
        this.kapazität = kapazität;
    }
}