package com.chatApp;

public class UserFactory {
    public static Observateur creer(String type, String nom){
        return switch (type.toLowerCase()) {
            case "console"-> new ConsoleUser(nom);
            case "mobile" -> new MobileUser(nom);
            default -> throw new IllegalArgumentException("Type inconnu: "+type);
        };
    }
}
