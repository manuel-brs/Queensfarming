package de.dhbw.ase.usecases;

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
import de.dhbw.ase.valueobjects.ErntePreis;

public class AgrarModulImpl implements AgrarModul {

    private final SpielRepository spielRepository;
    private final SpielfeldRepository spielfeldRepository;
    private final MarktRepository marktRepository;
    private final SpielerManagerRepository spielerManagerRepository;

    public AgrarModulImpl(SpielRepository spielRepository, SpielfeldRepository spielfeldRepository, MarktRepository marktRepository, SpielerManagerRepository spielerManagerRepository) {
        this.spielRepository = spielRepository;
        this.spielfeldRepository = spielfeldRepository;
        this.marktRepository = marktRepository;
        this.spielerManagerRepository = spielerManagerRepository;
    }

    @Override
    public void anbauenGemüse(int posY, int posX, GemueseTyp gemüse) throws Exception {
        Spiel spiel = spielRepository.get();
        Spieler spieler = spielerManagerRepository.ladeSpieler(spiel.getSpielerAmZug());
        Spielfeld spielfeld = spielfeldRepository.ladeSpielfeld(spieler);
        Markt markt = marktRepository.get();
        Scheune scheune = spielfeld.getScheune();

        posX = 4- posX;
        posY = posY +2;

        Kachel kachel = spielfeld.getSpielfeld()[posX][posY];

        if (scheune.getInventar().getOrDefault(gemüse, 0) < 1) {
            throw new Exception("Das Gemüse ist nicht im Inventar!");
        }
        if (kachel == null) {
            throw new Exception("Die Kachel ist null!");
        }
        if (kachel instanceof Scheune) {
            throw new Exception("Die Kachel ist eine Scheune!");
        }
        if (((BebaubareKachel) kachel).getAngebaut() != null) {
            throw new Exception("Die Kachel ist bereits bebaut!");
        }

        if (!((BebaubareKachel) kachel).baueGemüseAn(gemüse)) {
            throw new Exception("Das Gemüse kann nicht angebaut werden!");
        }

        scheune.getInventar().put(gemüse, scheune.getInventar().get(gemüse) - 1);
    }

    @Override
    public void ernteGemüse(int posY, int posX) throws Exception {
        Spiel spiel = spielRepository.get();
        Spieler spieler = spielerManagerRepository.ladeSpieler(spiel.getSpielerAmZug());
        Spielfeld spielfeld = spielfeldRepository.ladeSpielfeld(spieler);
        Markt markt = marktRepository.get();
        Scheune scheune = spielfeld.getScheune();

        posX = 4- posX;
        posY = posY +2;

        Kachel kachel = spielfeld.getSpielfeld()[posX][posY];

        if (kachel == null) {
            throw new Exception("Die Kachel ist null!");
        }

        if (kachel instanceof Scheune) {
            throw new Exception("Die Kachel ist eine Scheune!");
        }

        ErntePreis geerntetesGemüse = ((BebaubareKachel) kachel).ernteKachel();
        if (geerntetesGemüse.getGemüseTyp() == null) {
            throw new Exception("Das Gemüse ist null!");
        }

        scheune.getInventar().put(geerntetesGemüse.getGemüseTyp(), scheune.getInventar().get(geerntetesGemüse.getGemüseTyp()) + geerntetesGemüse.getAnzahl());
        ((BebaubareKachel) kachel).resetWachstumsstatus();
    }
}
