package de.dhbw.ase.repositories;

import de.dhbw.ase.entities.Markt;
import de.dhbw.ase.entities.Spiel;
import de.dhbw.ase.exceptions.GameNotFoundException;
import de.dhbw.ase.exceptions.MarktNotFoundException;

public interface MarktRepository {
    Markt save(Markt markt);
    Markt get() throws MarktNotFoundException;
    void delete() throws MarktNotFoundException;
}
