package de.dhbw.ase.Kachel;

import de.dhbw.ase.Gemüse.Gemüse;

public class Kachel {
    private String name;
    private int kapazität;
    private Gemüse anbaubaresgemüse[];

    public int getKapazität() {
        return kapazität;
    }

    public Kachel(int kapazität, Gemüse anbaubaresgemüse[], String name) {
        this.kapazität = kapazität;
        this.anbaubaresgemüse = anbaubaresgemüse;
        this.name = name;
    }

    public Gemüse[] getAnbaubaresgemüse() {
        return anbaubaresgemüse;
    }

    public String getName() {
        return name;
    }
}