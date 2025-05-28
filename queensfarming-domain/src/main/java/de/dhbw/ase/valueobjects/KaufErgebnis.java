package de.dhbw.ase.valueobjects;

import de.dhbw.ase.entities.Gemuese.Gemuese;
import java.util.Objects;

public class KaufErgebnis {
    private final Gemuese gemuese;
    private final int preis;

    public KaufErgebnis(Gemuese gemuese, int preis) {
        if (gemuese == null) {
            throw new IllegalArgumentException("Gemüse darf nicht null sein.");
        }
        if (preis < 0) {
            throw new IllegalArgumentException("Preis darf nicht negativ sein.");
        }
        this.gemuese = gemuese;
        this.preis = preis;
    }

    public Gemuese getGemüse() {
        return gemuese;
    }

    public int getPreis() {
        return preis;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KaufErgebnis that = (KaufErgebnis) o;
        return preis == that.preis && Objects.equals(gemuese, that.gemuese);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gemuese, preis);
    }

    @Override
    public String toString() {
        return "KaufErgebnis{" + "gemüse=" + gemuese + ", preis=" + preis + '}';
    }
}
