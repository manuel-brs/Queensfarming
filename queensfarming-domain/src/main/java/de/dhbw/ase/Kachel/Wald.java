package de.dhbw.ase.Kachel;

import de.dhbw.ase.Gemüse.Gemüse;
import de.dhbw.ase.Gemüse.GemüseTyp;

import java.util.Optional;

public class Wald extends BebaubareKachel {
    public Wald(GemüseTyp[] gemüsetypen) {
        super(4, gemüsetypen, "Wald");
    }
}