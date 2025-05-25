package de.dhbw.ase.repositories;

import de.dhbw.ase.entities.Spiel;
import de.dhbw.ase.exceptions.GameNotFoundException;

import java.util.Optional;

public interface SpielRepository {
    Spiel save(Spiel spiel);
    Spiel get() throws GameNotFoundException;
    void delete() throws GameNotFoundException;
}