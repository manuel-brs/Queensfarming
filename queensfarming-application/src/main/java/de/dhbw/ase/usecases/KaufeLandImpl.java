package de.dhbw.ase.usecases;

import de.dhbw.ase.entities.Kachel.Scheune;
import de.dhbw.ase.entities.Markt;
import de.dhbw.ase.entities.Spiel;
import de.dhbw.ase.entities.Spieler;
import de.dhbw.ase.entities.Spielfeld;
import de.dhbw.ase.repositories.MarktRepository;
import de.dhbw.ase.repositories.SpielRepository;
import de.dhbw.ase.repositories.SpielerManagerRepository;
import de.dhbw.ase.repositories.SpielfeldRepository;
import de.dhbw.ase.valueobjects.KachelPreis;

public class KaufeLandImpl implements KaufeLand {

    private final SpielRepository spielRepository;
    private final SpielfeldRepository spielfeldRepository;
    private final MarktRepository marktRepository;
    private final SpielerManagerRepository spielerManagerRepository;

    public KaufeLandImpl(SpielRepository spielRepository, SpielfeldRepository spielfeldRepository, MarktRepository marktRepository, SpielerManagerRepository spielerManagerRepository) {
        this.spielRepository = spielRepository;
        this.spielfeldRepository = spielfeldRepository;
        this.marktRepository = marktRepository;
        this.spielerManagerRepository = spielerManagerRepository;
    }

    @Override
    public void kaufeLand(int posY, int posX) throws Exception {
        Spiel spiel = spielRepository.get();
        Spieler spieler = spielerManagerRepository.ladeSpieler(spiel.getSpielerAmZug());
        Spielfeld spielfeld = spielfeldRepository.ladeSpielfeld(spieler);
        Markt markt = marktRepository.get();

        posX = 4- posX;
        posY = posY +2;

        int distanz = spielfeld.berechneScheunenDistanz(posX, posY);

        if (spielfeld.getSpielfeld()[posX][posY] != null) {
            throw new Exception("Hier ist bereits eine Kachel!");
        }

        KachelPreis kachelPreis = markt.kaufeLand(distanz);
        if (spieler.getAnzahlGold() >= kachelPreis.getPreis()) {
            spieler.setAnzahlGold(spieler.getAnzahlGold() - kachelPreis.getPreis());
            spielfeld.getSpielfeld()[posX][posY] = kachelPreis.getKachel();
        } else {
            markt.getKachelstapel().legeKachelZurück(kachelPreis.getKachel());
            markt.getKachelstapel().mischeStapel();
        }
    }
}
