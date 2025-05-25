package de.dhbw.ase.repositories;

import de.dhbw.ase.entities.Spieler;
import de.dhbw.ase.entities.Spielfeld;

import java.util.List;

public interface SpielfeldRepository {
    void speicherSpielfeld(Spieler spieler, Spielfeld spielfeld);

    List<Spielfeld> ladeAlleSpielfelder();

    Spielfeld ladeSpielfeld(Spieler spieler);
    void loescheSpielfeld(Spieler spieler);
}
