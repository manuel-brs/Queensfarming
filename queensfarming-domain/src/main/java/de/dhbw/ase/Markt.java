package de.dhbw.ase;

import de.dhbw.ase.Gemüse.Gemüse;
import de.dhbw.ase.Gemüse.GemüseTyp;
import de.dhbw.ase.Kachel.Kachel;
import de.dhbw.ase.ValueObject.KachelPreis;
import de.dhbw.ase.ValueObject.KaufErgebnis;

public class Markt {
    private GemüseTyp[] gemüsearten;
    private Kachelstapel kachelstapel;

    public Markt() {
        this.gemüsearten = new GemüseTyp[]{new GemüseTyp("KAROTTEN", 1, 3, 2), new GemüseTyp("SALAT", 2, 6, 4), new GemüseTyp("TOMATEN", 3, 9,6), new GemüseTyp("PILZE", 11, 21, 16), new GemüseTyp("GETREIDE", 1, 5, 2)};
        this.kachelstapel = new Kachelstapel(5, 5, 5, 5, 5, this.gemüsearten);
        this.kachelstapel.mischeStapel();
    }

    public KachelPreis kaufeLand(int scheunendistanz) {
        return new KachelPreis(kachelstapel.zieheKachel(), scheunendistanz*10);
    }

    public KaufErgebnis kaufeGemüse(GemüseTyp gemüseTyp) {
        Gemüse gemüse = new Gemüse(gemüseTyp);
        int preis = gemüseTyp.getPreis();
        gemüseTyp.erhöhePreis();
        return new KaufErgebnis(gemüse, preis);
    }

    public KaufErgebnis verkaufeGemüse(GemüseTyp gemüseTyp) {
        Gemüse gemüse = new Gemüse(gemüseTyp);
        int preis = gemüseTyp.getPreis();
        gemüseTyp.verringerePreis();
        return new KaufErgebnis(gemüse, preis);
    }

    public GemüseTyp[] getGemüsearten() {
        return gemüsearten;
    }

    public Kachelstapel getKachelstapel() {
        return kachelstapel;
    }
}