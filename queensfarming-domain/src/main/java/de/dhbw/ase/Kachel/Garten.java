package de.dhbw.ase.Kachel;

import de.dhbw.ase.Gemüse.Gemüse;
import de.dhbw.ase.Gemüse.Gemüsename;

public class Garten extends Kachel{
    public Garten() {
        super(2, new Gemüse[]{new Gemüse(Gemüsename.TOMATEN), new Gemüse(Gemüsename.SALAT), new Gemüse(Gemüsename.KAROTTEN)}, "Garten");
    }
}