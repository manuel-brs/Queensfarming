package de.dhbw.ase.Kachel;

import de.dhbw.ase.Gemüse.Gemüse;
import de.dhbw.ase.Gemüse.GemüseTyp;

public class Garten extends Kachel{
    public Garten(GemüseTyp[] gemüsetypen) {
        super(2, gemüsetypen, "Garten");
    }
}