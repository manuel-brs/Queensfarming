package de.dhbw.ase.Kachel;

import de.dhbw.ase.Gemüse.Gemüse;
import de.dhbw.ase.Gemüse.GemüseTyp;

import java.util.Optional;

public class Garten extends BebaubareKachel{
    public Garten(GemüseTyp[] gemüsetypen) {
        super(2, gemüsetypen, "Garten");
    }
}