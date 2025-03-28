package de.dhbw.ase;

import de.dhbw.ase.Kachel.Scheune;

public class Spieler {
    int spielernummer = 0;
    String name;
    private Spielfeld spielfeld;
    int anzahlGold;
    private Fabrik fabrik = new Fabrik();
    public Spieler(int spielernummer, String name, int startGold, Markt markt) {
        this.spielernummer = spielernummer;
        this.spielfeld = new Spielfeld(markt);
        this.name = name;
        this.anzahlGold = startGold;
    }
    
    public int getSpielernummer() {
        return spielernummer;
    }

    public String getName() {
        return name;
    }

    public Spielfeld getSpielfeld() {
        return this.spielfeld;
    }

    public int getAnzahlGold() {
        return anzahlGold;
    }

    public Fabrik getFabrik() {
        return fabrik;
    }

    public void setAnzahlGold(int i) {
        this.anzahlGold = i;
    }
}