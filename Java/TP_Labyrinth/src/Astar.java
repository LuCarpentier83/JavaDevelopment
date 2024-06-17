import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class Astar extends JPanel {
    final int windowSize = 300;
    final int gridSize = 10;
    int caseSize;
    Case[][] caseArray;
    Case caseEntree;
    Case caseSortie;
    List<Case> cheminAstar;
    List<Case> openSet = new LinkedList<>();
    List<Case> closedSet = new LinkedList<>();

    public Astar() {
        // LABYRINTHE
        this.setPreferredSize(new Dimension(windowSize, windowSize));
        this.caseSize = windowSize / gridSize;
        this.caseArray = new Case[gridSize][gridSize];
        this.cheminAstar = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                double rand = Math.random();
                boolean isObstacle = rand <= 0.1;
                if ((i != 0 || j != 0) && (i != gridSize - 1 || j != gridSize - 1)) {
                    this.caseArray[i][j] = new Case(j * caseSize, i * caseSize, (j + 1) * caseSize, (i + 1) * caseSize, isObstacle);
                } else {
                    this.caseArray[i][j] = new Case(j * caseSize, i * caseSize, (j + 1) * caseSize, (i + 1) * caseSize, false);
                }
            }
        }

        this.caseEntree = this.caseArray[0][0];
        this.caseSortie = this.caseArray[gridSize - 1][gridSize - 1];
        this.caseEntree.g = 0;
        this.caseEntree.h = heuristic(this.caseEntree, this.caseSortie);
        this.caseEntree.f = this.caseEntree.h;
        Astar();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;

        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                Case currentCase = this.caseArray[i][j];
                if ((currentCase != caseEntree) && (currentCase != caseSortie)) {
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
                g2D.setColor(Color.BLUE);
                for (Case caseAstar : cheminAstar) {
                    if ((caseAstar != caseEntree) && (caseAstar != caseSortie)) {
                        g2D.fillRect(caseAstar.x1, caseAstar.y1, caseSize - 2, caseSize - 2);
                    }
                }
            }
        }
    }

    public List<Case> Astar() {
        // Initialisation
        caseEntree.g = 0;
        caseEntree.h = heuristic(caseEntree, caseSortie);
        caseEntree.f = caseEntree.g + caseEntree.h;

        openSet.add(caseEntree);

        while (!openSet.isEmpty()) {
            // Trouver le nœud avec le score F le plus bas dans openSet
            Case current = getNodeWithLowestFScore(openSet);

            if (current.equals(caseSortie)) {
                // Si nous avons atteint le nœud d'arrivée, reconstruire et retourner le chemin
                return reconstructPath(caseSortie);
            }

            openSet.remove(current);
            closedSet.add(current);

            // Obtenir les voisins du nœud actuel
            List<Case> neighbors = getNeighbors(current);

            for (Case neighbor : neighbors) {
                // Vérifier si le voisin est déjà dans closedSet ou est un obstacle
                if (closedSet.contains(neighbor) || neighbor.isObstacle) {
                    continue; // Ignorer ce voisin
                }

                // Calculer le coût G du voisin
                double tentativeGScore = current.g + 1;
                System.out.println(neighbor);

                // Si le voisin n'est pas dans openSet ou a un coût G inférieur
                if (!openSet.contains(neighbor) || tentativeGScore < neighbor.g) {
                    // Mettre à jour les informations du voisin
                    neighbor.parent = current;
                    neighbor.g = tentativeGScore;
                    neighbor.h = heuristic(neighbor, caseSortie);
                    neighbor.f = neighbor.g + neighbor.h;

                    // Si le voisin n'est pas déjà dans openSet, l'ajouter
                    if (!openSet.contains(neighbor)) {
                        openSet.add(neighbor);
                    }
                }
            }
        }

        // Si openSet est vide et que le nœud d'arrivée n'a pas été trouvé, retourner null
        return null;
    }


    public List<Case> reconstructPath(Case goal) {
        List<Case> path = new ArrayList<>();
        Case current = goal;
        while (current != null) {
            path.add(current);
            current = current.parent;
        }
        Collections.reverse(path);
        cheminAstar = path;
        return cheminAstar;
    }

    private double heuristic(Case a, Case b) {
        return Math.sqrt(Math.pow(a.x1 - b.x1, 2) + Math.pow(a.y1 - b.y1, 2));
    }

    private double distance(Case a, Case b) {
        return 1;
    }

    private Case getNodeWithLowestFScore(List<Case> cases) {
        if (cases.isEmpty()) {
            return null;
        } else {
            Case lowestValue = cases.get(0);
            for (Case node : cases) {
                if (node.getFscore() < lowestValue.getFscore()) {
                    lowestValue = node;
                }
            }
            return lowestValue;
        }
    }

    public List<Case> getNeighbors(Case current) {
        int[] shiftH = {1, 0, 0, 0};
        int[] shiftV = {0, 0, 1, 0};

        List<Case> neighbors = new LinkedList<>();

        for (int i = 0; i < shiftH.length; i++) {
            int newX = current.x1 / caseSize + shiftH[i];
            int newY = current.y1 / caseSize + shiftV[i];

            if (isValid(newX, newY) && !caseArray[newX][newY].isObstacle) {
                Case neighbor = this.caseArray[newX][newY];
                if (!this.closedSet.contains(neighbor)) {
                    neighbors.add(neighbor);
                }
            }
        }
        return neighbors;
    }

    private boolean isObstacle(int x, int y) {
        return caseArray[x][y].isObstacle;
    }

    public boolean isValid(int x, int y) {
        return x >= 0 && x < gridSize && y >= 0 && y < gridSize;
    }
}
