package repository;

import de.dhbw.ase.entities.Spieler;
import de.dhbw.ase.repository.SpielerManagerRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SpielerManagerRepositoryImplTest {

    private SpielerManagerRepositoryImpl repository;

    @BeforeEach
    void setUp() {
        repository = new SpielerManagerRepositoryImpl();
    }

    @Test
    void testSpeicherUndLadeSpieler() {
        // Arrange
        Spieler spieler = new Spieler(1, "TestSpieler", 100);
        int spielerId = 1;

        // Act
        repository.speicherSpieler(spielerId, spieler);
        Spieler geladen = repository.ladeSpieler(spielerId);

        // Assert
        assertNotNull(geladen);
        assertSame(spieler, geladen);
    }

    @Test
    void testLadeAlleSpieler() {
        // Arrange
        Spieler spieler1 = new Spieler(1, "TestSpieler1", 100);
        Spieler spieler2 = new Spieler(2, "TestSpieler2", 100);
        repository.speicherSpieler(1, spieler1);
        repository.speicherSpieler(2, spieler2);

        // Act
        List<Spieler> alleSpieler = repository.ladeAlleSpieler();

        // Assert
        assertEquals(2, alleSpieler.size());
        assertTrue(alleSpieler.contains(spieler1));
        assertTrue(alleSpieler.contains(spieler2));
    }

    @Test
    void testSpeicherSpielerThrowsOnNegativeId() {
        Spieler spieler = new Spieler(1, "TestSpieler", 100);
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
                repository.speicherSpieler(-1, spieler)
        );
        assertEquals("Spieler ID muss positiv sein", ex.getMessage());
    }

    @Test
    void testSpeicherSpielerThrowsOnNullSpieler() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
                repository.speicherSpieler(1, null)
        );
        assertEquals("Spieler darf nicht null sein", ex.getMessage());
    }

    @Test
    void testLadeSpielerThrowsOnNegativeId() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
                repository.ladeSpieler(-1)
        );
        assertEquals("Spieler ID muss positiv sein", ex.getMessage());
    }

    @Test
    void testLadeSpielerThrowsWhenNotFound() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
                repository.ladeSpieler(99)
        );
        assertEquals("Spieler nicht gefunden", ex.getMessage());
    }
}

