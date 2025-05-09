package org.example;

import java.awt.*;
import java.util.*;
import java.util.List;
import javax.swing.*;


public class Maze extends JPanel{
    public static int ROW_NUMBERS = 50;
    public static int COL_NUMBERS = 65;
    static int CELL_WIDTH = 10;
    static int CELL_HEIGHT = 10;
    public double bfsTime;
    public double astarTime;
    Dimension dim = new Dimension (650, 500);
    static int startRow = 0;
    static int startCol = 0;
    static int endRow = ROW_NUMBERS-1;
    static int endCol = COL_NUMBERS-1;
    Box[][] grid = new Box[ROW_NUMBERS][COL_NUMBERS];

    public Maze(double wallProbability){
        setPreferredSize(dim);
        setBackground(Color.lightGray);
        this.generateMaze(wallProbability);
    }

    private void generateMaze(double wallProbability){
        for (int i=0;i<ROW_NUMBERS; i++){
            for(int j=0; j<COL_NUMBERS; j++){
                boolean isWall = Math.random() < wallProbability;
                grid[i][j] = new Box(i*CELL_WIDTH, j*CELL_HEIGHT, isWall);
            }
        }
        Box start = grid[startRow][startCol];
        Box end = grid[endRow][endCol];

        start.setWall(false).setColor(Color.GREEN);
        end.setWall(false).setColor(Color.BLUE);

    }
    public void displayMaze() {
        for (int i = 0; i < ROW_NUMBERS; i++) {
            for (int j = 0; j < COL_NUMBERS; j++) {
                if (grid[i][j].isWall()){
                    System.out.print('#');
                } else if(grid[i][j].getColor().equals(Color.GREEN)){
                    System.out.print('@');
                } else if(grid[i][j].getColor().equals(Color.BLUE)){
                    System.out.print('@');
                }else{
                    System.out.print('.');
                }

            }System.out.println();
        }
    }

    public void AStar() throws Exception{
        double startTime = System.nanoTime();

        PriorityQueue<AStarNode> openSet = new PriorityQueue<>();

        AStarNode startNode = new AStarNode(startRow,startCol,null);
        AStarNode endNode = new AStarNode(endRow,endCol,null);

        openSet.add(startNode);
        startNode.calculateHeuristic(endNode,0);

        boolean[][] visited = new boolean[ROW_NUMBERS][COL_NUMBERS];
        visited[startNode.row][startNode.col] = true;

        while(!openSet.isEmpty()){
            AStarNode current = openSet.poll();

            if (current.row == endNode.row && current.col == endNode.col){
                while(current.parent != null){
                    if(!(current.row == startRow && current.col == startCol) && !(endCol == current.col && endRow == current.row)){
                        grid[current.row][current.col].setColor(Color.PINK);
                    }
                    current = current.parent;
                }
                double endTime = System.nanoTime();
                double elapsedTime = endTime - startTime/1_000_000;
                this.astarTime = elapsedTime;
                return;
            }

            List<AStarNode> neighbors = getNeighbors(current, visited);
            for (AStarNode neighbor : neighbors){
                if(isValidCase(neighbor.row, neighbor.col) && !visited[neighbor.row][neighbor.col]){
                    visited[neighbor.row][neighbor.col] = true;
                    neighbor.calculateHeuristic(endNode, neighbor.g+1);
                    neighbor.parent = current;
                    openSet.add(neighbor);

                }
            }
        }
        repaint();
        throw new Exception();

    }

    public void clearMaze() {
        for(int i=0; i<ROW_NUMBERS; i++){
            for(int j=0; j<COL_NUMBERS; j++){
                if (grid[i][j].getColor() == Color.YELLOW || grid[i][j].getColor()==Color.PINK){
                    grid[i][j].setColor(Color.WHITE);
                } continue;
            }
        }
    }

    public void breadthFirstSearch() throws Exception {
        double startTime = System.nanoTime();

        boolean pathFound = false;
        Node startNode = new Node(startRow, startCol, null);
        Node endNode = new Node(endRow, endCol, null);

        Queue<Node> queue = new LinkedList<>();
        boolean[][] visited = new boolean[ROW_NUMBERS][COL_NUMBERS];


        queue.add(startNode);
        visited[startNode.row][startNode.col] = true;

        while(!queue.isEmpty()){

            Node current = queue.poll();

            if (current.isEqual(endNode)){
                while(current.parent != null){
                    if(!(current.row == startRow && current.col == startCol) && !(endCol == current.col && endRow == current.row)){
                        grid[current.row][current.col].setColor(Color.YELLOW);
                    }
                    current = current.parent;
                }
                double endTime = System.nanoTime();
                double elapsedTime = endTime - startTime/1_000_000;
                this.bfsTime = elapsedTime;
                return;


            }

            List<Node> neighbors = getNeighbors(current, visited);
            System.out.println(neighbors.toString());
            for (Node neighbor : neighbors){
                if (isValidCase(neighbor.row,neighbor.col) && !visited[neighbor.row][neighbor.col]){
                    visited[neighbor.row][neighbor.col] = true;
                    Node node = new Node(neighbor.row, neighbor.col, current);
                    queue.add(node);
                }
            }

        }
        repaint();
        throw new Exception();
    }

    public java.util.List<Node> getNeighbors(Node current, boolean[][] visited){
        java.util.List<Node> neighbors = new ArrayList<>();

        int[][] directions = {
                {-1,0},
                {1,0},
                {0,-1},
                {0,1}
        };

        for(int[] dir: directions){
            int col = current.col + dir[1];
            int row = current.row + dir[0];

            if (isValidCase(row, col)){
                Node neighbor = new Node(row, col, current);
                neighbors.add(neighbor);
            }
        }
        return neighbors;
    }

    public java.util.List<AStarNode> getNeighbors(AStarNode current, boolean[][] visited){
        java.util.List<AStarNode> neighbors = new ArrayList<>();

        int[][] directions = {
                {-1,0},
                {1,0},
                {0,-1},
                {0,1}
        };

        for(int[] dir: directions){
            int col = current.col + dir[1];
            int row = current.row + dir[0];

            if (isValidCase(row, col)){
                AStarNode neighbor = new AStarNode(row, col, current);
                neighbors.add(neighbor);
            }
        }
        return neighbors;
    }

    protected boolean isValidCase(int row, int col){
        if( (0 <= row && row < ROW_NUMBERS) && ( 0<=col && col<COL_NUMBERS)){
            if(!grid[row][col].isWall()){
                return true;
            }
        }
        return false;
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        for (int i=0;i<ROW_NUMBERS; i++){
            for(int j=0; j<COL_NUMBERS; j++){
                Box box = grid[i][j];

                // color of isObstacle
                g.setColor(box.getColor());
                g.fillRect(j*CELL_WIDTH,i*CELL_HEIGHT,CELL_WIDTH, CELL_HEIGHT);
            }
        }
    }

}
