package de.dhbw.ase;

import de.dhbw.ase.Gemüse.Gemüse;
import de.dhbw.ase.Gemüse.Gemüsename;

public class Markt {
    int preiskarrote = 2;
    int preistomate = 6;
    int preissalad = 4;
    int preispilz = 16;

    public Markt() {

    }

    public Gemüse kaufeGemüse(Gemüsename gemüsename) throws Exception {
        if(gemüsename.equals(Gemüsename.PILZE)) {
            if(preispilz < 21 && preispilz > 11) {
                preispilz += 1;
            }
        } else if (gemüsename.equals(Gemüsename.SALAT)) {
            if(preissalad < 6 && preissalad > 2) {
                preissalad += 1;
            }
        } else if (gemüsename.equals(Gemüsename.TOMATEN)) {
            if(preistomate < 9 && preistomate > 3) {
                preistomate += 1;
            }
        } else if (gemüsename.equals(Gemüsename.KAROTTEN)) {
            if(preiskarrote < 3 && preiskarrote > 1) {
                preiskarrote += 1;
            }
        } else {
            throw new Exception();
        }
        return new Gemüse(gemüsename);
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
            throw new Exception();
        }
    }
}