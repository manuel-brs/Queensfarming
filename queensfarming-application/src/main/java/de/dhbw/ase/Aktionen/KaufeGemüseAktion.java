package de.dhbw.ase.Aktionen;

import de.dhbw.ase.Gemüse.GemüseTyp;
import de.dhbw.ase.Markt;
import de.dhbw.ase.Spiel;
import de.dhbw.ase.Spieler;
import de.dhbw.ase.ValueObject.KaufErgebnis;
import de.dhbw.ase.Kachel.Scheune;

public class KaufeGemüseAktion implements Aktion {
    private final Spieler spieler;
    private final Markt markt;
    private final GemüseTyp gemüse;
    private final Spiel spiel;

    public KaufeGemüseAktion(Spieler spieler, Markt markt, GemüseTyp gemüse, Spiel spiel) {
        this.spieler = spieler;
        this.markt = markt;
        this.gemüse = gemüse;
        this.spiel = spiel;
    }

    @Override
    public boolean execute() {
        try {
            KaufErgebnis kaufErgebnis = markt.kaufeGemüse(gemüse);
            if (spieler.getAnzahlGold() < kaufErgebnis.getPreis()) {
                markt.verkaufeGemüse(gemüse);
                spiel.setMessage("Du hast nicht genug Geld um das Gemüse zu kaufen.");
                spiel.getGameController().notifyObservers();
                return false;
            }

            spieler.setAnzahlGold(spieler.getAnzahlGold() - kaufErgebnis.getPreis());
            Scheune scheune = (Scheune) spieler.getSpielfeld().getSpielfeld()[4][2];
            scheune.getInventar().put(gemüse, scheune.getInventar().getOrDefault(gemüse, 0) + 1);
            spiel.getGameController().notifyObservers();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
