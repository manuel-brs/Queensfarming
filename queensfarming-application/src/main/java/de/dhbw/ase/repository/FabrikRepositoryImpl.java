package de.dhbw.ase.repository;

import de.dhbw.ase.entities.Fabrik;
import de.dhbw.ase.entities.Spieler;
import de.dhbw.ase.repositories.FabrikRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FabrikRepositoryImpl implements FabrikRepository {

    private final Map<Spieler, Fabrik> fabrikMap = new HashMap<>();

   @Override
    public List<Fabrik> ladeAlleFabriken() {
        return List.copyOf(fabrikMap.values());
    }

    @Override
    public void speicherFabrik(Spieler spieler, Fabrik fabrik) {
        if (spieler == null || fabrik == null) {
            throw new IllegalArgumentException("Spieler und Fabrik dürfen nicht null sein.");
        }
        fabrikMap.put(spieler, fabrik);
    }

    @Override
    public Fabrik ladeFabrik(Spieler spieler) {
        if (spieler == null) {
            throw new IllegalArgumentException("Spieler darf nicht null sein.");
        }
        Fabrik fabrik = fabrikMap.get(spieler);
        if (fabrik == null) {
            throw new IllegalStateException("Fabrik für den Spieler " + spieler.getName() + " nicht gefunden.");
        }
        return fabrik;
    }
}
