package de.dhbw.ase.Aktionen;

import de.dhbw.ase.Gemüse.GemüseTyp;
import de.dhbw.ase.Spiel;
import de.dhbw.ase.Spieler;
import de.dhbw.ase.Kachel.Scheune;
import de.dhbw.ase.Markt;
import de.dhbw.ase.ValueObject.KaufErgebnis;

public class VerkaufeGemüseAktion extends Aktion {
    private final Spieler spieler;
    private final Markt markt;
    private final GemüseTyp gemüse;
    private final Spiel spiel;

    public VerkaufeGemüseAktion(Spieler spieler, Markt markt, GemüseTyp gemüse, Spiel spiel) {
        this.spieler = spieler;
        this.markt = markt;
        this.gemüse = gemüse;
        this.spiel = spiel;
    }

    @Override
    public boolean execute() {
        try {
            KaufErgebnis kaufErgebnis = markt.verkaufeGemüse(gemüse);
            Scheune scheune = (Scheune) spieler.getSpielfeld().getSpielfeld()[4][2];
            int anzahlGemüse = scheune.getInventar().get(gemüse);
            if (anzahlGemüse < 1) {
                markt.kaufeGemüse(gemüse);
                spiel.setMessage("Du hast kein Gemüse zum Verkaufen.");
                spiel.getGameController().notifyObservers();
                return false;
            }

            scheune.getInventar().put(gemüse, anzahlGemüse - 1);
            spieler.setAnzahlGold(spieler.getAnzahlGold() + kaufErgebnis.getPreis());
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
