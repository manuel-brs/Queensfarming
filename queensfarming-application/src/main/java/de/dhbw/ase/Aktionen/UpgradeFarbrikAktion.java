package de.dhbw.ase.Aktionen;

import de.dhbw.ase.Spiel;

public class UpgradeFarbrikAktion extends Aktion {
    private Spiel spiel;

    public UpgradeFarbrikAktion(Spiel spiel) {
        this.spiel = spiel;
    }

    @Override
    public boolean execute() {
        if (spiel.getSpieler().get(spiel.getSpieleramzug()).getAnzahlGold() >= spiel.getAktuellerSpieler().getFabrik().getKostenupdate()) {
            spiel.getAktuellerSpieler().getFabrik().upgradeFabrik();
            spiel.getSpieler().get(spiel.getSpieleramzug()).setAnzahlGold(spiel.getSpieler().get(spiel.getSpieleramzug()).getAnzahlGold() - spiel.getAktuellerSpieler().getFabrik().getKostenupdate());
            return true;
        } else {
            spiel.setMessage("Nicht genug Gold!");
            spiel.getGameController().notifyObservers();
        }
        return false;
    }
}
