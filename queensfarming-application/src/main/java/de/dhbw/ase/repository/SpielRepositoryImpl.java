package de.dhbw.ase.repository;

import de.dhbw.ase.entities.Spiel;
import de.dhbw.ase.exceptions.GameNotFoundException;
import de.dhbw.ase.repositories.SpielRepository;

import java.util.Optional;

public class SpielRepositoryImpl implements SpielRepository {
    private Spiel spiel;

    @Override
    public Spiel save(Spiel spiel) {
        this.spiel = spiel;
        return spiel;
    }

    @Override
    public Spiel get() throws GameNotFoundException {
        if (this.spiel == null) {
            throw new GameNotFoundException("No game found. Please start a new game.");
        }
        return this.spiel;
    }

    @Override
    public void delete() throws GameNotFoundException {
        if (this.spiel == null) {
            throw new GameNotFoundException("No game found to delete. Please start a new game.");
        }
        this.spiel = null;
    }
}