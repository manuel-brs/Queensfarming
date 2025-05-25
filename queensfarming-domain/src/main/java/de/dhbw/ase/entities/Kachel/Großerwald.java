package de.dhbw.ase.entities.Kachel;

import de.dhbw.ase.entities.Gemüse.GemüseTyp;

public class Großerwald extends BebaubareKachel{
    public Großerwald(GemüseTyp[] gemüsetypen) {
        super(8, gemüsetypen, "Großerwald", "GW");
    }
}
