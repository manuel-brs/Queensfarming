package de.dhbw.ase.valueobjects;

import java.util.Objects;

public class Produkt {
    private final String name;
    private final int preis;
    private final int rundenzumbacken;

    public Produkt(String name, int preis, int rundenzumbacken) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Produktname darf nicht null oder leer sein.");
        }
        if (preis < 0) {
            throw new IllegalArgumentException("Preis darf nicht negativ sein.");
        }
        if (rundenzumbacken < 0) {
            throw new IllegalArgumentException("Runden zum Backen darf nicht negativ sein.");
        }

        this.name = name;
        this.preis = preis;
        this.rundenzumbacken = rundenzumbacken;
    }

    public String getName() {
        return name;
    }

    public int getPreis() {
        return preis;
    }

    public int getRundenzumbacken() {
        return rundenzumbacken;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produkt produkt = (Produkt) o;
        return preis == produkt.preis &&
                rundenzumbacken == produkt.rundenzumbacken &&
                Objects.equals(name, produkt.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, preis, rundenzumbacken);
    }

    @Override
    public String toString() {
        return name + " (" + preis + "€, " + rundenzumbacken + " Runden)";
    }
}