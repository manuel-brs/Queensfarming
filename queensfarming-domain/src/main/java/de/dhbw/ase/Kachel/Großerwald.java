package de.dhbw.ase.Kachel;

import de.dhbw.ase.Gemüse.Gemüse;
import de.dhbw.ase.Gemüse.Gemüsename;

public class Großerwald extends Kachel{
    public Großerwald() {
        super(8, new Gemüse[]{new Gemüse(Gemüsename.PILZE)}, "Großerwald");
    }
}
