package de.dhbw.ase.entities.Kachel;

import de.dhbw.ase.entities.Gemuese.GemueseTyp;

public class Wald extends BebaubareKachel {
    public Wald(GemueseTyp[] gemüsetypen) {
        super(4, gemüsetypen, "Wald", "W");
    }
}