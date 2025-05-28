package de.dhbw.ase;

import de.dhbw.ase.entities.Gemuese.GemueseTyp;
import de.dhbw.ase.valueobjects.ErntePreis;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ErntePreisTest {

    // Ernte Preis ist der neue Preis des volatilen Marktes
    @Test
    void create_valid_erntepreis() {
        GemueseTyp typ = new GemueseTyp("Tomate", 1, 5, 3);
        ErntePreis ernte = new ErntePreis(typ, 5);

        assertEquals(typ, ernte.getGemüseTyp());
        assertEquals(5, ernte.getAnzahl());
    }

    @Test
    void create_erntepreis_with_null_gemüsetyp_should_throw() {
        assertThrows(IllegalArgumentException.class, () -> {
            new ErntePreis(null, 5);
        });
    }

    @Test
    void create_erntepreis_with_negative_anzahl_should_throw() {
        GemueseTyp typ = new GemueseTyp("Tomate", 1, 5, 3);
        assertThrows(IllegalArgumentException.class, () -> {
            new ErntePreis(typ, -1);
        });
    }

    @Test
    void equals_and_hashcode_should_work_properly() {
        GemueseTyp typ = new GemueseTyp("Tomate", 1, 5, 3);
        ErntePreis ep1 = new ErntePreis(typ, 10);
        ErntePreis ep2 = new ErntePreis(typ, 10);
        ErntePreis ep3 = new ErntePreis(new GemueseTyp("Tomate", 1, 5, 3), 3);

        assertEquals(ep1, ep2);
        assertEquals(ep1.hashCode(), ep2.hashCode());
        assertNotEquals(ep1, ep3);
    }
}

