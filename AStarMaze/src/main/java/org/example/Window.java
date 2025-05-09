package org.example;

import javax.print.attribute.standard.JobName;
import javax.swing.*;
import java.awt.*;


public class Window extends JFrame {
    private Maze panel;
    private JTextField wallInput;
    private JButton generateButton;
    private JButton solverBFS;
    private JButton solverAStar;
    private JButton clear;
    private JLabel timeBFS;
    private JLabel timeAStar;
    public Window(){

        wallInput = new JTextField("20",5);
        generateButton = new JButton("Generate Maze");
        solverBFS = new JButton("Solve with BFS");
        solverAStar = new JButton("Solve with AStar");
        clear = new JButton("Clear");
        panel = new Maze(0.2);
        timeBFS = new JLabel("BFS duration: " + panel.bfsTime);
        timeAStar = new JLabel("BFS duration: " + panel.astarTime);

        JPanel timePanel = new JPanel();
        timePanel.add(timeBFS);
        timePanel.add(timeAStar);

        JPanel controlPanel = new JPanel();
        controlPanel.add(new JLabel("Wall percentage: "));
        controlPanel.add(wallInput);
        controlPanel.add(generateButton);
        controlPanel.add(solverBFS);
        controlPanel.add(solverAStar);
        controlPanel.add(clear);

        this.setLayout(new BorderLayout());
        this.add(controlPanel, BorderLayout.NORTH);
        this.add(panel, BorderLayout.SOUTH);
        this.add(timePanel, BorderLayout.EAST);

        configureScreen();
        panel.displayMaze();



        solverBFS.addActionListener( input -> {
            try {
                panel.breadthFirstSearch();
                this.revalidate();
                timeBFS.setText("BFS duration: "+panel.bfsTime);
                this.validate();
                this.repaint();



            } catch (Exception e) {
                JOptionPane.showMessageDialog(this,"Error while solving with BFS");
            }
        });

        clear.addActionListener( input -> {
            try{
                    panel.clearMaze();
                    timeBFS.setText("BFS duration: "+0);
                    timeAStar.setText("AStar duration: "+0);
                    this.revalidate();
                    this.repaint();
            }catch(Exception e){
                JOptionPane.showMessageDialog(this,"Error while clearing");
            }
        });
        solverAStar.addActionListener( input -> {
            try {
                panel.AStar();
                timeAStar.setText("AStar duration: "+panel.astarTime);
                this.revalidate();
                this.repaint();

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this,"Error while solving with Astar");
            }
        });


        generateButton.addActionListener( input -> {
            try {
                double percentage = Double.parseDouble(wallInput.getText())/100;
                this.remove(panel);
                panel = new Maze(percentage);
                panel.displayMaze();
                this.add(panel,BorderLayout.SOUTH);
                this.revalidate();
                this.repaint();

            } catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(this, "Not a valid number");
            }
        });
    }



    private void configureScreen(){
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();  // size will be the one of the panel
        this.setResizable(false);
        this.setLocationRelativeTo((Component) null);
        this.setAlwaysOnTop(true);
        this.setVisible(true);
    }
}
