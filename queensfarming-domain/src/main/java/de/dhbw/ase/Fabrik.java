package de.dhbw.ase;

import de.dhbw.ase.Gemüse.GemüseTyp;
import de.dhbw.ase.Kachel.Scheune;
import de.dhbw.ase.ValueObject.Produkt;

import java.util.*;

public class Fabrik {
    private int anzahlArbeiter;
    private int kostenupdate = 2;
    private Map<Produkt, Integer> produkteinbearbeitung = new HashMap<>();
    private Queue<Produkt> lager = new LinkedList<>();

    public void upgradeFabrik() {
        this.anzahlArbeiter++;
        this.kostenupdate *= 2;
    }

    public boolean produziereProdukt(String produktname, Scheune scheune) {
        if (anzahlArbeiter > 0) {
            Produkt produkt = null;
            String[] zutaten = null;
            switch (produktname) {
                case "BROT":
                    zutaten = new String[]{"GETREIDE"};
                    produkt = new Produkt("BROT", 15, 3);
                    break;
                case "SALAT":
                    zutaten = new String[]{"SALAT", "TOMATE", "KAROTTE"};
                    produkt = new Produkt("SALAT", 18, 2);
                    break;
                default:
                    break;
            }
            if (produkt == null || !checkAndDecrementGemüse(scheune, zutaten)) {
                return false;
            }
            produkteinbearbeitung.put(produkt, 0);
            anzahlArbeiter--;
            return true;
        }
        return false;
    }

    private boolean checkAndDecrementGemüse(Scheune scheune, String[] gemüseNamen) {
        Map<GemüseTyp, Integer> tempInventar = new HashMap<>(scheune.getInventar());
        for (String gemüseName : gemüseNamen) {
            boolean found = false;
            for (GemüseTyp gemüse : tempInventar.keySet()) {
                if (gemüse.getGemüsename().equals(gemüseName) && tempInventar.get(gemüse) > 0) {
                    tempInventar.put(gemüse, tempInventar.get(gemüse) - 1);
                    found = true;
                    break;
                }
            }
            if (!found) {
                return false;
            }
        }
        scheune.getInventar().putAll(tempInventar);
        return true;
    }

    private void checkeFertigeBrote() {
        for (Map.Entry<Produkt, Integer> entry : produkteinbearbeitung.entrySet()) {
            if (entry.getValue() >= entry.getKey().getRundenzumbacken()) {
                produkteinbearbeitung.remove(entry.getKey());
                lager.add(entry.getKey());
                anzahlArbeiter++;
            } else {
                produkteinbearbeitung.put(entry.getKey(), entry.getValue() + 1);
            }
        }
    }

    public int getAnzahlArbeiter() {
        return anzahlArbeiter;
    }

    public int getKostenupdate() {
        return kostenupdate;
    }

    public Map<Produkt, Integer> getProdukteinbearbeitung() {
        return produkteinbearbeitung;
    }

    public Queue<Produkt> getLager() {
        return lager;
    }
}