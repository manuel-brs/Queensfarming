package de.dhbw.ase.entities;

import de.dhbw.ase.entities.Kachel.Scheune;
import de.dhbw.ase.valueobjects.Produkt;

import java.util.*;

public class Fabrik {
    private int anzahlArbeiter;
    private int kostenupdate;
    private Map<Produkt, Integer> produkteinbearbeitung = new HashMap<>();
    private Queue<Produkt> lager = new LinkedList<>();

    public Fabrik() {
        this.anzahlArbeiter = 1;
        this.kostenupdate = 2;
    }

    public int getAnzahlArbeiter() {
        return anzahlArbeiter;
    }

    public void setAnzahlArbeiter(int anzahlArbeiter) {
        this.anzahlArbeiter = anzahlArbeiter;
    }

    public int getKostenupdate() {
        return kostenupdate;
    }

    public void setKostenupdate(int kostenupdate) {
        this.kostenupdate = kostenupdate;
    }

    public Map<Produkt, Integer> getProdukteinbearbeitung() {
        return produkteinbearbeitung;
    }

    public void setProdukteinbearbeitung(Map<Produkt, Integer> produkteinbearbeitung) {
        this.produkteinbearbeitung = produkteinbearbeitung;
    }

    public Queue<Produkt> getLager() {
        return lager;
    }

    public void setLager(Queue<Produkt> lager) {
        this.lager = lager;
    }
}