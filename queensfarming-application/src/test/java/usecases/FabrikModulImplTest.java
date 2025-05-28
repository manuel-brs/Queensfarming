package usecases;

import de.dhbw.ase.entities.Fabrik;
import de.dhbw.ase.entities.Gemuese.GemueseTyp;
import de.dhbw.ase.entities.Kachel.Scheune;
import de.dhbw.ase.entities.Spiel;
import de.dhbw.ase.entities.Spieler;
import de.dhbw.ase.entities.Spielfeld;
import de.dhbw.ase.entities.ProduktMenge;
import de.dhbw.ase.exceptions.GameNotFoundException;
import de.dhbw.ase.repositories.FabrikRepository;
import de.dhbw.ase.repositories.SpielRepository;
import de.dhbw.ase.repositories.SpielerManagerRepository;
import de.dhbw.ase.repositories.SpielfeldRepository;
import de.dhbw.ase.usecases.FabrikModulImpl;
import de.dhbw.ase.valueobjects.Produkt;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FabrikModulImplTest {

    private FabrikRepository fabrikRepository;
    private SpielerManagerRepository spielerManagerRepository;
    private SpielRepository spielRepository;
    private SpielfeldRepository spielfeldRepository;
    private FabrikModulImpl fabrikModul;

    @BeforeEach
    void setUp() {
        fabrikRepository = mock(FabrikRepository.class);
        spielerManagerRepository = mock(SpielerManagerRepository.class);
        spielRepository = mock(SpielRepository.class);
        spielfeldRepository = mock(SpielfeldRepository.class);
        fabrikModul = new FabrikModulImpl(fabrikRepository, spielerManagerRepository, spielRepository);
    }

    @Test
    void testUpgradeFabrikErfolgreich() {
        Spieler spieler = new Spieler(1, "TestSpieler", 100);
        spieler.setAnzahlGold(10);

        Fabrik fabrik = new Fabrik();
        fabrik.setKostenupdate(5);
        fabrik.setAnzahlArbeiter(2);

        when(fabrikRepository.ladeFabrik(spieler)).thenReturn(fabrik);

        fabrikModul.upgradeFabrik(fabrikRepository, spieler);

        assertEquals(7, fabrik.getKostenupdate());
        assertEquals(3, fabrik.getAnzahlArbeiter());
    }

    @Test
    void testProduziereErfolgreich() throws GameNotFoundException {
        Spiel spiel = mock(Spiel.class);
        Spieler spieler = new Spieler(1, "TestSpieler", 100);
        spieler.setAnzahlGold(10);

        Fabrik fabrik = new Fabrik();
        fabrik.setAnzahlArbeiter(1);
        Map<String, Integer> rezept = new HashMap<>();
        rezept.put("Karotte", 2);
        fabrik.getREZEPTUREN().put("Saft", rezept);

        GemueseTyp karotte = new GemueseTyp("Karotte", 1, 2, 1);
        Map<GemueseTyp, Integer> inventar = new HashMap<>();
        inventar.put(karotte, 5);

        Scheune scheune = mock(Scheune.class);
        when(scheune.getInventar()).thenReturn(inventar);

        Spielfeld spielfeld = mock(Spielfeld.class);
        when(spielfeld.getScheune()).thenReturn(scheune);

        when(spielRepository.get()).thenReturn(spiel);
        when(spiel.getSpielerAmZug()).thenReturn(1);
        when(spielerManagerRepository.ladeSpieler(1)).thenReturn(spieler);
        when(fabrikRepository.ladeFabrik(spieler)).thenReturn(fabrik);
        when(spielfeldRepository.ladeSpielfeld(spieler)).thenReturn(spielfeld);

        fabrikModul.produziere("Saft", spielfeldRepository, spielRepository, spielerManagerRepository);

        assertEquals(0, fabrik.getAnzahlArbeiter());
        assertEquals(3, inventar.get(karotte));
        assertEquals(1, fabrik.getProdukteinbearbeitung().size());
        ProduktMenge pm = fabrik.getProdukteinbearbeitung().get(0);
        assertEquals("Saft", pm.getProdukt().getName());
        assertEquals(3, pm.getRundenZumBacken());
    }

    @Test
    void testVerkaufeProdukte() throws GameNotFoundException {
        Spiel spiel = mock(Spiel.class);
        Spieler spieler = new Spieler(1, "TestSpieler", 100);
        spieler.setAnzahlGold(0);

        Produkt produkt1 = new Produkt("Saft", 10, 3);
        Produkt produkt2 = new Produkt("Chips", 20, 2);

        Fabrik fabrik = new Fabrik();
        fabrik.getFertigeProdukte().add(produkt1);
        fabrik.getFertigeProdukte().add(produkt2);

        when(spielRepository.get()).thenReturn(spiel);
        when(spiel.getSpielerAmZug()).thenReturn(1);
        when(spielerManagerRepository.ladeSpieler(1)).thenReturn(spieler);
        when(fabrikRepository.ladeFabrik(spieler)).thenReturn(fabrik);

        fabrikModul.verkaufeProdukte(fabrikRepository, spielRepository, spielerManagerRepository);

        assertEquals(30, spieler.getAnzahlGold());
        assertTrue(fabrik.getFertigeProdukte().isEmpty());
    }
}
