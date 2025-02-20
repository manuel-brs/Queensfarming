package de.dhbw.ase.Kachel;

import de.dhbw.ase.Gemüse.Gemüse;
import de.dhbw.ase.Gemüse.Gemüsename;

public class Großesfeld extends Kachel{
    public Großesfeld() {
        super(8, new Gemüse[]{new Gemüse(Gemüsename.SALAT), new Gemüse(Gemüsename.KAROTTEN)});

    }
}
