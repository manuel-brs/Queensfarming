package de.dhbw.ase.Aktionen;

import de.dhbw.ase.Spiel;
import de.dhbw.ase.Spieler;
import de.dhbw.ase.Kachel.BebaubareKachel;
import de.dhbw.ase.Kachel.Scheune;
import de.dhbw.ase.ValueObject.ErntePreis;

public class ErnteAktion implements Aktion {
    private final Spieler spieler;
    private final int posX;
    private final int posY;
    private final Spiel spiel;

    public ErnteAktion(Spieler spieler, int posX, int posY, Spiel spiel) {
        this.spieler = spieler;
        this.posX = posX;
        this.posY = posY;
        this.spiel = spiel;
    }

    @Override
    public boolean execute() {
        var spielfeld = spieler.getSpielfeld();
        var kachel = spielfeld.getSpielfeld()[4 - posX][posY + 2];
        Scheune scheune = (Scheune) spielfeld.getSpielfeld()[4][2];

        if (kachel == null) {
            spiel.setMessage("Dieses Feld hat noch keine Kachel!");
            return false;
        }

        if (kachel instanceof Scheune) {
            spiel.setMessage("Die Scheune kann nicht geerntet werden!");
            return false;
        }

        ErntePreis geerntetesGemüse = ((BebaubareKachel) kachel).ernteKachel();
        if (geerntetesGemüse.getGemüseTyp() == null) {
            spiel.setMessage("Kachel konnte nicht geerntet werden!");
            return false;
        }

        scheune.getInventar().put(geerntetesGemüse.getGemüseTyp(), scheune.getInventar().get(geerntetesGemüse.getGemüseTyp()) + geerntetesGemüse.getAnzahl());
        ((BebaubareKachel) kachel).resetWachstumsstatus();
        return true;
    }
}
