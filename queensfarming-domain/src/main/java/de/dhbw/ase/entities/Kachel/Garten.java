package de.dhbw.ase.entities.Kachel;

import de.dhbw.ase.entities.Gemüse.GemüseTyp;

public class Garten extends BebaubareKachel{
    public Garten(GemüseTyp[] gemüsetypen) {
        super(2, gemüsetypen, "Garten", "G");
    }
}