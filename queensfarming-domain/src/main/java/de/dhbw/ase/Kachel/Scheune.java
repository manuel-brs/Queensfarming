package de.dhbw.ase.Kachel;

import de.dhbw.ase.Gemüse.GemüseTyp;

import java.util.HashMap;
import java.util.Map;

public class Scheune extends Kachel {
    private Map<GemüseTyp, Integer> inventar = new HashMap<>();

    public Scheune(GemüseTyp[] gemüsetypen) {
        super(-1, gemüsetypen, "Scheune");
        for (GemüseTyp gemüsetyp:gemüsetypen) {
            inventar.put(gemüsetyp, 0);
        }
    }

    public Map<GemüseTyp, Integer> getInventar() {
        return inventar;
    }
}