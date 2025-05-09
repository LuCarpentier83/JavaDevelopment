package com.chatApp;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Observateur lucas = UserFactory.creer("mobile","Lucas");
        Observateur camille = UserFactory.creer("console", "Camille");

        MessageStation.getInstance().ajouterUser(lucas);
        MessageStation.getInstance().ajouterUser(camille);

        lucas.afficherGUI();
        camille.afficherGUI();

    }
}