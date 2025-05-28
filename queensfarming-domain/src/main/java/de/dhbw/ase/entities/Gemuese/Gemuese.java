package de.dhbw.ase.entities.Gemuese;

public class Gemuese {
    GemueseTyp gemüse;

    public Gemuese(GemueseTyp gemueseTyp) {
        this.gemüse = gemueseTyp;
    }

    public GemueseTyp getGemüse() {
        return gemüse;
    }

    public void setGemüse(GemueseTyp gemüse) {
        this.gemüse = gemüse;
    }
}
