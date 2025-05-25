package de.dhbw.ase.usecases;

import de.dhbw.ase.entities.Gemüse.GemüseTyp;
import de.dhbw.ase.entities.Kachel.Scheune;
import de.dhbw.ase.entities.Markt;
import de.dhbw.ase.entities.Spiel;
import de.dhbw.ase.entities.Spieler;
import de.dhbw.ase.entities.Spielfeld;
import de.dhbw.ase.repositories.MarktRepository;
import de.dhbw.ase.repositories.SpielRepository;
import de.dhbw.ase.repositories.SpielerManagerRepository;
import de.dhbw.ase.repositories.SpielfeldRepository;
import de.dhbw.ase.valueobjects.KaufErgebnis;

public class HandelGemüseImpl implements HandelGemüse {

    private final SpielRepository spielRepository;
    private final SpielfeldRepository spielfeldRepository;
    private final MarktRepository marktRepository;
    private final SpielerManagerRepository spielerManagerRepository;

    public HandelGemüseImpl(SpielRepository spielRepository, SpielfeldRepository spielfeldRepository, MarktRepository marktRepository, SpielerManagerRepository spielerManagerRepository) {
        this.spielRepository = spielRepository;
        this.spielfeldRepository = spielfeldRepository;
        this.marktRepository = marktRepository;
        this.spielerManagerRepository = spielerManagerRepository;
    }


    @Override
    public void kaufeGemüse(GemüseTyp gemüseTyp) throws Exception {
        Spiel spiel = spielRepository.get();
        Spieler spieler = spielerManagerRepository.ladeSpieler(spiel.getSpielerAmZug());
        Spielfeld spielfeld = spielfeldRepository.ladeSpielfeld(spieler);
        Markt markt = marktRepository.get();
        Scheune scheune = spielfeld.getScheune();

        KaufErgebnis kaufErgebnis = markt.kaufeGemüse(gemüseTyp);

        if (spieler.getAnzahlGold() < kaufErgebnis.getPreis()) {
            markt.verkaufeGemüse(gemüseTyp);
        }

        spieler.setAnzahlGold(spieler.getAnzahlGold() - kaufErgebnis.getPreis());
        scheune.getInventar().put(gemüseTyp, scheune.getInventar().getOrDefault(gemüseTyp, 0) + 1);
    }

    @Override
    public void verkaufeGemüse(GemüseTyp gemüseTyp) throws Exception {
        Spiel spiel = spielRepository.get();
        Spieler spieler = spielerManagerRepository.ladeSpieler(spiel.getSpielerAmZug());
        Spielfeld spielfeld = spielfeldRepository.ladeSpielfeld(spieler);
        Markt markt = marktRepository.get();
        Scheune scheune = spielfeld.getScheune();

        int anzahl = scheune.getInventar().getOrDefault(gemüseTyp, 0);

        if (anzahl < 1) {
            throw new Exception("Nicht genug Gemüse vorhanden.");
        }

        KaufErgebnis kaufErgebnis = markt.verkaufeGemüse(gemüseTyp);

        scheune.getInventar().put(gemüseTyp, anzahl - 1);
        spieler.setAnzahlGold(spieler.getAnzahlGold() + kaufErgebnis.getPreis());
    }
}
