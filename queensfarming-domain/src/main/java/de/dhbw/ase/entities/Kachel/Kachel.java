package de.dhbw.ase.entities.Kachel;

import de.dhbw.ase.entities.Gemüse.GemüseTyp;

abstract public class Kachel {
    protected String name;
    protected GemüseTyp anbaubaresgemüse[];

    public Kachel(GemüseTyp anbaubaresgemüse[], String name) {
        this.anbaubaresgemüse = anbaubaresgemüse;
        this.name = name;
    }

    public String getName() {
        return name;
    }
}