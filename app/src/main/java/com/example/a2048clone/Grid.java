package com.example.a2048clone;

import android.util.Log;

import java.lang.Math;
import java.util.ArrayList;

public class Grid {
    public Tile[][] grid = new Tile[4][4];
    private int score;

    public Grid(){

        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[i].length;j++){
                grid[i][j]=null;
            }
        }

        newTile();
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
        return grid[x][y] == null;
    }

    public void move(Tile.directions dir){//sets up arrays and passes them to collide method. Like how we discussed in class
        ArrayList<Tile> line = new ArrayList<>();
        int i,j;
        Tile[][] cpy = grid;

        if(dir == Tile.directions.LEFT||dir == Tile.directions.UP){
            for(i = 0; i < grid.length; i++) {
                for (j = 0; j < grid[i].length; j++) {
                    if (dir == Tile.directions.LEFT && grid[i][j] != null)
                        line.add(grid[i][j]);
                    else if(dir == Tile.directions.UP && grid[j][i] != null)//adds values from bottom to top
                        line.add( grid[j][i]);
                }
                collide(line, dir, i);
                line.clear();
            }
        }
        else if(dir == Tile.directions.RIGHT||dir == Tile.directions.DOWN){
            for(i = grid.length-1; i >= 0; i--){
                for(j = grid[i].length-1; j >=0; j--) {
                    if (dir == Tile.directions.RIGHT && grid[i][j] != null)
                        line.add(grid[i][j]);
                    else if(dir == Tile.directions.DOWN && grid[j][i] != null)//adds values from bottom to top
                        line.add( grid[j][i]);
                }
                collide(line, dir, i);
                line.clear();
            }
        }


        newTile();
    }

    private void collide(ArrayList<Tile> tiles, Tile.directions dir, int pos){ //T1 collides into T2
        ArrayList<Tile> line = combine(tiles, dir);
        Log.i("Collide", "collapsed line size: " + line.size());
        int i;

        if(dir == Tile.directions.LEFT) {
            for (i = 0; i < grid.length; i++) {
                if(i < line.size())
                    grid[pos][i] = line.get(i);
                else
                    grid[pos][i] = null;
            }
        }
        else if(dir == Tile.directions.UP) {
            for (i = 0; i < grid.length; i++) {
                if(i < line.size())
                    grid[i][pos] = line.get(i);
                else
                    grid[i][pos] = null;
            }
        }
        else if(dir == Tile.directions.RIGHT) {
            for (i = 0; i < grid.length; i++) {
                if(i < line.size())
                    grid[pos][3-i] = line.get(i);
                else
                    grid[pos][3-i] = null;
            }
        }
        else if(dir == Tile.directions.DOWN) {
            for (i = 0; i < grid.length; i++) {
                if(i < line.size())
                    grid[3-i][pos] = line.get(i);
                else
                    grid[3-i][pos] = null;
            }
        }
    }

    private ArrayList<Tile> combine(ArrayList<Tile> tiles, Tile.directions dir){//T1 collides into T2
        if(tiles.size()==1) return tiles;

        ArrayList<Tile> newTiles = new ArrayList<>();
        Tile t1, t2=null;
        int newVal;
        Tile.conditions condition;


        for(int i = 0; i < tiles.size();i++){
            t1 = tiles.get(i);
            if(i+1 < tiles.size())
                t2 = tiles.get(i+1);

            if(t2 != null && t1.getValue().equals(t2.getValue())){//if combining
                newVal = Integer.parseInt(t2.getValue())*2;
                Log.i("Tiles Combined", "new tile val: " + newVal);
                if(t1.getCondition()==t2.getCondition())
                    condition = t1.getCondition();
                else
                    condition= Tile.conditions.NONE;

                newTiles.add(new Tile(Integer.toString(newVal), condition));
                i++;
                score+=newVal;
            }
            else {
                Log.i("Tiles Combined", "Tiles not combined, but added");
                newTiles.add(t1);
            }

            t2=null;
        }

        return newTiles;
    }

    public int[][] getGrid(){
        int[][] vals = new int[4][4];
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                if(grid[i][j]==null)
                    vals[i][j]=-1;
                else
                    vals[i][j] = Integer.parseInt(grid[i][j].getValue());
            }
        }
        return vals;
    }

    public int getScore(){
        return score;
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

        grid[x][y] = new Tile(value, condition);
    }

    public void reset(){
        for(int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                grid[i][j]=null;
            }
        }
        score = 0;
        newTile();
        newTile();
    }
}
