package com.chatApp;
import java.util.*;


public class MessageStation {
    private static MessageStation instance;
    private List<Observateur> observateurs = new ArrayList<>();
    private MessageStation(){}

    public static synchronized MessageStation getInstance(){
        if (instance == null){
            instance = new MessageStation();
        }
        return instance;
    }

    public List<Observateur> getUser(Observateur me){
        List<Observateur> users = new ArrayList<>();
        for(Observateur obs: observateurs){
            if(!obs.equals(me)){
            users.add(obs);
            }
        }
        return users;
    }

    public void ajouterUser(Observateur o ){
        observateurs.add(o);
    }

    public void recevoirMessage(Message msg){
        for(Observateur obs : observateurs){
            if ( !obs.equals(msg.getExp()) && obs.equals(msg.getDest())) {
                obs.recevoirMessage(msg);

            }
        }
    }
}
