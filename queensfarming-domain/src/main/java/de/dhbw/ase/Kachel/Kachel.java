package de.dhbw.ase.Kachel;

import de.dhbw.ase.Gemüse.Gemüse;
import de.dhbw.ase.Gemüse.GemüseTyp;

import java.util.Optional;

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