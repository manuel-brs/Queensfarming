package de.dhbw.ase.entities;

import java.util.ArrayList;
import java.util.List;

public class Spiel {
    private int spielerAmZug = 0;
    private int aktionsZähler = 0;
    private final int zielGold;
    public Spiel(List<String> spielerNamen, int zielGold) {
        this.zielGold = zielGold;
    }

    public int getSpielerAmZug() {
        return spielerAmZug;
    }

    public void setSpielerAmZug(int spielerAmZug) {
        this.spielerAmZug = spielerAmZug;
    }

    public int getAktionsZähler() {
        return aktionsZähler;
    }

    public void setAktionsZähler(int aktionsZähler) {
        this.aktionsZähler = aktionsZähler;
    }

    public int getZielGold() {
        return zielGold;
    }
}
