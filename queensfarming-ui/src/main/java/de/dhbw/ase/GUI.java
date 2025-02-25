package de.dhbw.ase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import static javax.swing.BorderFactory.createLineBorder;

public class GUI {
    static private JTextField x;
    static private JTextField y;
    static private JLabel output;
    static private JButton plant;
    static private TextArea barn;
    static private JFrame frame;
    static private int carot = 0;
    static private int salad = 0;
    static private int mushroom = 0;
    static private int tomato = 0;
    static private JCheckBox saladcheck;
    static private JCheckBox mushroomcheck;
    static private JCheckBox tomatocheck;
    static private JCheckBox carotcheck;
    static private TextArea kachel[][];
    static int numberOfPlayers;
    static int goldstart;
    static int goldwin;
    static int currentPlayer = 0;
    String name[];
    private static boolean tooManyActions = false;
    private static int vegetableSold[] = new int[4];

    public GUI() {
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
            }
        }

        //textarea barn
        barn = new TextArea();
        barn.setText("Carot: \n\nTomato :\n\nMushroom :\n\nSalad:\n\nsum:\n\ngold:");
        barn.setBackground(Color.white);
        barn.setBounds (50, 50, 250, 400);
        barn.setEnabled(false);


        //Output label
        output = new JLabel();
        output.setBounds(50, 500, 250, 50);


        //button actions
        plant = new JButton("plant");
        plant.setBounds(50, 700, 100, 30);
        //plant.addActionListener(this::plant);

        JButton harvest = new JButton("harvest");
        harvest.setBounds(150, 700, 100, 30);
        //harvest.addActionListener(this::harvest);

        JButton sell = new JButton("sell");
        sell.setBounds(250, 700, 100, 30);
        //sell.addActionListener(this::sell);

        JButton buyland = new JButton("buy land");
        buyland.setBounds(350, 700, 100, 30);
        //buyland.addActionListener(this::buyland);

        JButton buyvegetable = new JButton("buy vegetable");
        buyvegetable.setBounds(450, 700, 100, 30);
        //buyvegetable.addActionListener(this::buyvegetable);

        JButton changeplayer = new JButton("endturn");
        changeplayer.setBounds(50, 600, 100, 30);
        //changeplayer.addActionListener(this::changePlayer);

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
        saladcheck = new JCheckBox("salad");
        saladcheck.setBounds(700, 700, 100, 20);
        //saladcheck.setVisible(false);
        carotcheck = new JCheckBox("carot");
        carotcheck.setBounds(700, 750, 100, 20);
        //carotcheck.setVisible(false);
        mushroomcheck = new JCheckBox("mushroom");
        mushroomcheck.setBounds(700, 800, 100, 20);
        //mushroomcheck.setVisible(false);
        tomatocheck = new JCheckBox("tomato");
        tomatocheck.setBounds(700, 850, 100, 20);
        //tomatocheck.setVisible(false);



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
    }


}