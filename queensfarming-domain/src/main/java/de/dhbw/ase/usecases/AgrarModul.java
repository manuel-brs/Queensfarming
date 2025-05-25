package de.dhbw.ase.usecases;

import de.dhbw.ase.entities.Gemüse.GemüseTyp;
import de.dhbw.ase.exceptions.GameNotFoundException;
import de.dhbw.ase.exceptions.MarktNotFoundException;

public interface AgrarModul {
    void anbauenGemüse(int posX, int posY, GemüseTyp gemüse) throws Exception;

    void ernteGemüse(int posX, int posY) throws Exception;
}
