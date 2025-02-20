package de.dhbw.ase;

import de.dhbw.ase.Kachel.*;

import java.util.Collections;
import java.util.Stack;

public class Kachelstapel {
    Stack<Kachel> spielstapel = new Stack<>();

    public Kachelstapel(int anzahlgärten, int anzahlfelder, int anzahlwälder, int anzahlgroßwälder, int anzahlgroßfelder) {
        for (int i = 0; i < anzahlfelder; i++) {
            this.spielstapel.push(new Feld());
        }
        for (int i = 0; i < anzahlgärten; i++) {
            this.spielstapel.push(new Garten());
        }
        for (int i = 0; i < anzahlwälder; i++) {
            this.spielstapel.push(new Wald());
        }
        for (int i = 0; i < anzahlgroßwälder; i++) {
            this.spielstapel.push(new Großerwald());
        }
        for (int i = 0; i < anzahlgroßfelder; i++) {
            this.spielstapel.push(new Großesfeld());
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