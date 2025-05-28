package usecases;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import de.dhbw.ase.entities.Gemuese.GemueseTyp;
import de.dhbw.ase.entities.Kachel.BebaubareKachel;
import de.dhbw.ase.entities.Kachel.Kachel;
import de.dhbw.ase.entities.Kachel.Scheune;
import de.dhbw.ase.entities.Markt;
import de.dhbw.ase.entities.Spiel;
import de.dhbw.ase.entities.Spieler;
import de.dhbw.ase.entities.Spielfeld;
import de.dhbw.ase.repositories.MarktRepository;
import de.dhbw.ase.repositories.SpielRepository;
import de.dhbw.ase.repositories.SpielerManagerRepository;
import de.dhbw.ase.repositories.SpielfeldRepository;
import de.dhbw.ase.usecases.AgrarModulImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.Map;

public class AgrarModulImplTest {

    @Mock
    SpielRepository spielRepository;

    @Mock
    SpielfeldRepository spielfeldRepository;

    @Mock
    MarktRepository marktRepository;

    @Mock
    SpielerManagerRepository spielerManagerRepository;

    AgrarModulImpl agrarModul;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        agrarModul = new AgrarModulImpl(spielRepository, spielfeldRepository, marktRepository, spielerManagerRepository);
    }

    @Test
    void testAnbauenGemüseErfolgreich() throws Exception {
        // Mocks vorbereiten
        Spiel spiel = mock(Spiel.class);
        Spieler spieler = mock(Spieler.class);
        Spielfeld spielfeld = mock(Spielfeld.class);
        Markt markt = mock(Markt.class);
        Scheune scheune = mock(Scheune.class);

        // Gemeinsame GemüseTyp-Instanz
        GemueseTyp tomate = new GemueseTyp("Tomate", 1, 3, 2);

        // Inventar mit Tomate füllen
        Map<GemueseTyp, Integer> inventar = new HashMap<>();
        inventar.put(tomate, 2);

        when(spielRepository.get()).thenReturn(spiel);
        when(spiel.getSpielerAmZug()).thenReturn(1);
        when(spielerManagerRepository.ladeSpieler(1)).thenReturn(spieler);
        when(spielfeldRepository.ladeSpielfeld(spieler)).thenReturn(spielfeld);
        when(marktRepository.get()).thenReturn(markt);
        when(spielfeld.getScheune()).thenReturn(scheune);
        when(scheune.getInventar()).thenReturn(inventar);

        // Spielfeld mit Kacheln
        BebaubareKachel kachel = mock(BebaubareKachel.class);
        Kachel[][] kacheln = new Kachel[5][5];
        kacheln[4 - 1][1 + 2] = kachel;

        when(spielfeld.getSpielfeld()).thenReturn(kacheln);
        when(kachel.getAngebaut()).thenReturn(null);
        when(kachel.baueGemüseAn(tomate)).thenReturn(true);

        // Methode testen
        agrarModul.anbauenGemüse(1, 1, tomate);

        // Prüfen, ob Inventar reduziert wurde
        assertEquals(1, inventar.get(tomate));

        // Prüfen, dass baueGemüseAn aufgerufen wurde
        verify(kachel).baueGemüseAn(tomate);
    }


    @Test
    void testAnbauenGemüseNichtImInventar() throws Exception {
        Spiel spiel = mock(Spiel.class);
        Spieler spieler = mock(Spieler.class);
        Spielfeld spielfeld = mock(Spielfeld.class);
        Markt markt = mock(Markt.class);
        Scheune scheune = mock(Scheune.class);

        Map<GemueseTyp, Integer> inventar = new HashMap<>();

        when(spielRepository.get()).thenReturn(spiel);
        when(spiel.getSpielerAmZug()).thenReturn(1);
        when(spielerManagerRepository.ladeSpieler(1)).thenReturn(spieler);
        when(spielfeldRepository.ladeSpielfeld(spieler)).thenReturn(spielfeld);
        when(marktRepository.get()).thenReturn(markt);
        when(spielfeld.getScheune()).thenReturn(scheune);
        when(scheune.getInventar()).thenReturn(inventar);

        BebaubareKachel kachel = mock(BebaubareKachel.class);
        Kachel[][] kacheln = new Kachel[5][5];
        kacheln[4 - 1][1 + 2] = kachel;

        when(spielfeld.getSpielfeld()).thenReturn(kacheln);

        Exception exception = assertThrows(Exception.class, () -> {
            agrarModul.anbauenGemüse(1, 1, new GemueseTyp("Tomate", 1, 3, 2));
        });

        assertEquals("Das Gemüse ist nicht im Inventar!", exception.getMessage());
    }
}

