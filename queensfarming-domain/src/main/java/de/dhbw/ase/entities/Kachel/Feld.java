package de.dhbw.ase.entities.Kachel;

import de.dhbw.ase.entities.Gemüse.GemüseTyp;


public class Feld extends BebaubareKachel {
    public Feld(GemüseTyp[] gemüsetypen) {
        super(4, gemüsetypen, "Feld", "F");
    }
}