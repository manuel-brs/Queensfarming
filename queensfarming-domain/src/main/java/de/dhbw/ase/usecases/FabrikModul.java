package de.dhbw.ase.usecases;

import de.dhbw.ase.entities.Spieler;
import de.dhbw.ase.exceptions.GameNotFoundException;
import de.dhbw.ase.repositories.FabrikRepository;
import de.dhbw.ase.repositories.SpielRepository;
import de.dhbw.ase.repositories.SpielerManagerRepository;
import de.dhbw.ase.repositories.SpielfeldRepository;

public interface FabrikModul {
    void upgradeFabrik(FabrikRepository fabrikRepository, Spieler spieler);

    void produziere(String produkt, SpielfeldRepository spielfeldRepository, SpielRepository spielRepository, SpielerManagerRepository spielerManagerRepository);

    void verkaufeProdukte(FabrikRepository fabrikRepository, SpielRepository spielRepository, SpielerManagerRepository spielerManagerRepository) throws GameNotFoundException;
}
