package com.example.a2048clone;

import java.lang.Math;
import java.util.ArrayList;

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
        return grid[x][y] != null;
    }

    public void move(Tile.directions dir){//sets up arrays and passes them to collide method. Like how we discussed in class
        ArrayList<Tile> line = new ArrayList<Tile>();
        int i,j;
        for(i = 0; i < grid.length; i++){
            for(j = 0; j < grid[i].length; j++) {
                if (dir == Tile.directions.LEFT && grid[i][j] != null)
                    line.add(grid[i][j]);
                else if(dir == Tile.directions.DOWN && grid[(j-3)*-1][i] != null)
                    line.add(grid[(j-3)*-1][i]);
                else if(dir == Tile.directions.RIGHT && grid[i][(j-3)*-1] != null)//adds values from right to left
                    line.add(grid[i][(j-3)*-1]);
                else if(dir == Tile.directions.UP && grid[j][i] != null)//adds values from bottom to top
                    line.add( grid[j][i]);
            }
            collide(line, dir, j);
            line.clear();
        }

        newTile();
    }

    private void collide(ArrayList<Tile> tiles, Tile.directions dir, int pos){ //T1 collides into T2
        for(int i = 0; i < tiles.size()-1; i++) {
            if (tiles.get(i) != null && tiles.get(i).canCombine(tiles.get(i + 1), dir)){
                combine(tiles.get(i), tiles.get(i+1));
                tiles.set(i+1,null);
            }
        }
        int offset = 0;
        for(int i = 0; i < tiles.size(); i++){//Shifts tiles to the direction side of the screen
            if(tiles.get(i) == null)
                offset++;
            else if (dir == Tile.directions.LEFT)
                grid[pos][i-offset] = tiles.get(i);
            else if(dir == Tile.directions.DOWN)
                grid[3-i+offset][pos] = tiles.get(i);
            else if(dir == Tile.directions.RIGHT)//adds values from right to left
                grid[pos][3-i+offset] = tiles.get(i);
            else if(dir == Tile.directions.UP)//adds values from bottom to top
                grid[i+offset][pos] = tiles.get(i);
        }

        for(int i = 0; i < tiles.size()-offset; i++){// fills in rest of grid with null tiles
            if(dir == Tile.directions.LEFT)
                grid[pos][3-i] = null;
            else if(dir == Tile.directions.DOWN)
                grid[i][pos] = null;
            else if(dir == Tile.directions.RIGHT)
                grid[pos][i] = null;
            else if(dir == Tile.directions.UP)
                grid[3-i][pos] = null;
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
        grid[t1.getX()][t1.getY()] = null;

        score += newVal;
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
