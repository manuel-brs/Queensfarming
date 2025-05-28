package de.dhbw.ase.usecases;

import de.dhbw.ase.entities.Gemuese.GemueseTyp;

public interface HandelGemüse {
    void kaufeGemüse(GemueseTyp gemueseTyp) throws Exception;

    void verkaufeGemüse(GemueseTyp gemueseTyp) throws Exception;
}
