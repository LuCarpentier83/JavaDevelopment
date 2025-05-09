package com.chatApp;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ChatGUI extends JPanel {
    private JTextArea message;
    private JTextField input;
    private JButton send;
    private JButton reset;
    private Observateur user;
    private JComboBox<Observateur> dest;
    public ChatGUI(Observateur obs){

        message = new JTextArea(10,30);
        message.setEditable(true);
        input = new JTextField("", 30);
        reset = new JButton("Reset");
        send = new JButton("Send");

        this.user = obs;

        java.util.List<Observateur> autres = new ArrayList<>(MessageStation.getInstance().getUser(this.user));
        this.dest = new JComboBox<>(autres.toArray(new Observateur[0]));

        this.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.add(input);
        inputPanel.add(dest);
        inputPanel.add(send);
        inputPanel.add(reset);

        this.add(new JScrollPane(message), BorderLayout.CENTER);
        this.add(inputPanel, BorderLayout.SOUTH);

        reset.addActionListener( (event) -> {
            try {
                this.message.setText("");
            }
            catch(Error e){
                JOptionPane.showMessageDialog(this,e);
            }
        });
        send.addActionListener( (event) -> {
            try {
                String texte = input.getText();
                Observateur chosen = (Observateur) dest.getSelectedItem();
                Message message = new Message(texte, this.user, chosen);
                MessageStation.getInstance().recevoirMessage(message);
                this.afficherMessage(message);
                input.setText("");
                this.revalidate();
                this.repaint();
            }
            catch(Error e){
                JOptionPane.showMessageDialog(this,e);
            }
        });
    }
    public void
    afficherMessage(Message msg) {
        this.message.append(msg.getExp().getUser() + " → " + msg.getText() + "\n");
    }


}
