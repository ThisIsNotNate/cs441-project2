package com.example.a2048clone;

import android.widget.TextView;

public class Tile {
    public String val;
    int x,y;
    public enum conditions {NONE, UPDOWN, LEFTRIGHT};

    public Tile(String value, int horiz, int vert){
        val = value;
        x = horiz;

    }

}
