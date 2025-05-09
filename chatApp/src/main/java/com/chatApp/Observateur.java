package com.chatApp;

import java.sql.SQLOutput;

public interface Observateur {
    void afficherGUI();
    void recevoirMessage(Message message);
    String getUser();

}
