/*package de.dhbw.ase.Aktionen;

import de.dhbw.ase.entities.Spiel;
import de.dhbw.ase.valueobjects.Produkt;

public class VerkaufeProduktAktion extends Aktion {
    private Spiel spiel;

    public VerkaufeProduktAktion(Spiel spiel) {
        this.spiel = spiel;
    }

    @Override
    public boolean execute() {
        Produkt produkt = spiel.getAktuellerSpieler().getFabrik().getLager().peek();
        if (produkt != null) {
            spiel.getAktuellerSpieler().getFabrik().getLager().poll();
            spiel.getSpieler().get(spiel.getSpieleramzug()).setAnzahlGold(spiel.getSpieler().get(spiel.getSpieleramzug()).getAnzahlGold() + produkt.getPreis());
            return true;
        } else {
            spiel.setMessage("Kein Produkt zum Verkaufen vorhanden!");
            spiel.getGameController().notifyObservers();
        }
        return false;
    }
}
*/
