package de.dhbw.ase.Kachel;

import de.dhbw.ase.Gemüse.Gemüse;
import de.dhbw.ase.Gemüse.GemüseTyp;

import java.util.Optional;

public class Großesfeld extends BebaubareKachel{
    public Großesfeld(GemüseTyp[] gemüsetypen) {
        super(8, gemüsetypen, "Großesfeld");

    }
}
