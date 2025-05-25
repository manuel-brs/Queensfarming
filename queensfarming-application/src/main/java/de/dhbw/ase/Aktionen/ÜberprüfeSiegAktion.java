/*package de.dhbw.ase.Aktionen;

import de.dhbw.ase.entities.Spiel;
import de.dhbw.ase.entities.Spieler;

public class ÜberprüfeSiegAktion extends Aktion {
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
            return true;
        }
        return false;
    }
}


 */