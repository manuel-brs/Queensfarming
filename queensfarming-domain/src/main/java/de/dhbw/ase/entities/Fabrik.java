package de.dhbw.ase.entities;

import de.dhbw.ase.valueobjects.Produkt;

import java.util.*;

public class Fabrik {
    private int anzahlArbeiter;
    private int kostenupdate;
    private List<ProduktMenge> produkteinbearbeitung = new ArrayList<>();
    private List<Produkt> fertigeProdukte = new ArrayList<>();
    private Queue<Produkt> lager = new LinkedList<>();

    private final Map<String, Map<String, Integer>> REZEPTUREN = new HashMap<>();

    public Fabrik() {
        this.anzahlArbeiter = 1;
        this.kostenupdate = 2;

        // Definiere die benötigten Zutaten für jedes Produkt
        REZEPTUREN.put("Brot", Map.of("GETREIDE", 2));
        REZEPTUREN.put("Salat", Map.of("SALAT", 1, "TOMATE", 1, "KAROTTE", 1));
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

    public List<ProduktMenge> getProdukteinbearbeitung() {
        return produkteinbearbeitung;
    }

    public void setProdukteinbearbeitung(List<ProduktMenge> produkteinbearbeitung) {
        this.produkteinbearbeitung = produkteinbearbeitung;
    }

    public Queue<Produkt> getLager() {
        return lager;
    }

    public void setLager(Queue<Produkt> lager) {
        this.lager = lager;
    }

    public Map<String, Map<String, Integer>> getREZEPTUREN() {
        return REZEPTUREN;
    }

    public List<Produkt> getFertigeProdukte() {
        return fertigeProdukte;
    }

    public void setFertigeProdukte(List<Produkt> fertigeProdukte) {
        this.fertigeProdukte = fertigeProdukte;
    }
}