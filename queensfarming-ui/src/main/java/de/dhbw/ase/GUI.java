package de.dhbw.ase;

import de.dhbw.ase.Gemüse.Gemüsename;
import de.dhbw.ase.Kachel.Scheune;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import static javax.swing.BorderFactory.createLineBorder;

public class GUI {
    static private JTextField x;
    static private JTextField y;
    static private JLabel output;
    static private JButton plant;
    static private TextArea barn;
    static private JFrame frame;
    static private JCheckBox saladcheck;
    static private JCheckBox mushroomcheck;
    static private JCheckBox tomatocheck;
    static private JCheckBox carotcheck;
    static private TextArea kachel[][];
    private final SpielController gameController;
    static JLabel currentPlayerLabel;
    public GUI(SpielController gameController) {
        this.gameController = gameController;
        initComponents();
    }
    private void initComponents() {
        //Kachel
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
        setzeAktuellerSpieler(this.gameController.getSpiel().spieler.get(0));
        currentPlayerLabel.setBounds(50, 20, 250, 30);


        //textarea barn
        barn = new TextArea();
        Scheune scheune = (Scheune) gameController.getSpiel().spieler.get(gameController.getSpiel().spieleramzug).getSpielfeld().getSpielfeld()[4][2];
        barn.setText("Karotten: " + scheune.getInventar().get(Gemüsename.KAROTTEN) + "\n\nTomaten: " + scheune.getInventar().get(Gemüsename.TOMATEN) + " \n\nPilze: " + scheune.getInventar().get(Gemüsename.PILZE) + " \n\nSalat: " + scheune.getInventar().get(Gemüsename.SALAT) + "\n\nGold: " + gameController.getSpiel().aktuellerSpieler.anzahlGold);
        barn.setBackground(Color.white);
        barn.setBounds (50, 50, 250, 400);
        barn.setEnabled(false);


        //Output label
        output = new JLabel();
        output.setBounds(50, 500, 250, 50);


        //button actions
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
        buyvegetable.setBounds(450, 700, 100, 30);
        buyvegetable.addActionListener(this::buyvegetable);

        JButton changeplayer = new JButton("endturn");
        changeplayer.setBounds(50, 600, 100, 30);
        changeplayer.addActionListener(this::changePlayer);

        JLabel lx = new JLabel();
        lx.setText("x:");
        lx.setBounds(590, 700, 30, 30);
        x = new JTextField();
        x.setBounds(600, 700, 30, 30);
        JLabel ly = new JLabel();
        ly.setText("y:");
        ly.setBounds(640, 700, 30, 30);
        y = new JTextField();
        y.setBounds(650, 700, 30, 30);


        //vegetables
        saladcheck = new JCheckBox("salad (" + gameController.getSpiel().markt.getPreissalad() + " gold)");
        saladcheck.setBounds(700, 700, 200, 20);
        //saladcheck.setVisible(false);
        carotcheck = new JCheckBox("carot (" + gameController.getSpiel().markt.getPreiskarrote() + " gold)");
        carotcheck.setBounds(700, 750, 200, 20);
        //carotcheck.setVisible(false);
        mushroomcheck = new JCheckBox("mushroom (" + gameController.getSpiel().markt.getPreispilz() + " gold)");
        mushroomcheck.setBounds(700, 800, 200, 20);
        //mushroomcheck.setVisible(false);
        tomatocheck = new JCheckBox("tomato (" + gameController.getSpiel().markt.getPreistomate() + " gold)");
        tomatocheck.setBounds(700, 850, 200, 20);
        //tomatocheck.setVisible(false);


        // ActionListener für exklusive Auswahl
        ItemListener exclusiveSelectionListener = e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                saladcheck.setSelected(e.getSource() == saladcheck);
                carotcheck.setSelected(e.getSource() == carotcheck);
                mushroomcheck.setSelected(e.getSource() == mushroomcheck);
                tomatocheck.setSelected(e.getSource() == tomatocheck);
            }
        };

        saladcheck.addItemListener(exclusiveSelectionListener);
        carotcheck.addItemListener(exclusiveSelectionListener);
        mushroomcheck.addItemListener(exclusiveSelectionListener);
        tomatocheck.addItemListener(exclusiveSelectionListener);


        //Field
        JPanel boardField = new JPanel () ;
        boardField.setBackground(Color.white);
        boardField.setBounds (350, 20, 600, 600);
        boardField.setBorder(createLineBorder(null));
        boardField.setLayout(new GridLayout(5, 5, 0, 0));
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                boardField.add(kachel[i][j]);
            }
        }


        //Frame
        frame = new JFrame();
        frame. setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout (null);
        frame.setSize(1000, 1000);
        frame.setVisible(true);
        frame.add (boardField);
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
        frame.add(saladcheck);
        frame.add(carotcheck);
        frame.add(mushroomcheck);
        frame.add(tomatocheck);
        frame.add(currentPlayerLabel);
        maleSpielfeldNeu(gameController.getSpiel().aktuellerSpieler.spielfeld);
    }

    private void plant(ActionEvent event) {
        int posX = Integer.parseInt(x.getText());
        int posY = Integer.parseInt(y.getText());
        if (saladcheck.isSelected()) gameController.pflanzen(posX, posY, Gemüsename.SALAT);
        if (carotcheck.isSelected()) gameController.pflanzen(posX, posY, Gemüsename.KAROTTEN);
        if (mushroomcheck.isSelected()) gameController.pflanzen(posX, posY, Gemüsename.PILZE);
        if (tomatocheck.isSelected()) gameController.pflanzen(posX, posY, Gemüsename.TOMATEN);
    }

    private void harvest(ActionEvent event) {
        int posX = Integer.parseInt(x.getText());
        int posY = Integer.parseInt(y.getText());
        gameController.ernten(posX, posY);
    }

    private void sell(ActionEvent event) {
        if (saladcheck.isSelected()) gameController.verkaufeGemüse(Gemüsename.SALAT);
        if (carotcheck.isSelected()) gameController.verkaufeGemüse(Gemüsename.KAROTTEN);
        if (mushroomcheck.isSelected()) gameController.verkaufeGemüse(Gemüsename.PILZE);
        if (tomatocheck.isSelected()) gameController.verkaufeGemüse(Gemüsename.TOMATEN);
    }

    private void buyland(ActionEvent event) {
        int posX = Integer.parseInt(x.getText());
        int posY = Integer.parseInt(y.getText());
        gameController.kaufeLand(posX, posY);
    }

    private void buyvegetable(ActionEvent event) {
        if (saladcheck.isSelected()) gameController.kaufeGemüse(Gemüsename.SALAT);
        if (carotcheck.isSelected()) gameController.kaufeGemüse(Gemüsename.KAROTTEN);
        if (mushroomcheck.isSelected()) gameController.kaufeGemüse(Gemüsename.PILZE);
        if (tomatocheck.isSelected()) gameController.kaufeGemüse(Gemüsename.TOMATEN);
        maleSpielfeldNeu(this.gameController.getSpiel().aktuellerSpieler.spielfeld);
    }

    private void changePlayer(ActionEvent event) {
        gameController.wechselSpieler();
        Scheune scheune = (Scheune) gameController.getSpiel().spieler.get(gameController.getSpiel().spieleramzug).getSpielfeld().getSpielfeld()[4][2];
        barn.setText("Karotten: " + scheune.getInventar().get(Gemüsename.KAROTTEN) + "\n\nTomaten: " + scheune.getInventar().get(Gemüsename.TOMATEN) + " \n\nPilze: " + scheune.getInventar().get(Gemüsename.PILZE) + " \n\nSalat: " + scheune.getInventar().get(Gemüsename.SALAT) + "\n\nGold: " + gameController.getSpiel().aktuellerSpieler.anzahlGold);
        setzeAktuellerSpieler(this.gameController.getSpiel().spieler.get(gameController.getSpiel().spieleramzug));
        maleSpielfeldNeu(this.gameController.getSpiel().spieler.get(gameController.getSpiel().spieleramzug).spielfeld);
    }

    private void setzeAktuellerSpieler(Spieler aktuellerSpieler) {
        currentPlayerLabel.setText("Spieler: " + aktuellerSpieler.name + " ist am Zug");
    }

    private void maleSpielfeldNeu(Spielfeld spielfeld) {
        for (int i = 0; i < spielfeld.getSpielfeld().length; i++) {
            for (int j = 0; j < spielfeld.getSpielfeld()[i].length; j++) {
                if(spielfeld.getSpielfeld()[i][j] != null) {
                    kachel[i][j].setText(spielfeld.getSpielfeld()[i][j].getName());
                    kachel[i][j].setBackground(Color.gray);
                    kachel[i][j].setEnabled(true);
                }
            }
        }
        Scheune scheune = (Scheune) gameController.getSpiel().spieler.get(gameController.getSpiel().spieleramzug).getSpielfeld().getSpielfeld()[4][2];
        barn.setText("Karotten: " + scheune.getInventar().get(Gemüsename.KAROTTEN) + "\n\nTomaten: " + scheune.getInventar().get(Gemüsename.TOMATEN) + " \n\nPilze: " + scheune.getInventar().get(Gemüsename.PILZE) + " \n\nSalat: " + scheune.getInventar().get(Gemüsename.SALAT) + "\n\nGold: " + gameController.getSpiel().aktuellerSpieler.anzahlGold);

        //vegetables
        saladcheck.setText("salad (" + gameController.getSpiel().markt.getPreissalad() + " gold)");
        carotcheck.setText("carot (" + gameController.getSpiel().markt.getPreiskarrote() + " gold)");
        mushroomcheck.setText("mushroom (" + gameController.getSpiel().markt.getPreispilz() + " gold)");
        tomatocheck.setText("tomato (" + gameController.getSpiel().markt.getPreistomate() + " gold)");

        frame.revalidate();
        frame.repaint();
    }
}