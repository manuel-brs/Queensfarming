package de.dhbw.ase.ValueObject;

import de.dhbw.ase.Kachel.Kachel;

import java.util.Objects;

public class KachelPreis {
    final private Kachel kachel;
    final private int preis;

    public KachelPreis(Kachel kachel, int preis) {
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
}
