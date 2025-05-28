package de.dhbw.ase.entities.Kachel;

import de.dhbw.ase.entities.Gemuese.GemueseTyp;

import java.util.HashMap;
import java.util.Map;

public class Scheune extends Kachel {
    private Map<GemueseTyp, Integer> inventar = new HashMap<>();

    public Scheune(GemueseTyp[] gemüsetypen) {
        super(gemüsetypen, "Scheune");
        for (GemueseTyp gemüsetyp:gemüsetypen) {
            inventar.put(gemüsetyp, 0);
        }
    }

    public Map<GemueseTyp, Integer> getInventar() {
        return inventar;
    }
}