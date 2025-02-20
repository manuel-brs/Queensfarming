package de.dhbw.ase.Gemüse;

public enum Gemüsename {
    KAROTTEN(5),
    SALAT(3),
    TOMATEN(4),
    PILZE(2);
    final int rundenzumwachsen;

    Gemüsename(int rundenzumwachsen) {
        this.rundenzumwachsen = rundenzumwachsen;
    }
}
