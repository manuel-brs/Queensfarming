package de.dhbw.ase.usecases;

import de.dhbw.ase.entities.Gemuese.GemueseTyp;

public interface AgrarModul {
    void anbauenGemüse(int posX, int posY, GemueseTyp gemüse) throws Exception;

    void ernteGemüse(int posX, int posY) throws Exception;
}
