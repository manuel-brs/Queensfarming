package de.dhbw.ase.entities.Kachel;

import de.dhbw.ase.entities.Gemuese.GemueseTyp;


public class Feld extends BebaubareKachel {
    public Feld(GemueseTyp[] gemüsetypen) {
        super(4, gemüsetypen, "Feld", "F");
    }
}