package de.dhbw.ase.entities.Kachel;

import de.dhbw.ase.entities.Gemuese.GemueseTyp;

abstract public class Kachel {
    protected String name;
    protected GemueseTyp anbaubaresgemüse[];

    public Kachel(GemueseTyp anbaubaresgemüse[], String name) {
        this.anbaubaresgemüse = anbaubaresgemüse;
        this.name = name;
    }

    public String getName() {
        return name;
    }
}