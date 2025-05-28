package de.dhbw.ase;


import de.dhbw.ase.entities.Gemuese.Gemuese;
import de.dhbw.ase.entities.Gemuese.GemueseTyp;
import de.dhbw.ase.valueobjects.KaufErgebnis;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class KaufErgebnisTest {

    // KaufErgebnis ist der neue Preis aus dem Markt entnommen

    @Test
    void create_valid_kaufergebnis() {
        Gemuese tomate = new Gemuese(new GemueseTyp("Tomate", 1, 5, 3));
        KaufErgebnis ergebnis = new KaufErgebnis(tomate, 150);

        assertEquals(tomate, ergebnis.getGemüse());
        assertEquals(150, ergebnis.getPreis());
    }

    @Test
    void create_kaufergebnis_with_null_gemüse_should_throw() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            new KaufErgebnis(null, 100);
        });
        assertTrue(ex.getMessage().contains("Gemüse"));
    }

    @Test
    void create_kaufergebnis_with_negative_preis_should_throw() {
        Gemuese tomate = new Gemuese(new GemueseTyp("Tomate", 1, 5, 3));
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            new KaufErgebnis(tomate, -10);
        });
        assertTrue(ex.getMessage().contains("Preis"));
    }

    @Test
    void create_kaufergebnis_with_preis_out_of_bounce() {
        Gemuese tomate = new Gemuese(new GemueseTyp("Tomate", 1, 5, 3));
        KaufErgebnis kaufErgebnis = new KaufErgebnis(tomate, 10);
        int minPreis = tomate.getGemüse().getMinpreis();
        int maxPreis = tomate.getGemüse().getMaxpreis();
        assertFalse( kaufErgebnis.getPreis() >= minPreis && kaufErgebnis.getPreis() <= maxPreis, "Preis sollte im gültigen Bereich liegen.");
    }

    @Test
    void create_kaufergebnis_with_preis_in_bounce() {
        Gemuese tomate = new Gemuese(new GemueseTyp("Tomate", 1, 5, 3));
        KaufErgebnis kaufErgebnis = new KaufErgebnis(tomate, 4);
        int minPreis = tomate.getGemüse().getMinpreis();
        int maxPreis = tomate.getGemüse().getMaxpreis();
        assertTrue(kaufErgebnis.getPreis() >= minPreis && kaufErgebnis.getPreis() <= maxPreis, "Preis sollte im gültigen Bereich liegen.");
    }

    @Test
    void test_equals_and_hashCode() {
        Gemuese karotte = new Gemuese(new GemueseTyp("Tomate", 1, 5, 3));
        KaufErgebnis e1 = new KaufErgebnis(karotte, 50);
        KaufErgebnis e2 = new KaufErgebnis(karotte, 50);
        KaufErgebnis e3 = new KaufErgebnis(new Gemuese(new GemueseTyp("Tomate", 1, 5, 3)), 30);

        assertEquals(e1, e2);
        assertEquals(e1.hashCode(), e2.hashCode());
        assertNotEquals(e1, e3);
    }
}
