package de.dhbw.ase.Aktionen;

import de.dhbw.ase.Fabrik;
import de.dhbw.ase.Spiel;
import de.dhbw.ase.Spieler;

public class UpdateFabrik extends Aktion {
    private final Fabrik fabrik;
    private final Spiel spiel;
    private final Spieler spieler;

    public UpdateFabrik(Fabrik fabrik, Spiel spiel, Spieler spieler) {
        this.fabrik = fabrik;
        this.spiel = spiel;
        this.spieler = spieler;
    }

    @Override
    public boolean execute() {
        try {
            if (this.fabrik.getKostenupdate() > spieler.getAnzahlGold()) {
                spiel.setMessage("Du hast nicht genug Gold um die Fabrik zu updaten!");
                spiel.getGameController().notifyObservers();
                return false;
            }
            fabrik.upgradeFabrik();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
