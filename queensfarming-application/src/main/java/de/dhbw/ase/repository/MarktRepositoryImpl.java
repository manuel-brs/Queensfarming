package de.dhbw.ase.repository;

import de.dhbw.ase.entities.Markt;
import de.dhbw.ase.exceptions.MarktNotFoundException;
import de.dhbw.ase.repositories.MarktRepository;

public class MarktRepositoryImpl implements MarktRepository {
    private Markt markt;

    @Override
    public Markt save(Markt markt) {
        this.markt = markt;
        return markt;
    }

    @Override
    public Markt get() throws MarktNotFoundException {
        if (this.markt == null) {
            throw new MarktNotFoundException("Markt not found. Please create a new Markt first.");
        }
        return this.markt;
    }

    @Override
    public void delete() throws MarktNotFoundException {
        if (this.markt == null) {
            throw new MarktNotFoundException("Markt not found. Please create a new Markt first.");
        }
        this.markt = null;
    }
}
