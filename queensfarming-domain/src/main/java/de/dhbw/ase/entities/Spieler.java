package de.dhbw.ase.entities;

public class Spieler {
    int spielernummer = 0;
    String name;
    private Spielfeld spielfeld;
    int anzahlGold;
    private Fabrik fabrik = new Fabrik();
    public Spieler(int spielernummer, String name, int startGold) {
        this.spielernummer = spielernummer;
        this.name = name;
        this.anzahlGold = startGold;
    }

    public int getSpielernummer() {
        return spielernummer;
    }

    public void setSpielernummer(int spielernummer) {
        this.spielernummer = spielernummer;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Spielfeld getSpielfeld() {
        return spielfeld;
    }

    public void setSpielfeld(Spielfeld spielfeld) {
        this.spielfeld = spielfeld;
    }

    public void setAnzahlGold(int anzahlGold) {
        this.anzahlGold = anzahlGold;
    }

    public Fabrik getFabrik() {
        return fabrik;
    }

    public void setFabrik(Fabrik fabrik) {
        this.fabrik = fabrik;
    }

    public int getAnzahlGold() {
        return anzahlGold;
    }

    public String getName() {
        return name;
    }
}