package de.dhbw.ase.Aktionen;

import de.dhbw.ase.Gemüse.GemüseTyp;
import de.dhbw.ase.Kachel.Kachel;
import de.dhbw.ase.Spiel;
import de.dhbw.ase.Spieler;
import de.dhbw.ase.Kachel.BebaubareKachel;
import de.dhbw.ase.Kachel.Scheune;
import de.dhbw.ase.Spielfeld;

public class PflanzenAktion implements Aktion {
    private final Spieler spieler;
    private final int posX;
    private final int posY;
    private final GemüseTyp gemüse;
    private final Spiel spiel;

    public PflanzenAktion(Spieler spieler, int posX, int posY, GemüseTyp gemüse, Spiel spiel) {
        this.spieler = spieler;
        this.posX = posX;
        this.posY = posY;
        this.gemüse = gemüse;
        this.spiel = spiel;
    }

    @Override
    public boolean execute() {
        Spielfeld spielfeld = spieler.getSpielfeld();
        Kachel kachel = spielfeld.getSpielfeld()[4- posX][posY + 2];
        Scheune scheune = (Scheune) spielfeld.getSpielfeld()[4][2];

        if (scheune.getInventar().getOrDefault(gemüse, 0) < 1) {
            spiel.setMessage("Du hast kein " + gemüse.getGemüsename() + " in deiner Scheune!");
            return false;
        }

        if (kachel == null) {
            spiel.setMessage("Dieses Feld hat noch keine Kachel!");
            return false;
        }

        if (kachel instanceof Scheune) {
            spiel.setMessage("In die Scheune kann nichts angebaut werden!");
            return false;
        }

        if (((BebaubareKachel) kachel).getAngebaut() != null) {
            spiel.setMessage("Dieses Feld ist bereits bebaut!");
            return false;
        }

        if (!((BebaubareKachel) kachel).baueGemüseAn(gemüse)) {
            spiel.setMessage("Gemüse konnte nicht gepflanzt werden!");
            return false;
        }

        scheune.getInventar().put(gemüse, scheune.getInventar().get(gemüse) - 1);
        return true;
    }
}