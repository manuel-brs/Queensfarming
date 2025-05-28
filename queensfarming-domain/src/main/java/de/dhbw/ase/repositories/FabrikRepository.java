package de.dhbw.ase.repositories;

import de.dhbw.ase.entities.Fabrik;
import de.dhbw.ase.entities.Spieler;
import de.dhbw.ase.entities.Spielfeld;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface FabrikRepository {
    void speicherFabrik(Spieler spieler, Fabrik fabrik);

    Fabrik ladeFabrik(Spieler spieler);

    List<Fabrik> ladeAlleFabriken();
}
