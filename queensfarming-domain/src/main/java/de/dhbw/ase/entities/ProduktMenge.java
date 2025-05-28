package de.dhbw.ase.entities;

import de.dhbw.ase.valueobjects.Produkt;

import java.util.Objects;

public class ProduktMenge {
    private Produkt produkt;
    private int rundenZumBacken;

    public ProduktMenge(Produkt produkt, int rundenZumBacken) {
        this.produkt = produkt;
        this.rundenZumBacken = rundenZumBacken;
    }

    public Produkt getProdukt() {
        return produkt;
    }

    public int getRundenZumBacken() {
        return rundenZumBacken;
    }

    public void setProdukt(Produkt produkt) {
        this.produkt = produkt;
    }

    public void setRundenZumBacken(int rundenZumBacken) {
        this.rundenZumBacken = rundenZumBacken;
    }

    @Override
    public String toString() {
        return produkt + " (Menge: " + rundenZumBacken + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProduktMenge that = (ProduktMenge) o;
        return rundenZumBacken == that.rundenZumBacken && Objects.equals(produkt, that.produkt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(produkt, rundenZumBacken);
    }
}
