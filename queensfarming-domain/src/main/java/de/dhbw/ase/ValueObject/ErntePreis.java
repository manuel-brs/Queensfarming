package de.dhbw.ase.ValueObject;

import de.dhbw.ase.Gemüse.GemüseTyp;

import java.util.Objects;

public class ErntePreis {
    final private GemüseTyp gemüseTyp;
    final private int anzahl;

    public ErntePreis(GemüseTyp gemüseTyp, int anzahl) {
        this.gemüseTyp = gemüseTyp;
        this.anzahl = anzahl;
    }

    public GemüseTyp getGemüseTyp() {
        return gemüseTyp;
    }

    public int getAnzahl() {
        return anzahl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ErntePreis that = (ErntePreis) o;
        return anzahl == that.anzahl && Objects.equals(gemüseTyp, that.gemüseTyp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gemüseTyp, anzahl);
    }
}
