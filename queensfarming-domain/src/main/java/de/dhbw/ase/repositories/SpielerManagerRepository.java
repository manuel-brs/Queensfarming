package de.dhbw.ase.repositories;

import de.dhbw.ase.entities.Spieler;

import java.util.List;

public interface SpielerManagerRepository {
    void speicherSpieler(int spielerId, Spieler spieler);
    Spieler ladeSpieler(int spielerId);

    List<Spieler> ladeAlleSpieler();
}