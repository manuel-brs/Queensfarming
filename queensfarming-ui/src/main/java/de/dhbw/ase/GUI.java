package de.dhbw.ase;

import de.dhbw.ase.Gemüse.GemüseTyp;
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

    private GemüseTyp selectedGemüseTyp;

    public GUI(Markt markt) {
        this.gameController = SpielController.getInstance();
        this.gameController.registerObserver(this);
        this.markt = markt;
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
        setzeAktuellerSpieler(Spiel.getInstance().getSpieler().get(0));
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

        maleSpielfeldNeu(Spiel.getInstance().getSpieler().get(Spiel.getInstance().getSpieleramzug()).getSpielfeld());
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
        Spiel.getInstance().pflanzen(Integer.parseInt(x.getText()), Integer.parseInt(y.getText()), selectedGemüseTyp);
    }

    private void harvest(ActionEvent event) {
        Spiel.getInstance().ernten(Integer.parseInt(x.getText()), Integer.parseInt(y.getText()));
    }

    private void sell(ActionEvent event) {
        if (selectedGemüseTyp == null) {
            output.setText("Bitte ein Gemüse auswählen!");
            return;
        }
        Spiel.getInstance().verkaufeGemüse(selectedGemüseTyp);
    }

    private void buyland(ActionEvent event) {
        Spiel.getInstance().kaufeLand(Integer.parseInt(x.getText()), Integer.parseInt(y.getText()));
    }

    private void buyvegetable(ActionEvent event) {
        System.out.println("kaufe gemüse");
        if (selectedGemüseTyp == null) {
            output.setText("Bitte ein Gemüse auswählen!");
            return;
        }
        Spiel.getInstance().kaufeGemüse(selectedGemüseTyp);
    }

    private void changePlayer(ActionEvent event) {
        Spiel.getInstance().beendeZug();
        maleSpielfeldNeu(Spiel.getInstance().getAktuellerSpieler().getSpielfeld());
        setzeAktuellerSpieler(Spiel.getInstance().getAktuellerSpieler());
    }

    private void setzeAktuellerSpieler(Spieler aktuellerSpieler) {
        currentPlayerLabel.setText("Spieler: " + aktuellerSpieler.name + " ist am Zug");
    }

    private void maleSpielfeldNeu(Spielfeld spielfeld) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (spielfeld.getSpielfeld()[i][j] != null) {
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
        Scheune scheune = (Scheune) Spiel.getInstance().getAktuellerSpieler().getSpielfeld().getSpielfeld()[4][2];
        StringBuilder sb = new StringBuilder();
        for (GemüseTyp gemüsetyp : markt.getGemüsearten()) {
            sb.append(gemüsetyp.getGemüsename())
                    .append(": ")
                    .append(scheune.getInventar().getOrDefault(gemüsetyp, -1))
                    .append("\n\n");
        }
        sb.append("Gold: ").append(Spiel.getInstance().getAktuellerSpieler().anzahlGold);
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
        String message = Spiel.getInstance().getMessage();
        if (!message.isEmpty()) {
            JOptionPane popup = new JOptionPane(message, JOptionPane.INFORMATION_MESSAGE);
            JDialog dialog = popup.createDialog(frame, "Nachricht");

            // Starte einen Timer, um das Pop-up nach 3 Sekunden zu schließen
            new Timer(5000, e -> dialog.dispose()).start();

            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        }
        maleSpielfeldNeu(Spiel.getInstance().getAktuellerSpieler().getSpielfeld());
        Spiel.getInstance().setMessage("");
    }
}
