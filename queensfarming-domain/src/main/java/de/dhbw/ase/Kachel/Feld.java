package de.dhbw.ase.Kachel;

import de.dhbw.ase.Gemüse.Gemüse;
import de.dhbw.ase.Gemüse.Gemüsename;

public class Feld extends Kachel{
    public Feld() {
        super(4, new Gemüse[]{new Gemüse(Gemüsename.SALAT), new Gemüse(Gemüsename.KAROTTEN)}, "Feld");
    }
}