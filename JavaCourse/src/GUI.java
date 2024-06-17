package src;

import javax.swing.JOptionPane;

public class GUI {
    public static void main(String[] args) {
        String name = JOptionPane.showInputDialog("Enter your name");
        JOptionPane.showMessageDialog(null, "Hello " + name);

        int age = Integer.parseInt(JOptionPane.showInputDialog("How old are you"));
        JOptionPane.showMessageDialog(null, "You are " + age + " years old");

        double height = Double.parseDouble(JOptionPane.showInputDialog("How tall are you"));
        JOptionPane.showMessageDialog(null, "You are " + height + " cm tall");

        double weight = Double.parseDouble(JOptionPane.showInputDialog("How much do you weigh (in kilograms)"));
        JOptionPane.showMessageDialog(null, "You weigh " + weight + " kg");

        String smokerInput = JOptionPane.showInputDialog("Do you smoke? (true or false)");
        boolean isSmoker = Boolean.parseBoolean(smokerInput);

        if (isSmoker) {
            JOptionPane.showMessageDialog(null, "You smoke");
        } else {
            JOptionPane.showMessageDialog(null, "You do not smoke");
        }
    }
}

