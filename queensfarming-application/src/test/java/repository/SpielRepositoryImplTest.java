package repository;

import de.dhbw.ase.entities.Spiel;
import de.dhbw.ase.exceptions.GameNotFoundException;
import de.dhbw.ase.repository.SpielRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SpielRepositoryImplTest {

    private SpielRepositoryImpl repository;

    @BeforeEach
    void setUp() {
        repository = new SpielRepositoryImpl();
    }

    @Test
    void testSaveAndGet() throws GameNotFoundException {
        // Arrange
        Spiel spiel = new Spiel(List.of("Player1", "Player2"), 200);

        // Act
        Spiel savedSpiel = repository.save(spiel);
        Spiel retrievedSpiel = repository.get();

        // Assert
        assertNotNull(savedSpiel);
        assertNotNull(retrievedSpiel);
        assertSame(savedSpiel, retrievedSpiel);
    }

    @Test
    void testGetThrowsWhenNoGameSaved() {
        // Act & Assert
        GameNotFoundException exception = assertThrows(GameNotFoundException.class, () -> repository.get());
        assertEquals("No game found. Please start a new game.", exception.getMessage());
    }

    @Test
    void testDeleteRemovesGame() throws GameNotFoundException {
        // Arrange
        Spiel spiel = new Spiel(List.of("Player1", "Player2"), 200);
        repository.save(spiel);

        // Act
        repository.delete();

        // Assert
        assertThrows(GameNotFoundException.class, () -> repository.get());
    }

    @Test
    void testDeleteThrowsWhenNoGameSaved() {
        // Act & Assert
        GameNotFoundException exception = assertThrows(GameNotFoundException.class, () -> repository.delete());
        assertEquals("No game found to delete. Please start a new game.", exception.getMessage());
    }
}

