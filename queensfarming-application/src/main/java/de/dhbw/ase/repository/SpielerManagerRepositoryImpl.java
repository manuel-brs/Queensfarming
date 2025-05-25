package de.dhbw.ase.repository;

import de.dhbw.ase.entities.Spieler;
import de.dhbw.ase.repositories.SpielerManagerRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpielerManagerRepositoryImpl implements SpielerManagerRepository {
    private final Map<Integer, Spieler> spieler = new HashMap<>();


    @Override
    public void speicherSpieler(int spielerId, Spieler spieler) {
        if (spielerId < 0) {
            throw new IllegalArgumentException("Spieler ID muss positiv sein");
        }
        if (spieler == null) {
            throw new IllegalArgumentException("Spieler darf nicht null sein");
        }
        this.spieler.put(spielerId, spieler);
    }

    @Override
    public Spieler ladeSpieler(int spielerId) {
        if (spielerId < 0) {
            throw new IllegalArgumentException("Spieler ID muss positiv sein");
        }
        Spieler spieler = this.spieler.get(spielerId);
        if (spieler == null) {
            throw new IllegalArgumentException("Spieler nicht gefunden");
        }
        return spieler;
    }

    @Override
    public List<Spieler> ladeAlleSpieler() {
        return new ArrayList<>(spieler.values());
    }
}