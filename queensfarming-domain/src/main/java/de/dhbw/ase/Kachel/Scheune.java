package de.dhbw.ase.Kachel;

import de.dhbw.ase.Gemüse.Gemüse;
import de.dhbw.ase.Gemüse.Gemüsename;
import de.dhbw.ase.Kachel.Kachel;

public class Scheune extends Kachel {
    int anzahlgold = 4;
    public Scheune() {
        super(-1, new Gemüse[]{new Gemüse(Gemüsename.SALAT), new Gemüse(Gemüsename.KAROTTEN), new Gemüse(Gemüsename.TOMATEN), new Gemüse(Gemüsename.PILZE)});
    }
}