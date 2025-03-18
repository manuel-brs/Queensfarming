package de.dhbw.ase.Kachel;

import de.dhbw.ase.Gemüse.GemüseTyp;


public class Feld extends BebaubareKachel {
    public Feld(GemüseTyp[] gemüsetypen) {
        super(4, gemüsetypen, "Feld");
    }
}