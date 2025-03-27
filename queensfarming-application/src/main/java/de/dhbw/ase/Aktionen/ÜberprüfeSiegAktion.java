package de.dhbw.ase.Aktionen;

import de.dhbw.ase.Spiel;
import de.dhbw.ase.Spieler;

public class ÜberprüfeSiegAktion implements Aktion {
    private final Spieler spieler;
    private final Spiel spiel;

    public ÜberprüfeSiegAktion(Spieler spieler, Spiel spiel) {
        this.spieler = spieler;
        this.spiel = spiel;
    }

    @Override
    public boolean execute() {
        // Überprüfen, ob der Spieler genug Gold hat
        if (spieler.getAnzahlGold() >= spiel.getZielGold()) {
            spiel.setMessage("Spieler " + spieler.getName() + " hat gewonnen!!!");
            return true;
        }
        return false;
    }
}
