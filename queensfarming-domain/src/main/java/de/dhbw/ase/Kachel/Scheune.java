package de.dhbw.ase.Kachel;

import de.dhbw.ase.Gemüse.Gemüse;
import de.dhbw.ase.Gemüse.Gemüsename;
import de.dhbw.ase.Kachel.Kachel;

import java.util.HashMap;
import java.util.Map;

public class Scheune extends Kachel {
    private Map<Gemüsename, Integer> inventar = new HashMap<>();

    public Scheune() {
        super(-1, new Gemüse[]{new Gemüse(Gemüsename.SALAT), new Gemüse(Gemüsename.KAROTTEN), new Gemüse(Gemüsename.TOMATEN), new Gemüse(Gemüsename.PILZE)});
        inventar.put(Gemüsename.KAROTTEN, 0);
        inventar.put(Gemüsename.TOMATEN, 0);
        inventar.put(Gemüsename.SALAT, 0);
        inventar.put(Gemüsename.PILZE, 0);
    }

    public Map<Gemüsename, Integer> getInventar() {
        return inventar;
    }
}