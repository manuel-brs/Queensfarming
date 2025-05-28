package de.dhbw.ase.entities.Kachel;

import de.dhbw.ase.entities.Gemuese.GemueseTyp;

public class Garten extends BebaubareKachel{
    public Garten(GemueseTyp[] gemüsetypen) {
        super(2, gemüsetypen, "Garten", "G");
    }
}