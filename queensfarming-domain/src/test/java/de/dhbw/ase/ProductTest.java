package de.dhbw.ase;

import de.dhbw.ase.valueobjects.Produkt;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {
    @Test
    void create_valid_produkt() {
        Produkt produkt = new Produkt("Brot", 200, 3);
        assertEquals("Brot", produkt.getName());
        assertEquals(200, produkt.getPreis());
        assertEquals(3, produkt.getRundenzumbacken());
    }

    @Test
    void create_invalid_produkt_with_null_name() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            new Produkt(null, 100, 2);
        });
        assertTrue(ex.getMessage().contains("Produktname"));
    }

    @Test
    void create_invalid_produkt_with_negative_preis() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            new Produkt("Brot", -1, 2);
        });
        assertTrue(ex.getMessage().contains("Preis"));
    }

    @Test
    void create_invalid_produkt_with_negative_rundenzumbacken() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            new Produkt("Brot", 100, -5);
        });
        assertTrue(ex.getMessage().contains("Runden"));
    }

    @Test
    void to_string_should_be_readable() {
        Produkt produkt = new Produkt("Brot", 200, 3);
        assertEquals("Brot (200€, 3 Runden)", produkt.toString());
    }

    @Test
    void test_equals_and_hashCode() {
        Produkt p1 = new Produkt("Brot", 200, 3);
        Produkt p2 = new Produkt("Brot", 200, 3);
        Produkt p3 = new Produkt("Kuchen", 300, 5);

        assertEquals(p1, p2);
        assertEquals(p1.hashCode(), p2.hashCode());
        assertNotEquals(p1, p3);
    }
}
