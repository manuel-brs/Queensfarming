package de.dhbw.ase.Kachel;

import de.dhbw.ase.Gemüse.Gemüse;
import de.dhbw.ase.Gemüse.GemüseTyp;

import java.util.Optional;

public class Großerwald extends BebaubareKachel{
    public Großerwald(GemüseTyp[] gemüsetypen) {
        super(8, gemüsetypen, "Großerwald");
    }
}
