package de.dhbw.ase;

import de.dhbw.ase.Gemüse.GemüseTyp;
import de.dhbw.ase.Kachel.BebaubareKachel;
import de.dhbw.ase.Kachel.Kachel;
import de.dhbw.ase.Kachel.Scheune;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.util.HashMap;
import java.util.Map;

import static javax.swing.BorderFactory.createLineBorder;

public class GUI implements IObserver {
    static private JTextField x;
    static private JTextField y;
    static private JLabel output;
    static private JButton plant;
    static private TextArea barn;
    static private JFrame frame;
    static private TextArea[][] kachel;
    private final SpielController gameController;
    static JLabel currentPlayerLabel;
    private final Markt markt;
    private final Map<GemüseTyp, JCheckBox> gemüseCheckboxen = new HashMap<>();
    private Spiel spiel;

    private GemüseTyp selectedGemüseTyp;

    public GUI(SpielController controller, Markt markt, Spiel spiel) {
        this.gameController = controller;
        this.gameController.registerObserver(this);
        this.markt = markt;
        this.spiel = spiel;
        initComponents();
    }

    private void initComponents() {
        frame = new JFrame();

        // Kachel initialisieren
        kachel = new TextArea[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                kachel[i][j] = new TextArea();
                kachel[i][j].setBackground(Color.white);
                kachel[i][j].setEnabled(false);
                kachel[i][j].setEditable(false);
            }
        }

        currentPlayerLabel = new JLabel();
        setzeAktuellerSpieler(spiel.getSpieler().get(0));
        currentPlayerLabel.setBounds(50, 20, 250, 30);

        // Scheune anzeigen
        barn = new TextArea();
        barn.setBackground(Color.white);
        barn.setBounds(50, 50, 250, 400);
        barn.setEnabled(false);

        // Output Label
        output = new JLabel();
        output.setBounds(50, 500, 250, 50);

        // Buttons
        plant = new JButton("plant");
        plant.setBounds(50, 700, 100, 30);
        plant.addActionListener(this::plant);

        JButton harvest = new JButton("harvest");
        harvest.setBounds(150, 700, 100, 30);
        harvest.addActionListener(this::harvest);

        JButton sell = new JButton("sell");
        sell.setBounds(250, 700, 100, 30);
        sell.addActionListener(this::sell);

        JButton buyland = new JButton("buy land");
        buyland.setBounds(350, 700, 100, 30);
        buyland.addActionListener(this::buyland);

        JButton buyvegetable = new JButton("buy vegetable");
        buyvegetable.setBounds(450, 700, 130, 30);
        buyvegetable.addActionListener(this::buyvegetable);

        JButton changeplayer = new JButton("endturn");
        changeplayer.setBounds(50, 600, 100, 30);
        changeplayer.addActionListener(this::changePlayer);

        // Koordinaten
        JLabel lx = new JLabel("x:");
        lx.setBounds(590, 700, 30, 30);
        x = new JTextField();
        x.setBounds(600, 700, 30, 30);

        JLabel ly = new JLabel("y:");
        ly.setBounds(640, 700, 30, 30);
        y = new JTextField();
        y.setBounds(650, 700, 30, 30);

        // Checkboxen für Gemüsearten
        int checkboxYPosition = 700;
        for (GemüseTyp gemüseTyp : markt.getGemüsearten()) {
            JCheckBox checkBox = new JCheckBox(gemüseTyp.getGemüsename() + " (" + gemüseTyp.getPreis() + " gold)");
            checkBox.setBounds(700, checkboxYPosition, 200, 20);
            checkBox.addItemListener(e -> handleCheckboxSelection(e, gemüseTyp));
            gemüseCheckboxen.put(gemüseTyp, checkBox);
            frame.add(checkBox);
            checkboxYPosition += 30;
        }

        // Spielfeld
        JPanel boardField = new JPanel();
        boardField.setBackground(Color.white);
        boardField.setBounds(350, 20, 600, 600);
        boardField.setBorder(createLineBorder(null));
        boardField.setLayout(new GridLayout(5, 5, 0, 0));
        for (TextArea[] row : kachel) {
            for (TextArea area : row) {
                boardField.add(area);
            }
        }

        // Frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(1000, 1000);
        frame.setVisible(true);
        frame.add(boardField);
        frame.add(barn);
        frame.add(plant);
        frame.add(harvest);
        frame.add(sell);
        frame.add(buyland);
        frame.add(buyvegetable);
        frame.add(x);
        frame.add(y);
        frame.add(lx);
        frame.add(ly);
        frame.add(output);
        frame.add(changeplayer);
        frame.add(currentPlayerLabel);

