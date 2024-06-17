import java.awt.Component;
import javax.swing.JFrame;

@SuppressWarnings("SpellCheckingInspection")

public class Fenetre extends JFrame {
    Labyrinthe panel = new Labyrinthe();
    //Astar panel = new Astar();

    public Fenetre() {
        this.setResizable(true);
        this.setLocationRelativeTo((Component)null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.add(this.panel);
        this.pack();
        this.setAlwaysOnTop(true);
        this.setVisible(true);
    }

}
