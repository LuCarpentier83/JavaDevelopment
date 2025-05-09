package org.example;

import java.awt.*;

public class Box {
    private final int rows;
    private final int cols;
    private boolean isWall;
    private Color color;
    int pos_x,pos_y;
    public Box(int rows, int cols, boolean isObstacle){
        this.rows = rows;
        this.cols = cols;
        this.isWall = isObstacle;
        this.color = isObstacle ? Color.BLACK : Color.WHITE;
    }

    public int getRows(){ return this.rows;}
    public int getCols(){ return this.cols;}
    public Color getColor(){ return this.color;}
    public boolean isWall(){ return this.isWall;}

    public Box setWall(boolean wall){
        this.isWall = wall;
        this.color = wall ? Color.BLACK : Color.WHITE;
        return this;
    }

    public void setColor(Color color){
        this.color = color;
    }
}
