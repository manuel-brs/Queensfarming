package de.dhbw.ase.Kachel;

import de.dhbw.ase.Gemüse.GemüseTyp;
import de.dhbw.ase.ValueObject.ErntePreis;

import java.util.Arrays;
import java.util.Optional;

abstract public class BebaubareKachel extends Kachel {
    private GemüseTyp angebaut;
    private int wachstumsstatus = 0;
    private int kapazität;

    public BebaubareKachel(int kapazität, GemüseTyp[] anbaubaresgemüse, String name) {
        super(anbaubaresgemüse, name);
        this.kapazität = kapazität;
    }
    public boolean baueGemüseAn(GemüseTyp gemüse) {
        for (GemüseTyp gemüseTyp : this.anbaubaresgemüse) {
            if (gemüseTyp.getGemüsename().equals(gemüseTyp.getGemüsename())) {
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
        GemüseTyp gemüse = this.angebaut;
        this.angebaut = null;
        return new ErntePreis(gemüse, this.wachstumsstatus);
    }

    public int getWachstumsstatus() {
        return wachstumsstatus;
    }

    public int getKapazität() {
        return kapazität;
    }

    public GemüseTyp getAngebaut() {
        return angebaut;
    }

    public void resetWachstumsstatus() {
        this.wachstumsstatus = 0;
    }
}