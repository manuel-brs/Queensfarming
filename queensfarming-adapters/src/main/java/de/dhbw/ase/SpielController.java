package de.dhbw.ase;

import de.dhbw.ase.Gemüse.Gemüsename;

public class SpielController {
    private final Spiel spiel;

    public SpielController(Spiel spiel) {
        this.spiel = spiel;
    }

    public void pflanzen(int x, int y, Gemüsename vegetable) {

    }

    public void ernten(int x, int y) {

    }

    public void verkaufeGemüse(Gemüsename vegetable) {

    }

    public void kaufeLand(int x, int y) {

    }

    public void kaufeGemüse(Gemüsename vegetable) {
        spiel.kaufeGemüse(vegetable);
    }

    public void wechselSpieler() {
        spiel.beendeZug();
    }

    public Spiel getSpiel() {
        return this.spiel;
    }
}