        maleSpielfeldNeu(spiel.getSpieler().get(spiel.getSpieleramzug()).getSpielfeld());
    }

    private void handleCheckboxSelection(ItemEvent e, GemüseTyp gemüseTyp) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            gemüseCheckboxen.forEach((key, checkBox) -> {
                if (!key.equals(gemüseTyp)) checkBox.setSelected(false);
            });
            selectedGemüseTyp = gemüseTyp;
        } else if (selectedGemüseTyp == gemüseTyp) {
            selectedGemüseTyp = null;
        }
    }

    private void plant(ActionEvent event) {
        if (spiel.getAktionszähler() >= 2) {
            spiel.setMessage("Ein Spieler darf nur 2 Aktionen durchführen!");
        } else if (selectedGemüseTyp == null) {
            spiel.setMessage("Bitte ein Gemüse auswählen!");
        } else {
            try {
                int x_ = Integer.parseInt(x.getText());
                int y_ = Integer.parseInt(y.getText());

                if (spiel.pflanzen(y_, x_, selectedGemüseTyp)) {
                    spiel.inkrementAktionszähler();
                }
            } catch (NumberFormatException e) {
                spiel.setMessage("Bitte gebe gültige Koordinaten ein!");
            }
        }

        gameController.notifyObservers();
    }


    private void harvest(ActionEvent event) {
        if (spiel.getAktionszähler() < 2) {
            if (selectedGemüseTyp == null) {
                spiel.setMessage("Bitte ein Gemüse auswählen!");
                gameController.notifyObservers();
                return;
            }
            if (spiel.ernten(Integer.parseInt(y.getText()), Integer.parseInt(x.getText()))) {
                spiel.inkrementAktionszähler();
            }
        } else {
            spiel.setMessage("Ein Spieler darf nur 2 Aktionen durchführen!");
        }
        gameController.notifyObservers();
    }

    private void sell(ActionEvent event) {
        if (spiel.getAktionszähler() < 2) {
            if (selectedGemüseTyp == null) {
                spiel.setMessage("Bitte ein Gemüse auswählen!");
                gameController.notifyObservers();
                return;
            }
            if (spiel.verkaufeGemüse(selectedGemüseTyp)) {
                spiel.inkrementAktionszähler();
            }
        } else {
            spiel.setMessage("Ein Spieler darf nur 2 Aktionen durchführen!");
        }
        gameController.notifyObservers();
    }

    private void buyland(ActionEvent event) {
        if (spiel.getAktionszähler() >= 2) {
            spiel.setMessage("Ein Spieler darf nur 2 Aktionen durchführen!");
        } else {
            try {
                int x_ = Integer.parseInt(x.getText());
                int y_ = Integer.parseInt(y.getText());

                if (spiel.kaufeLand(y_, x_)) {
                    spiel.inkrementAktionszähler();
                }
            } catch (NumberFormatException e) {
                spiel.setMessage("Bitte gebe gültige Koordinaten ein!");
            }
        }

        gameController.notifyObservers();
    }


    private void buyvegetable(ActionEvent event) {
        if (spiel.getAktionszähler() >= 2) {
            spiel.setMessage("Ein Spieler darf nur 2 Aktionen durchführen!");
        } else if (selectedGemüseTyp == null) {
            spiel.setMessage("Bitte ein Gemüse auswählen!");
        } else if (spiel.kaufeGemüse(selectedGemüseTyp)) {
            spiel.inkrementAktionszähler();
        }

        gameController.notifyObservers();
    }


    private void changePlayer(ActionEvent event) {
        spiel.beendeZug();
        maleSpielfeldNeu(spiel.getAktuellerSpieler().getSpielfeld());
        setzeAktuellerSpieler(spiel.getAktuellerSpieler());
    }

    private void setzeAktuellerSpieler(Spieler aktuellerSpieler) {
        currentPlayerLabel.setText("Spieler: " + aktuellerSpieler.name + " ist am Zug");
    }

    private void maleSpielfeldNeu(Spielfeld spielfeld) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (spielfeld.getSpielfeld()[i][j] instanceof BebaubareKachel) {
                    BebaubareKachel currentkachel = (BebaubareKachel) spielfeld.getSpielfeld()[i][j];
                    String angebautText = (currentkachel.getAngebaut() != null)
                            ? currentkachel.getAngebaut().getGemüsename()
                            : "";

                    String text  = currentkachel.getName() + "\nangebaut: " + angebautText +
                            "\nkapazität: " + currentkachel.getWachstumsstatus() + "/" + currentkachel.getKapazität();

                    kachel[i][j].setText(text);
                    kachel[i][j].setBackground(Color.gray);
                    kachel[i][j].setEnabled(true);
                } else if (spielfeld.getSpielfeld()[i][j] instanceof Scheune) {
                    kachel[i][j].setText(spielfeld.getSpielfeld()[i][j].getName());
                    kachel[i][j].setBackground(Color.gray);
                    kachel[i][j].setEnabled(true);
                } else {
                    kachel[i][j].setText("");
                    kachel[i][j].setBackground(Color.white);
                    kachel[i][j].setEnabled(false);
                }
            }
        }

        // Scheune und Gold aktualisieren
        Scheune scheune = (Scheune) spiel.getAktuellerSpieler().getSpielfeld().getSpielfeld()[4][2];
        StringBuilder sb = new StringBuilder();
        for (GemüseTyp gemüsetyp : markt.getGemüsearten()) {
            sb.append(gemüsetyp.getGemüsename())
                    .append(": ")
                    .append(scheune.getInventar().getOrDefault(gemüsetyp, -1))
                    .append("\n\n");
        }
        sb.append("Gold: ").append(spiel.getAktuellerSpieler().anzahlGold);
        barn.setText(sb.toString());

        for (Map.Entry<GemüseTyp, JCheckBox> entry : gemüseCheckboxen.entrySet()) {
            GemüseTyp gemüseTyp = entry.getKey();
            JCheckBox checkBox = entry.getValue();
            String neuerText = gemüseTyp.getGemüsename() + " (" + entry.getKey().getPreis() + " gold)";
            checkBox.setText(neuerText);
        }


        frame.revalidate();
        frame.repaint();
    }

    @Override
    public void update() {
        String message = spiel.getMessage();
        if (!message.isEmpty()) {
            JOptionPane popup = new JOptionPane(message, JOptionPane.INFORMATION_MESSAGE);
            JDialog dialog = popup.createDialog(frame, "Nachricht");

            // Starte einen Timer, um das Pop-up nach 3 Sekunden zu schließen
            new Timer(5000, e -> dialog.dispose()).start();

            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        }
        maleSpielfeldNeu(spiel.getAktuellerSpieler().getSpielfeld());
        spiel.setMessage("");
    }
}
