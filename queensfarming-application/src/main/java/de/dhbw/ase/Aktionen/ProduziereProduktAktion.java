package de.dhbw.ase.Aktionen;

import de.dhbw.ase.Kachel.Scheune;
import de.dhbw.ase.Spiel;
import de.dhbw.ase.Spieler;
import java.util.Map;
import java.util.HashMap;

public class ProduziereProduktAktion extends Aktion {
    private final String produkt;
    private final Spiel spiel;

    private static final Map<String, Map<String, Integer>> REZEPTUREN = new HashMap<>();

    static {
        // Definiere die benötigten Zutaten für jedes Produkt
        REZEPTUREN.put("Brot", Map.of("GETREIDE", 2));
        REZEPTUREN.put("Salat", Map.of("SALAT", 1, "TOMATO", 1, "KAROTTE", 1));
    }

    public ProduziereProduktAktion(String produkt, Spiel spiel) {
        this.produkt = produkt;
        this.spiel = spiel;
    }

    @Override
    public boolean execute() {
        Spieler spieler = spiel.getSpieler().get(spiel.getSpieleramzug());
        Scheune scheune = (Scheune) spieler.getSpielfeld().getSpielfeld()[4][2];

        Map<String, Integer> rezept = REZEPTUREN.get(produkt);
        if (rezept == null) {
            return false;
        }

        if (!hatAusreichendeZutaten(scheune, rezept)) {
            spiel.setMessage("Nicht genügend Zutaten!");
            spiel.getGameController().notifyObservers();
            return false;
        }

        scheune.getInventar().forEach((gemüse, anzahl) -> {
            System.out.println(gemüse.getGemüsename() + ": " + anzahl);
        });

        if(!spieler.getFabrik().produziereProdukt(produkt, scheune)) {
            spiel.setMessage("Nicht genügend Arbeiter!");
            spiel.getGameController().notifyObservers();
            return false;
        }
        scheune.getInventar().forEach((gemüse, anzahl) -> {
            System.out.println(gemüse.getGemüsename() + ": " + anzahl);
        });
        entnehmeZutaten(scheune, rezept);

        spiel.getGameController().notifyObservers();
        System.out.println("Produziere " + produkt);
        return true;
    }

    private boolean hatAusreichendeZutaten(Scheune scheune, Map<String, Integer> rezept) {
        return rezept.entrySet().stream().anyMatch(entry ->
                scheune.getInventar().entrySet().stream().anyMatch(k -> {
                    if (k.getKey().getGemüsename().equals(entry.getKey())) {
                        System.out.println("Vorhanden: " + k.getValue() + " benötigt: " + entry.getValue());
                    }
                    return k.getKey().getGemüsename().equals(entry.getKey()) && k.getValue() >= entry.getValue();
                })
        );
    }

    private void entnehmeZutaten(Scheune scheune, Map<String, Integer> rezept) {
        rezept.forEach((zutat, menge) -> {
            scheune.getInventar().entrySet().stream()
                    .filter(entry -> entry.getKey().getGemüsename().equals(zutat))
                    .findFirst()
                    .ifPresent(entry -> {
                        int alterWert = entry.getValue();
                        int neueMenge = alterWert - menge;

                        scheune.getInventar().put(entry.getKey(), neueMenge);
                    });
        });
    }
}
