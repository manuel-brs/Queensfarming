package de.dhbw.ase.entities.Gemüse;

public class Gemüse {
    GemüseTyp gemüse;

    public Gemüse(GemüseTyp gemüseTyp) {
        this.gemüse = gemüseTyp;
    }

    public GemüseTyp getGemüse() {
        return gemüse;
    }

    public void setGemüse(GemüseTyp gemüse) {
        this.gemüse = gemüse;
    }
}
