package com.example.a2048clone;

import java.lang.Math;

public class Grid {
    private Tile[][] grid = new Tile[4][4];
    private int score;

    public enum conditions {NONE, UPDOWN, LEFTRIGHT}
    public enum directions {UP, DOWN, LEFT, RIGHT}

    public Grid(){
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[i].length;j++){
                grid[i][j]=null;
            }
        }

        newTile();

        score = 0;
    }

    public void newTile(){
        int  randomVal = (int)(Math.random()*100);
        String value;
        if(randomVal%5 <= 3)
            value = "2";
        else
            value = "4";
        int x,y;
        do {
            x = (int) (Math.random() * 4);
            y = (int) (Math.random() * 4);//Needs to be a different random value or tile will always spawn at (x,x)
        }while(!alreadyTile(x,y));

        Tile.conditions condition;
        if(randomVal%10 == 0)
            condition = Tile.conditions.UPDOWN;
        else if(randomVal%10 == 9)
            condition = Tile.conditions.LEFTRIGHT;
        else
            condition = Tile.conditions.NONE;

        grid[x][y] = new Tile(value, x, y, condition);
    }

    private boolean alreadyTile(int x, int y){
        if(grid[x][y] == null)
            return false;
        else
            return true;
    }
}
