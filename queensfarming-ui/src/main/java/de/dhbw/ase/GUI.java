package de.dhbw.ase;

import de.dhbw.ase.Gemüse.GemüseTyp;
import de.dhbw.ase.Kachel.BebaubareKachel;
import de.dhbw.ase.Kachel.Scheune;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

import static javax.swing.BorderFactory.createLineBorder;

public class GUI implements IObserver {
    static private JLabel output;
    static private JButton plant;
    static private TextArea barn;
    static private JFrame frame;
    static private JPanel fabrikPanel;
    static private JLabel fabrikLabel;
    static private JButton upgradeButton;
    static private JButton sellProdukt;
    static private JTextField fabriktextfield;
    static private TextArea[][] kachel;
    private final SpielController gameController;
    static JLabel currentPlayerLabel;
    private final Markt markt;
    private final Map<GemüseTyp, JCheckBox> gemüseCheckboxen = new HashMap<>();
    private Spiel spiel;

    private GemüseTyp selectedGemüseTyp;
    private int selectedX = -1;
    private int selectedY = -1;

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
                kachel[i][j].setEnabled(true);
                kachel[i][j].setEditable(false);
                int finalI = i;
                int finalJ = j;
                kachel[i][j].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        handleKachelClick(finalI, finalJ);
                    }
                });
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

        fabrikPanel = new JPanel();
        fabrikPanel.setLayout(new BoxLayout(fabrikPanel, BoxLayout.Y_AXIS));
        fabrikPanel.setBounds(960, 20, 300, 600);

        fabrikLabel = new JLabel("Fabrik");
        fabrikLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        fabrikPanel.add(fabrikLabel);

        fabriktextfield = new JTextField();
        fabriktextfield.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        fabriktextfield.setEditable(false);
        fabrikPanel.add(fabriktextfield);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));

        upgradeButton = new JButton("upgrade "+spiel.getSpieler().get(spiel.getSpieleramzug()).getFabrik().getKostenupdate());
        upgradeButton.addActionListener(e -> upgradeFabrik(fabriktextfield, upgradeButton));
        buttonPanel.add(upgradeButton);

        sellProdukt = new JButton("Finished Product");
        sellProdukt.addActionListener(e -> sellProdukt(fabriktextfield, sellProdukt));
        buttonPanel.add(sellProdukt);

        fabrikPanel.add(buttonPanel);

        // Frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(1000, 1000);
        frame.setVisible(true);
        frame.add(boardField);
        frame.add(barn);
        frame.add(plant);
        frame.add(fabrikPanel);
        frame.add(harvest);
        frame.add(sell);
        frame.add(buyland);
        frame.add(buyvegetable);
        frame.add(output);
        frame.add(changeplayer);
        frame.add(currentPlayerLabel);

        maleSpielfeldNeu(spiel.getSpieler().get(spiel.getSpieleramzug()).getSpielfeld());
    }

    private void handleKachelClick(int x, int y) {
        if (x == 4 && y == 2) {
            return;
        }
        if (selectedX != -1 && selectedY != -1) {
            kachel[selectedX][selectedY].setBackground(Color.white);
        }
        selectedX = x;
        selectedY = y;
        kachel[selectedX][selectedY].setBackground(Color.lightGray);
    }

    private void sellProdukt(JTextField fabriktextfield, JButton sellProdukt) {
        if (spiel.getAktionszähler() >= 4) {
            spiel.setMessage("Ein Spieler darf nur 4 Aktionen durchführen!");
        } else {
            spiel.sellProdukt();
            spiel.inkrementAktionszähler();
        }
        gameController.notifyObservers();
    }

    private void upgradeFabrik(JTextField mitarbeiterField, JButton upgradeButton) {
        if (spiel.getAktionszähler() >= 4) {
            spiel.setMessage("Ein Spieler darf nur 4 Aktionen durchführen!");
        } else {
            spiel.upgradeFabrik();
            spiel.inkrementAktionszähler();
        }
        gameController.notifyObservers();
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
        if (spiel.getAktionszähler() >= 4) {
            spiel.setMessage("Ein Spieler darf nur 4 Aktionen durchführen!");
        } else if (selectedGemüseTyp == null) {
            spiel.setMessage("Bitte ein Gemüse auswählen!");
        } else if (selectedX == -1 || selectedY == -1) {
            spiel.setMessage("Bitte ein Feld auswählen!");
        } else {
            if (spiel.pflanzen(selectedX, selectedY, selectedGemüseTyp)) {
                spiel.inkrementAktionszähler();
            }
        }

        gameController.notifyObservers();
    }

    private void harvest(ActionEvent event) {
        if (spiel.getAktionszähler() < 4) {
            if (selectedX == -1 || selectedY == -1) {
                spiel.setMessage("Bitte ein Feld auswählen!");
                gameController.notifyObservers();
                return;
            }
            if (spiel.ernten(selectedX, selectedY)) {
                spiel.inkrementAktionszähler();
            }
        } else {
            spiel.setMessage("Ein Spieler darf nur 4 Aktionen durchführen!");
        }
        gameController.notifyObservers();
    }

    private void sell(ActionEvent event) {
        if (spiel.getAktionszähler() < 4) {
            if (selectedGemüseTyp == null) {
                spiel.setMessage("Bitte ein Gemüse auswählen!");
                gameController.notifyObservers();
                return;
            }
            if (spiel.verkaufeGemüse(selectedGemüseTyp)) {
                spiel.inkrementAktionszähler();
            }
        } else {
            spiel.setMessage("Ein Spieler darf nur 4 Aktionen durchführen!");
        }
        gameController.notifyObservers();
    }

    private void buyland(ActionEvent event) {
        if (spiel.getAktionszähler() >= 4) {
            spiel.setMessage("Ein Spieler darf nur 4 Aktionen durchführen!");
        } else if (selectedX == -1 || selectedY == -1) {
            spiel.setMessage("Bitte ein Feld auswählen!");
        } else {
            if (spiel.kaufeLand(selectedX, selectedY)) {
                spiel.inkrementAktionszähler();
            }
        }

        gameController.notifyObservers();
    }

    private void buyvegetable(ActionEvent event) {
        if (spiel.getAktionszähler() >= 4) {
            spiel.setMessage("Ein Spieler darf nur 4 Aktionen durchführen!");
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
                    kachel[i][j].setEnabled(true);
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