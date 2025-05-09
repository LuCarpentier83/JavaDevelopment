package org.example;

public class AStarNode extends Node implements Comparable<AStarNode>{
    int g;
    int h;
    int f;
    public AStarNode parent;
    public AStarNode(int row, int col,AStarNode parent){
        super(row,col,null);
    }

    public void calculateHeuristic(Node end, int costFromStart){
        this.g = costFromStart;
        this.h = Math.abs(row-end.row) + Math.abs(col-end.col);  // manhattan distance here
        this.f = g+h;
    }

    @Override
    public int compareTo(AStarNode other) {
        return Integer.compare(this.f, other.f);
    }
}
