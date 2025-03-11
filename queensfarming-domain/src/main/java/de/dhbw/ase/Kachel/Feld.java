package de.dhbw.ase.Kachel;

import de.dhbw.ase.Gemüse.GemüseTyp;

public class Feld extends Kachel{
    public Feld(GemüseTyp[] gemüsetypen) {
        super(4, gemüsetypen, "Feld");
    }
}