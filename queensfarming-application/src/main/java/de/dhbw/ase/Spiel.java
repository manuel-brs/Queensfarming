package de.dhbw.ase;

import de.dhbw.ase.Aktionen.*;
import de.dhbw.ase.Gemüse.GemüseTyp;

import java.util.ArrayList;
import java.util.List;

public class Spiel {
    private final SpielController gameController;
    private List<Spieler> spieler = new ArrayList<>();
    private Spieler aktuellerSpieler;
    private Markt markt;
    private boolean spielaktiv;
    private int spieleramzug = 0;
    private final int zielGold;
    private int aktionszähler = 0;
    private String message = "";

    public Spiel(SpielController controller, int zielGold) {
        this.gameController = controller;
        this.markt = new Markt();
        this.zielGold = zielGold;
    }

    public void startSpiel() {
        if (!this.spielaktiv) {
            this.spielaktiv = true;
            this.aktuellerSpieler = spieler.get(0);
        }
    }

    public boolean spielerHinzufügen(Spieler neuerspieler) {
        if (!this.spielaktiv) {
            spieler.add(neuerspieler);
            return true;
        }
        return false;
    }

    public void beendeZug() {
        if (new ÜberprüfeSiegAktion(spieler.get(spieleramzug), this).execute()) {
            message = "Spieler " + aktuellerSpieler.getName() + " hat gewonnen!!!";
            gameController.notifyObservers();
            return;
        }
        spieleramzug = (spieleramzug + 1) % spieler.size();
        aktuellerSpieler = spieler.get(spieleramzug);
        spieler.forEach(s -> s.getSpielfeld().wachsen());
        gameController.notifyObservers();
        aktionszähler = 0;
    }

    public boolean kaufeGemüse(GemüseTyp gemüse) {
        return new KaufeGemüseAktion(aktuellerSpieler, markt, gemüse, this).execute();
    }

    public boolean pflanzen(int posX, int posY, GemüseTyp gemüse) {
        return new PflanzenAktion(aktuellerSpieler, posX, posY, gemüse, this).execute();
    }

    public boolean ernten(int posX, int posY) {
        return new ErnteAktion(aktuellerSpieler, posX, posY, this).execute();
    }

    public boolean verkaufeGemüse(GemüseTyp gemüse) {
        return new VerkaufeGemüseAktion(aktuellerSpieler, markt, gemüse, this).execute();
    }

    public boolean kaufeLand(int posX, int posY) {
        return new KaufeLandAktion(aktuellerSpieler, markt, posX, posY, this).execute();
    }

    public void upgradeFabrik() {
        //TODO
    }

    public void sellProdukt() {
        //TODO
    }

    public List<Spieler> getSpieler() {
        return spieler;
    }
    public Spieler getAktuellerSpieler() {
        return aktuellerSpieler;
    }
    public Markt getMarkt() {
        return markt;
    }
    public boolean isSpielaktiv() {
        return spielaktiv;
    }
    public int getSpieleramzug() {
        return spieleramzug;
    }
    public int getZielGold() {
        return zielGold;
    }
    public String getMessage() {
        return message;
    }
    public int getAktionszähler() {
        return aktionszähler;
    }
    public void inkrementAktionszähler() {
        this.aktionszähler++;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public SpielController getGameController() {
        return gameController;
    }
}
