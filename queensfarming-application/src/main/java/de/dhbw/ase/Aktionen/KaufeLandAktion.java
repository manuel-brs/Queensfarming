package de.dhbw.ase.Aktionen;

import de.dhbw.ase.Spiel;
import de.dhbw.ase.Spieler;
import de.dhbw.ase.Markt;
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

        if (spielfeld.getSpielfeld()[posX][posY] != null) {
            spiel.setMessage("Dieses Feld ist bereits bebaut!");
            spiel.getGameController().notifyObservers();
            return false;
        }

        KachelPreis kachelPreis = markt.kaufeLand(distanz);
        if (spieler.getAnzahlGold() >= kachelPreis.getPreis()) {
            System.out.println(kachelPreis.getPreis());
            spieler.setAnzahlGold(spieler.getAnzahlGold() - kachelPreis.getPreis());
            spielfeld.getSpielfeld()[posX][posY] = kachelPreis.getKachel();
            spiel.getGameController().notifyObservers();
            return true;
        } else {
            markt.getKachelstapel().legeKachelZurück(kachelPreis.getKachel());
            markt.getKachelstapel().mischeStapel();
            spiel.setMessage("Nicht genügend Gold!");
        }
        spiel.getGameController().notifyObservers();
        return false;
    }
}
