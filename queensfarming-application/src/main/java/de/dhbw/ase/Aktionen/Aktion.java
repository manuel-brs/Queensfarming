package de.dhbw.ase.Aktionen;

import de.dhbw.ase.Spiel;

public abstract class Aktion {
    public boolean actionExec(Spiel spiel) {
        if (!überprüfeActionCounter(spiel)) {
            return false;
        }
        return execute();
    }

    abstract boolean execute();

    private boolean überprüfeActionCounter(Spiel spiel) {
        if (spiel.getAktionszähler() > 3) {
            spiel.setMessage("Du hast bereits 4 Aktionen durchgeführt!");
            spiel.getGameController().notifyObservers();
            return false;
        }
        return true;
    }
}