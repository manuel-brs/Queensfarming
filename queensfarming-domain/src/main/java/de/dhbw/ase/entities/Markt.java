package de.dhbw.ase.entities;

import de.dhbw.ase.entities.Gemuese.Gemuese;
import de.dhbw.ase.entities.Gemuese.GemueseTyp;
import de.dhbw.ase.valueobjects.KachelPreis;
import de.dhbw.ase.valueobjects.KaufErgebnis;

public class Markt {
    private GemueseTyp[] gemüsearten;
    private Kachelstapel kachelstapel;

    public Markt() {
        this.gemüsearten = new GemueseTyp[]{new GemueseTyp("KAROTTEN", 1, 3, 2), new GemueseTyp("SALAT", 2, 6, 4), new GemueseTyp("TOMATEN", 3, 9,6), new GemueseTyp("PILZE", 11, 21, 16), new GemueseTyp("GETREIDE", 1, 5, 2)};
        this.kachelstapel = new Kachelstapel(5, 5, 5, 5, 5, this.gemüsearten);
    }

    public KaufErgebnis kaufeGemüse(GemueseTyp gemueseTyp) {
        Gemuese gemuese = new Gemuese(gemueseTyp);
        int preis = gemueseTyp.getPreis();
        gemueseTyp.erhöhePreis();
        return new KaufErgebnis(gemuese, preis);
    }

    public KaufErgebnis verkaufeGemüse(GemueseTyp gemueseTyp) {
        Gemuese gemuese = new Gemuese(gemueseTyp);
        int preis = gemueseTyp.getPreis();
        gemueseTyp.verringerePreis();
        return new KaufErgebnis(gemuese, preis);
    }

    public KachelPreis kaufeLand(int scheunendistanz) {
        return new KachelPreis(kachelstapel.zieheKachel(), scheunendistanz*10);
    }

    public GemueseTyp[] getGemüsearten() {
        return gemüsearten;
    }

    public void setGemüsearten(GemueseTyp[] gemüsearten) {
        this.gemüsearten = gemüsearten;
    }

    public Kachelstapel getKachelstapel() {
        return kachelstapel;
    }

    public void setKachelstapel(Kachelstapel kachelstapel) {
        this.kachelstapel = kachelstapel;
    }
}