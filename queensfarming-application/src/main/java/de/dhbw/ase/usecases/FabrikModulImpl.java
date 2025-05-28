package de.dhbw.ase.usecases;

import de.dhbw.ase.entities.Fabrik;
import de.dhbw.ase.entities.Kachel.Scheune;
import de.dhbw.ase.entities.Spiel;
import de.dhbw.ase.entities.Spieler;
import de.dhbw.ase.exceptions.GameNotFoundException;
import de.dhbw.ase.repositories.FabrikRepository;
import de.dhbw.ase.repositories.SpielRepository;
import de.dhbw.ase.repositories.SpielerManagerRepository;
import de.dhbw.ase.repositories.SpielfeldRepository;
import de.dhbw.ase.valueobjects.Produkt;
import de.dhbw.ase.entities.ProduktMenge;

import java.util.Map;

public class FabrikModulImpl implements FabrikModul {
    private final FabrikRepository fabrikRepository;
    private final SpielerManagerRepository spielerManagerRepository;
    private final SpielRepository spielRepository;

    public FabrikModulImpl(FabrikRepository fabrikRepository, SpielerManagerRepository spielerManagerRepository, SpielRepository spielRepository) {
        this.fabrikRepository = fabrikRepository;
        this.spielerManagerRepository = spielerManagerRepository;
        this.spielRepository = spielRepository;
    }

    @Override
    public void upgradeFabrik(FabrikRepository fabrikRepository, Spieler spieler) {
        try {
            Fabrik fabrik = fabrikRepository.ladeFabrik(spieler);
            if (fabrik.getKostenupdate() <= spieler.getAnzahlGold()) {
                fabrik.setKostenupdate(
                        fabrik.getKostenupdate() + 2
                );
                fabrik.setAnzahlArbeiter(
                        fabrik.getAnzahlArbeiter() + 1
                );
            } else {
                throw new Exception("Nicht genug Gold zum Upgraden der Fabrik.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    @Override
    public void produziere(String produkt, SpielfeldRepository spielfeldRepository, SpielRepository spielRepository, SpielerManagerRepository spielerManagerRepository) {
        try {
            Spiel spiel = spielRepository.get();
            Spieler spieler = spielerManagerRepository.ladeSpieler(spiel.getSpielerAmZug());
            Fabrik fabrik = fabrikRepository.ladeFabrik(spieler);
            Scheune scheune = spielfeldRepository.ladeSpielfeld(spieler).getScheune();
            Map<String, Integer> rezept = fabrik.getREZEPTUREN().get(produkt);

            boolean genügendZutaten = rezept.entrySet().stream().anyMatch(entry ->
                    scheune.getInventar().entrySet().stream().anyMatch(k -> {
                        if (k.getKey().getGemüsename().equals(entry.getKey())) {
                            System.out.println("Vorhanden: " + k.getValue() + " benötigt: " + entry.getValue());
                        }
                        return k.getKey().getGemüsename().equals(entry.getKey()) && k.getValue() >= entry.getValue();
                    })
            );
            if (fabrik.getAnzahlArbeiter() > 0 && genügendZutaten) {
                fabrik.setAnzahlArbeiter(
                        fabrik.getAnzahlArbeiter() -1
                );
                rezept.forEach((zutat, menge) -> {
                    scheune.getInventar().entrySet().stream()
                            .filter(entry -> entry.getKey().getGemüsename().equals(zutat))
                            .findFirst()
                            .ifPresent(entry -> {
                                int alterWert = entry.getValue();
                                int neueMenge = alterWert - menge;

                                scheune.getInventar().put(entry.getKey(), neueMenge);
                            });
                });
                fabrik.getProdukteinbearbeitung().add(new ProduktMenge(new Produkt(produkt, 10, 3),3));
                System.out.println("Produktion erfolgreich.");
            } else {
                throw new Exception("Nicht genug Arbeiter oder Zutaten für die Produktion.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void verkaufeProdukte(FabrikRepository fabrikRepository, SpielRepository spielRepository, SpielerManagerRepository spielerManagerRepository) throws GameNotFoundException {
        Spiel spiel = spielRepository.get();
        Spieler spieler = spielerManagerRepository.ladeSpieler(spiel.getSpielerAmZug());
        Fabrik fabrik = fabrikRepository.ladeFabrik(spieler);

        int goldGewinn = 0;
        for ( Produkt produkt : fabrik.getFertigeProdukte()){
            goldGewinn += produkt.getPreis();
        }
        fabrik.getFertigeProdukte().clear();
        spieler.setAnzahlGold(
                spieler.getAnzahlGold() + goldGewinn
        );
    }
}