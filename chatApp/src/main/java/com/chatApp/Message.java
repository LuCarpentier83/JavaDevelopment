package com.chatApp;

public class Message {
    private String text;
    private Observateur exp;
    private Observateur dest;
    public Message(String text, Observateur exp, Observateur dest){
        this.dest = dest;
        this.exp = exp;
        this.text = text;
    }

    public String getText(){
        return this.text;
    }
    public Observateur getExp(){
        return this.exp;
    }
    public Observateur getDest(){
        return this.dest;
    }
}
