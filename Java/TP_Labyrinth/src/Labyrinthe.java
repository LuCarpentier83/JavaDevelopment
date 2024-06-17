import java.awt.*;
import java.util.*;
import java.util.List;
import javax.swing.*;

public class Labyrinthe extends JPanel {
    final int windowSize = 300;
    final int gridSize = 10;
    int caseSize;
    Case[][] caseArray;
    Case caseEntree;
    Case caseSortie;
    List<Case> cheminBFS;  // Le chemin BFS Vector


    public Labyrinthe() {
        this.setPreferredSize(new Dimension(windowSize, windowSize));
        this.caseSize = windowSize / gridSize;
        this.caseArray = new Case[gridSize][gridSize];
        this.cheminBFS = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                double rand = Math.random();
                boolean isObstacle = rand <= 0.3;
                if ((i != 0 || j != 0) && (i != gridSize-1 || j != gridSize-1)) {
                    this.caseArray[i][j] = new Case(j * caseSize, i * caseSize, (j + 1) * caseSize, (i + 1) * caseSize, isObstacle);
                } else {
                    this.caseArray[i][j] = new Case(j * caseSize, i * caseSize, (j + 1) * caseSize, (i + 1) * caseSize, false);
                }
            }
        }

        this.caseEntree = this.caseArray[0][0];
        this.caseSortie = this.caseArray[gridSize -1][gridSize -1];
        bfs();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;

        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                Case currentCase = this.caseArray[i][j];
                if ((currentCase != caseEntree) && (currentCase != caseSortie)) {
                    for (Case caseBFS : cheminBFS) {
                        if ((caseBFS != caseEntree) && (caseBFS != caseSortie)) {
                            g2D.fillRect(caseBFS.x1, caseBFS.y1, caseSize - 2, caseSize - 2);
                        }
                    }
                    g2D.drawRect(currentCase.x1, currentCase.y1, currentCase.x2, currentCase.y2);
                    if (currentCase.isObstacle) {
                        g2D.setColor(Color.BLACK);
                        g2D.fillRect(currentCase.x1, currentCase.y1, caseSize - 2, caseSize - 2);
                    } else {
                        g2D.setColor(Color.WHITE);
                        g2D.fillRect(currentCase.x1, currentCase.y1, caseSize - 2, caseSize - 2);
                    }
                } else {
                    g2D.setColor(Color.GRAY);
                    g2D.fillRect(currentCase.x1, currentCase.y1, caseSize - 2, caseSize - 2);
                }
            }
        }

        // Dessiner le chemin BFS en bleu, je l'inclue pas dans la boucle car c est trop lourd
        g2D.setColor(Color.BLUE);
        for (Case caseBFS : cheminBFS) {
            if ((caseBFS != caseEntree) && (caseBFS != caseSortie)) {
                g2D.fillRect(caseBFS.x1, caseBFS.y1, caseSize - 2, caseSize - 2);
            }
        }
    }

    public void bfs() {
        cheminBFS.clear();                                              // Toujours reset les variables en POO sinon ca peut causer des degats
        Queue<Case> queue = new LinkedList<>();                         // je crée une liste liée d'objet Case (initialement nulle)
        boolean[][] visited = new boolean[gridSize][gridSize];          // je crée un booleen liée a une Case pour savoir si elle a été visitée (true) ou pas (false)
        Case[][] parent = new Case[gridSize][gridSize];                 // je crée une Case qui sera le parent

        queue.add(caseEntree);                                          // position de départ
        visited[0][0] = true;                                           // j initialise a true car la case de départ est forcement visité

        while (!queue.isEmpty()) {                                      // tant que la queue n est pas vide j'itère
            Case currentCase = queue.poll();                            // la currentCase prend la valeur de la case que je poll de la liste ( equivalent de .pop() en Python)
            if (currentCase.equals(caseSortie)) {
                // caseSortie trouvée, j 'ai trouvé la sortie donc le chemin :
                Case cheminInverse = currentCase;
                while (cheminInverse != null) {                         // tant que j'ai une case cheminInverse ie tant que je suis pas sur la case de l'arrivé
                    cheminBFS.add(cheminInverse);
                    cheminInverse = parent[cheminInverse.x1 / caseSize][cheminInverse.y1 / caseSize];
                }
                // Inverse le chemin pour le dessiner du début à la fin
                Collections.reverse(cheminBFS);
            }

            int[] dx = {1, -1, 0, 0};
            int[] dy = {0, 0, 1, -1};

            for (int i = 0; i < dx.length; i++) {
                int newX = currentCase.x1 / caseSize + dx[i];
                int newY = currentCase.y1 / caseSize + dy[i];

                if (isValid(newX, newY) && !visited[newX][newY] && !caseArray[newX][newY].isObstacle) {
                    queue.add(caseArray[newY][newX]);
                    visited[newX][newY] = true;
                    parent[newX][newY] = currentCase;
                }
            }
        }
    }


    private boolean isValid(int x, int y) {
        return x >= 0 && x < gridSize && y >= 0 && y < gridSize;
    }


}