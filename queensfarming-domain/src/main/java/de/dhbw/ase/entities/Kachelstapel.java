package de.dhbw.ase.entities;

import de.dhbw.ase.entities.Gemüse.GemüseTyp;
import de.dhbw.ase.entities.Kachel.*;

import java.util.Collections;
import java.util.Stack;

public class Kachelstapel {
    Stack<Kachel> spielstapel = new Stack<>();

    public Kachelstapel(int anzahlgärten, int anzahlfelder, int anzahlwälder, int anzahlgroßwälder, int anzahlgroßfelder, GemüseTyp[] gemüsetypen) {
        for (int i = 0; i < anzahlfelder; i++) {
            this.spielstapel.push(new Feld(new GemüseTyp[]{gemüsetypen[0], gemüsetypen[1], gemüsetypen[4]}));
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
            this.spielstapel.push(new Großesfeld(new GemüseTyp[]{gemüsetypen[0], gemüsetypen[1], gemüsetypen[4]}));
        }
        Collections.shuffle(spielstapel);
        // just to be safe :)
        Collections.shuffle(spielstapel);
    }

    public Kachel zieheKachel() {
        return spielstapel.pop();
    }

    public void legeKachelZurück(Kachel kachel) {
        spielstapel.push(kachel);
    }

    public void mischeStapel() {
        Collections.shuffle(spielstapel);
    }

    public Stack<Kachel> getSpielstapel() {
        return spielstapel;
    }

    public void setSpielstapel(Stack<Kachel> spielstapel) {
        this.spielstapel = spielstapel;
    }
}