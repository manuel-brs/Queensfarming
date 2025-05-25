package de.dhbw.ase.entities.Kachel;

import de.dhbw.ase.entities.Gemüse.GemüseTyp;

public class Wald extends BebaubareKachel {
    public Wald(GemüseTyp[] gemüsetypen) {
        super(4, gemüsetypen, "Wald", "W");
    }
}