package de.dhbw.ase.Kachel;

import de.dhbw.ase.Gemüse.Gemüse;
import de.dhbw.ase.Gemüse.Gemüsename;

public class Wald extends Kachel {
    public Wald() {
        super(4, new Gemüse[]{new Gemüse(Gemüsename.PILZE)}, "Wald");
    }
}