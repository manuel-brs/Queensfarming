package de.dhbw.ase.Kachel;

import de.dhbw.ase.Gemüse.Gemüse;
import de.dhbw.ase.Gemüse.GemüseTyp;

public class Kachel {
    private String name;
    private int kapazität;
    private GemüseTyp anbaubaresgemüse[];

    public int getKapazität() {
        return kapazität;
    }

    public Kachel(int kapazität, GemüseTyp anbaubaresgemüse[], String name) {
        this.kapazität = kapazität;
        this.anbaubaresgemüse = anbaubaresgemüse;
        this.name = name;
    }

    public GemüseTyp[] getAnbaubaresgemüse() {
        return anbaubaresgemüse;
    }

    public String getName() {
        return name;
    }
}