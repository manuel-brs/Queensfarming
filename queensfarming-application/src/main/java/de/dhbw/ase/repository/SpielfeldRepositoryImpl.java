package de.dhbw.ase.repository;

import de.dhbw.ase.entities.Spieler;
import de.dhbw.ase.entities.Spielfeld;
import de.dhbw.ase.repositories.SpielfeldRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpielfeldRepositoryImpl implements SpielfeldRepository {
    private final Map<Spieler, Spielfeld> spielfeldMap = new HashMap<>();

    @Override
    public void speicherSpielfeld(Spieler spieler, Spielfeld spielfeld) {
        if (spieler == null || spielfeld == null) {
            throw new IllegalArgumentException("Spieler und Spielfeld dürfen nicht null sein.");
        }
        spielfeldMap.put(spieler, spielfeld);
    }

    @Override
    public List<Spielfeld> ladeAlleSpielfelder() {
        return new ArrayList<>(spielfeldMap.values());
    }

    @Override
    public Spielfeld ladeSpielfeld(Spieler spieler) {
        if (spieler == null) {
            throw new IllegalArgumentException("Spieler darf nicht null sein.");
        }
        Spielfeld spielfeld = spielfeldMap.get(spieler);
        if (spielfeld == null) {
            throw new IllegalStateException("Spielfeld für den Spieler " + spieler.getName() + " nicht gefunden.");
        }
        return spielfeld;
    }

    @Override
    public void loescheSpielfeld(Spieler spieler) {
        spielfeldMap.remove(spieler);
    }
}
