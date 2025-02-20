package de.dhbw.ase.Kachel;

import de.dhbw.ase.Gemüse.Gemüse;

public class Kachel {
    private int kapazität;
    private Gemüse anbaubaresgemüse[];

    public int getKapazität() {
        return kapazität;
    }

    public Kachel(int kapazität, Gemüse anbaubaresgemüse[]) {
        this.kapazität = kapazität;
        this.anbaubaresgemüse = anbaubaresgemüse;
    }

    public Gemüse[] getAnbaubaresgemüse() {
        return anbaubaresgemüse;
    }
}