package de.dhbw.ase.entities.Kachel;

import de.dhbw.ase.entities.Gemuese.GemueseTyp;

public class Großerwald extends BebaubareKachel{
    public Großerwald(GemueseTyp[] gemüsetypen) {
        super(8, gemüsetypen, "Großerwald", "GW");
    }
}
