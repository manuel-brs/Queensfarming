package de.dhbw.ase;

import de.dhbw.ase.Gemüse.GemüseTyp;
import de.dhbw.ase.Kachel.*;

import java.util.Collections;
import java.util.Stack;

public class Kachelstapel {
    Stack<Kachel> spielstapel = new Stack<>();

    public Kachelstapel(int anzahlgärten, int anzahlfelder, int anzahlwälder, int anzahlgroßwälder, int anzahlgroßfelder, GemüseTyp[] gemüsetypen) {
        for (int i = 0; i < anzahlfelder; i++) {
            this.spielstapel.push(new Feld(new GemüseTyp[]{gemüsetypen[0], gemüsetypen[1]}));
        }
        for (int i = 0; i < anzahlgärten; i++) {
            this.spielstapel.push(new Garten(new GemüseTyp[]{gemüsetypen[1], gemüsetypen[2]}));
        }
        for (int i = 0; i < anzahlwälder; i++) {
            this.spielstapel.push(new Wald(new GemüseTyp[]{gemüsetypen[3]}));
        }
        for (int i = 0; i < anzahlgroßwälder; i++) {
            this.spielstapel.push(new Großerwald(new GemüseTyp[]{gemüsetypen[3]}));
        }
        for (int i = 0; i < anzahlgroßfelder; i++) {
            this.spielstapel.push(new Großesfeld(new GemüseTyp[]{gemüsetypen[0], gemüsetypen[1]}));
        }
        Collections.shuffle(spielstapel);
    }

    public void mischeStapel() {
        Collections.shuffle(spielstapel);
    }

    public Kachel zieheKachel() {
        return spielstapel.pop();
    }
}