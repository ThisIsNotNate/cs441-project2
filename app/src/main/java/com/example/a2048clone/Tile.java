package com.example.a2048clone;

import java.lang.Math;

public class Tile {

    public enum conditions {NONE, UPDOWN, LEFTRIGHT};
    public enum directions {UP, DOWN, LEFT, RIGHT};

    private int x,y;
    private conditions condition;
    private String value;

    public Tile(String val, int horiz, int vert, conditions cond){
        String value = val;
        int x = horiz;
        int y = vert;
        conditions condition = cond;
    }

    //2 tiles can't combine if: 1) they have different values, 2)The tile is moved against its combining condition
    public boolean canCombine(Tile other, directions dir){
        if(other == null || other.getValue().compareTo(value)!=0)
            return false;
        else if(dir == directions.DOWN || dir == directions.UP && condition == conditions.LEFTRIGHT)
            return false;
        else if(dir == directions.LEFT || dir == directions.RIGHT && condition == conditions.UPDOWN)
            return false;
        else
            return true;
    }

    //Needed for combining method to check if condition is kept
    public conditions getCondition(){
        return condition;
    }

    public String getValue(){
        return value;
    }

    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
}
