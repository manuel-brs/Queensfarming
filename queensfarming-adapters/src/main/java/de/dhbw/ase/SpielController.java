package de.dhbw.ase;

public class SpielController {
    private final Spiel spiel;

    public SpielController(Spiel spiel) {
        this.spiel = spiel;
    }

    public void plant(int x, int y, String vegetable) {

    }

    public void harvest(int x, int y) {

    }

    public void sell(String vegetable) {

    }

    public void buyLand(int x, int y) {

    }

    public void buyVegetable(String vegetable) {

    }

    public void changePlayer() {
        spiel.beendeZug();
    }

    public Spiel getSpiel() {
        return this.spiel;
    }
}
