package com.chatApp;

import javax.swing.*;

public class ConsoleUser implements Observateur{

    private final String user;
    private ChatGUI screen;
    public ConsoleUser(String user){
        this.user = user;
    }
    public void afficherGUI(){
        this.screen = new ChatGUI(this);
        JFrame frame = new JFrame(this.user);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(screen);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    @Override
    public String toString() {
        return this.user;
    }

    @Override
    public String getUser(){
        return this.user;
    }
    @Override
    public void recevoirMessage(Message message) {
        this.screen.afficherMessage(message);
    }
}
