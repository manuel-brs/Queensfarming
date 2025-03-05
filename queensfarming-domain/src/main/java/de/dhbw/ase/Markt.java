package de.dhbw.ase;

import de.dhbw.ase.Gemüse.Gemüse;
import de.dhbw.ase.Gemüse.Gemüsename;

public class Markt {
    private int preiskarrote = 2;
    private int preistomate = 6;
    private int preissalad = 4;
    private int preispilz = 16;

    public Markt() {

    }

    public KaufErgebnis kaufeGemüse(Gemüsename gemüsename) throws Exception {
        int kosten = 0;
        if(gemüsename.equals(Gemüsename.PILZE)) {
            if(preispilz < 21 && preispilz > 11) {
                preispilz += 1;
                kosten = preispilz;
            }
        } else if (gemüsename.equals(Gemüsename.SALAT)) {
            if(preissalad < 6 && preissalad > 2) {
                preissalad += 1;
                kosten = preissalad;
            }
        } else if (gemüsename.equals(Gemüsename.TOMATEN)) {
            if(preistomate < 9 && preistomate > 3) {
                preistomate += 1;
                kosten = preistomate;
            }
        } else if (gemüsename.equals(Gemüsename.KAROTTEN)) {
            if(preiskarrote < 3 && preiskarrote > 1) {
                preiskarrote += 1;
                kosten = preiskarrote;
            }
        } else {
            throw new Exception("Gemüsename nicht erkannt");
        }
        return new KaufErgebnis(new Gemüse(gemüsename), kosten);
    }

    public void verkaufeGemüse(Gemüsename gemüsename) throws Exception {
        if(gemüsename.equals(Gemüsename.PILZE)) {
            if(preispilz < 21 && preispilz > 11) {
                preispilz -= 1;
            }
        } else if (gemüsename.equals(Gemüsename.SALAT)) {
            if(preissalad < 6 && preissalad > 2) {
                preissalad -= 1;
            }
        } else if (gemüsename.equals(Gemüsename.TOMATEN)) {
            if(preistomate < 9 && preistomate > 3) {
                preistomate -= 1;
            }
        } else if (gemüsename.equals(Gemüsename.KAROTTEN)) {
            if(preiskarrote < 3 && preiskarrote > 1) {
                preiskarrote -= 1;
            }
        } else {
            throw new Exception("Gemüsename nicht erkannt");
        }
    }

    public int getPreiskarrote() {
        return preiskarrote;
    }

    public int getPreistomate() {
        return preistomate;
    }

    public int getPreissalad() {
        return preissalad;
    }

    public int getPreispilz() {
        return preispilz;
    }
}