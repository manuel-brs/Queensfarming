package de.dhbw.ase.valueobjects;

import de.dhbw.ase.entities.Gemüse.Gemüse;

import java.util.Objects;

public class KaufErgebnis {
    final private Gemüse gemüse;
    final private int preis;

    public KaufErgebnis(Gemüse gemüse, int preis) {
        this.gemüse = gemüse;
        this.preis = preis;
    }

    public Gemüse getGemüse() {
        return gemüse;
    }

    public int getPreis() {
        return preis;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KaufErgebnis that = (KaufErgebnis) o;
        return preis == that.preis && Objects.equals(gemüse, that.gemüse);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gemüse, preis);
    }
}