package org.example;

public class Node {
    public int row;
    public int col;
    public Node parent;
    public Node(int row, int col, Node parent){
        this.col = col;
        this.row = row;
        this.parent = parent;

    }
    @Override
    public String toString(){
        return String.format("( %d : %d )",row,col);
    }

    public boolean isEqual(Node parent){
        return this.row == parent.row && this.col == parent.col;
    }
}
