package com.example.a2048clone;

import java.lang.Math;

public class Grid {
    private Tile[][] grid = new Tile[4][4];
    private int score;

    public Grid(){
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[i].length;j++){
                grid[i][j]=null;
            }
        }

        newTile();

        score = 0;
    }

    /*
        Way to process move:
        1) set tile toCollideWith to null
        2) If move is up/down For each column, else For each row
                For tiles a from direction to opposite of direction
                    if toCollideWith is null
                        toCollideWIth is a, a moved to edge of board in direction a
                    else
                        collide(a,toCollideWith) which handles combining of tiles
                        ***Need to handle setting toCollideWith if tiles combine
     */
    private boolean alreadyTile(int x, int y){
        if(grid[x][y] == null)
            return false;
        else
            return true;
    }

    private void collide(Tile t1, Tile t2, Tile.directions d){ //T1 collides into T2
        if(t1.canCombine(t2,d))
            combine(t1,t2);
        else{//move toCollideWIth

        }
    }

    private void combine(Tile t1, Tile t2){//T1 collides into T2
        int newVal = Integer.parseInt(t1.getValue())*2;
        int x = t2.getX();
        int y = t2.getY();

        Tile.conditions condition;
        if(t1.getCondition()==t2.getCondition())
            condition = t1.getCondition();
        else
            condition= Tile.conditions.NONE;
        grid[x][y] = new Tile(Integer.toString(newVal), x, y, condition);

    }

    public void newTile() {
        int randomVal = (int) (Math.random() * 100);
        String value;
        if (randomVal % 5 <= 3)
            value = "2";
        else
            value = "4";
        int x, y;
        do {
            x = (int) (Math.random() * 4);
            y = (int) (Math.random() * 4);//Needs to be a different random value or tile will always spawn at (x,x)
        } while (!alreadyTile(x, y));

        Tile.conditions condition;
        if (randomVal % 10 == 0)
            condition = Tile.conditions.UPDOWN;
        else if (randomVal % 10 == 9)
            condition = Tile.conditions.LEFTRIGHT;
        else
            condition = Tile.conditions.NONE;

        grid[x][y] = new Tile(value, x, y, condition);
    }
}
