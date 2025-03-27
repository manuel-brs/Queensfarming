package de.dhbw.ase.Aktionen;

import de.dhbw.ase.Spiel;
import de.dhbw.ase.Spieler;
import de.dhbw.ase.Markt;
import de.dhbw.ase.Kachel.Kachel;
import de.dhbw.ase.ValueObject.KachelPreis;
import de.dhbw.ase.Spielfeld;

public class KaufeLandAktion implements Aktion {
    private final Spieler spieler;
    private final Markt markt;
    private final int posX;
    private final int posY;
    private final Spiel spiel;

    public KaufeLandAktion(Spieler spieler, Markt markt, int posX, int posY, Spiel spiel) {
        this.spieler = spieler;
        this.markt = markt;
        this.posX = posX;
        this.posY = posY;
        this.spiel = spiel;
    }

    @Override
    public boolean execute() {
        Spielfeld spielfeld = spieler.getSpielfeld();
        int distanz = spielfeld.berechneScheunenDistanz(posX, posY);

        if (spielfeld.getSpielfeld()[4 - posX][posY + 2] != null) {
            spiel.setMessage("Dieses Feld ist bereits bebaut!");
            spiel.getGameController().notifyObservers();
            return false;
        }

        KachelPreis kachelPreis = markt.kaufeLand(distanz);
        if (spieler.getAnzahlGold() >= kachelPreis.getPreis()) {
            spieler.setAnzahlGold(spieler.getAnzahlGold() - kachelPreis.getPreis());
            spielfeld.getSpielfeld()[4- posX][posY + 2] = kachelPreis.getKachel();
            return true;
        }
        spiel.getGameController().notifyObservers();
        return false;
    }
}
