public class Main {
    public Main() {
    }

    public static void main(String[] args) {
        new Fenetre();
        double compteur = 0;

        for (int i = 0; i < 10; i++) {
            double startTime = System.nanoTime();
            someFunctionToMeasure();                        // on appelle la fonction ici
            double endTime = System.nanoTime();
            double elapsedTime = endTime - startTime;

            double seconds = elapsedTime / 1_000_000;
            compteur = compteur + seconds;
        }

        System.out.println("Temps d'exécution : " + compteur/10 + " ms");

    }

    public static void someFunctionToMeasure() {
        Labyrinthe labyrinth = new Labyrinthe();
        labyrinth.bfs();
        //Astar labyrinth = new Astar();
        //labyrinth.Astar();

    }
}

/*

502 ms pour 10 resolutions en 100x100 avec 0.1 de proba d obstacles   BFS
1400 ms pour 10 resolutions en 100x100 avec 0.1 de proba d obstacles  ASTAR
facteur 3 entre les deux, le BFS gagne haut la main

*/



