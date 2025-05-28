package de.dhbw.ase.valueobjects;

import de.dhbw.ase.entities.Kachel.Kachel;

import java.util.Objects;

public class KachelPreis {
    private final Kachel kachel;
    private final int preis;

    public KachelPreis(Kachel kachel, int preis) {
        if (kachel == null) {
            throw new IllegalArgumentException("Kachel darf nicht null sein.");
        }
        if (preis < 0) {
            throw new IllegalArgumentException("Preis darf nicht negativ sein.");
        }
        this.kachel = kachel;
        this.preis = preis;
    }

    public Kachel getKachel() {
        return kachel;
    }

    public int getPreis() {
        return preis;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KachelPreis that = (KachelPreis) o;
        return preis == that.preis && Objects.equals(kachel, that.kachel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(kachel, preis);
    }

    @Override
    public String toString() {
        return "KachelPreis{" + "kachel=" + kachel + ", preis=" + preis + '}';
    }
}