package com.example.a2048clone;


public class Tile {

    public enum conditions {NONE, UPDOWN, LEFTRIGHT};
    public enum directions {UP, DOWN, LEFT, RIGHT};

    private conditions condition;
    private String value;

    public Tile(String val, conditions cond){
        value = val;
        condition = conditions.NONE; //CHANGED UNTIL FUTURE UPDATES
    }

    //Needed for combining method to check if condition is kept
    public conditions getCondition(){
        return condition;
    }

    public String getValue(){
        return value;
    }
}
