package de.dhbw.ase.usecases;

import de.dhbw.ase.entities.Gemüse.GemüseTyp;

public interface HandelGemüse {
    void kaufeGemüse(GemüseTyp gemüseTyp) throws Exception;

    void verkaufeGemüse(GemüseTyp gemüseTyp) throws Exception;
}
