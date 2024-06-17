import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener {
    static final int SCREEN_WIDTH = 600;
    static final int SCREEN_HEIGHT = 600;
    static final int UNIT_SIZE = 25;
    static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / UNIT_SIZE;
    static final int DELAY = 75;
    static final int x[] = new int[GAME_UNITS];
    static final int y[] = new int[GAME_UNITS];
    int bodyPArts = 6;
    int applesEatens = 0;
    int appleX;
    int appleY;
    char direction = 'R';
    boolean running = false;
    Random random;
    Timer timer;


    public GamePanel() {
        random = new Random();


        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        startGame();


    }

    public void startGame() {
        newApple();
        running = true;
        timer = new Timer(DELAY, this);
        timer.start();


    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);

    }

    public void draw(Graphics g) {
        if (running) {
            for (int i = 0; i < SCREEN_HEIGHT / UNIT_SIZE; i++) {
                for (int j = 0; j < SCREEN_WIDTH / UNIT_SIZE; j++) {
                    //g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_HEIGHT);
                    //g.drawLine(0, i * UNIT_SIZE, SCREEN_HEIGHT, i * UNIT_SIZE);
                }

            }
            g.setColor(Color.RED);
            g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);

            for (int i = 0; i < bodyPArts; i++) {
                if (i == 0) {
                    g.setColor(Color.GREEN);
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                } else {
                    g.setColor(new Color(80, 200, 120));
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                }
            }
            g.setColor(Color.RED);
            g.setFont(new Font("Ink Free", Font.BOLD, 50));
            FontMetrics metrics = getFontMetrics(g.getFont());
            g.drawString("Score " + applesEatens, (SCREEN_WIDTH - metrics.stringWidth("Score " + applesEatens)) / 2, g.getFont().getSize());
        } else {
            gameOver(g);
        }

    }

    public void newApple() {
        appleX = random.nextInt((int) (SCREEN_WIDTH / UNIT_SIZE)) * (UNIT_SIZE);
        System.out.println(appleX);
        appleY = random.nextInt((int) (SCREEN_HEIGHT / UNIT_SIZE)) * (UNIT_SIZE);
        System.out.println(appleY);

    }

    public void move() {
        for (int i = bodyPArts; i > 0; i--) {     // la tete est en position 0
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }
        switch (direction) {
            case 'U':
                y[0] = y[0] - UNIT_SIZE;
                break;
            case 'D':
                y[0] = y[0] + UNIT_SIZE;
                break;
            case 'R':
                x[0] = x[0] + UNIT_SIZE;
                break;
            case 'L':
                x[0] = x[0] - UNIT_SIZE;
                break;
        }
    }

    public void checkApple() {
        if ((x[0] == appleX) && (y[0] == appleY)) {
            bodyPArts++;
            applesEatens++;
            newApple();
        }

    }

    public void checkCollisions() {
        // verifie si la tete se prend le corps ou pas
        for (int i = bodyPArts; i > 0; i--) {
            if ((x[0] == x[i]) && y[0] == y[i]) {
                running = false;
            }
        }
        if (x[0] < 0) {
            running = false;
        }
        if (x[0] > SCREEN_WIDTH) {
            running = false;
        }
        if (y[0] < 0) {
            running = false;
        }
        if (y[0] > SCREEN_HEIGHT) {
            running = false;
        }
        if (!running) {
            timer.stop();
        }

    }

    public void gameOver(Graphics g) {
        //Game Over
        g.setColor(Color.RED);
        g.setFont(new Font("Ink Free", Font.BOLD, 75));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("Game Over", (SCREEN_WIDTH - metrics.stringWidth("Game Over")) / 2, SCREEN_HEIGHT / 2);

        g.setColor(Color.RED);
        g.setFont(new Font("Ink Free", Font.BOLD, 50));
        FontMetrics score = getFontMetrics(g.getFont());
        g.drawString("Score " + applesEatens, (SCREEN_WIDTH - score.stringWidth("Score " + applesEatens)) / 2, g.getFont().getSize());

    }

    public void actionPerformed(ActionEvent e) {
        if (running) {
            move();
            checkApple();
            checkCollisions();

        }
        repaint();


    }

    public void restartGame() {
        applesEatens = 0;
        bodyPArts = 6;
        direction = 'R';
        running = true;
        x[0] = 0;
        y[0] = 0;
        newApple();
        timer.start();
        repaint();
    }

    public class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    if (direction != 'R') {
                        direction = 'L';
                        break;
                    }
                case KeyEvent.VK_RIGHT:
                    if (direction != 'L') {
                        direction = 'R';
                        break;
                    }
                case KeyEvent.VK_UP:
                    if (direction != 'D') {
                        direction = 'U';
                        break;
                    }
                case KeyEvent.VK_DOWN:
                    if (direction != 'U') {
                        direction = 'D';
                        break;
                    }
                case KeyEvent.VK_ENTER: {
                    restartGame();
                    break;
                }


            }
        }

    }


}
