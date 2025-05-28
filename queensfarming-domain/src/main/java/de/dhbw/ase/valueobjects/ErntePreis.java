package de.dhbw.ase.valueobjects;

import de.dhbw.ase.entities.Gemuese.GemueseTyp;

import java.util.Objects;

public class ErntePreis {
    private final GemueseTyp gemueseTyp;
    private final int anzahl;

    public ErntePreis(GemueseTyp gemueseTyp, int anzahl) {
        if (gemueseTyp == null) {
            throw new IllegalArgumentException("GemüseTyp darf nicht null sein.");
        }
        if (anzahl < 0) {
            throw new IllegalArgumentException("Anzahl darf nicht negativ sein.");
        }
        this.gemueseTyp = gemueseTyp;
        this.anzahl = anzahl;
    }

    public GemueseTyp getGemüseTyp() {
        return gemueseTyp;
    }

    public int getAnzahl() {
        return anzahl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ErntePreis that = (ErntePreis) o;
        return anzahl == that.anzahl && Objects.equals(gemueseTyp, that.gemueseTyp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gemueseTyp, anzahl);
    }

    @Override
    public String toString() {
        return "ErntePreis{" +
                "gemüseTyp=" + gemueseTyp +
                ", anzahl=" + anzahl +
                '}';
    }
}