package com.example.a2048clone;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Grid game;
    private int[][] values;
    private TextView[][] grid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //
        grid = new TextView[4][4];
        grid[0][0] = findViewById(R.id.box_11);
        grid[0][1] = findViewById(R.id.box_12);
        grid[0][2] = findViewById(R.id.box_13);
        grid[0][3] = findViewById(R.id.box_14);

        grid[1][0] = findViewById(R.id.box_21);
        grid[1][1] = findViewById(R.id.box_22);
        grid[1][2] = findViewById(R.id.box_23);
        grid[1][3] = findViewById(R.id.box_24);

        grid[2][0] = findViewById(R.id.box_31);
        grid[2][1] = findViewById(R.id.box_32);
        grid[2][2] = findViewById(R.id.box_33);
        grid[2][3] = findViewById(R.id.box_34);

        grid[3][0] = findViewById(R.id.box_41);
        grid[3][1] = findViewById(R.id.box_42);
        grid[3][2] = findViewById(R.id.box_43);
        grid[3][3] = findViewById(R.id.box_44);

        //game = new Grid(); //Doesn't like this either I guess
        //updateGrid(); //Kills Everything when right here. Don't do it
        grid[1][2].setText("Oi");
        initGame();//breaks it too. Can't do it this way
    }

    public void initGame(){
        game = new Grid();
    }

    public void updateGrid(){
        values = game.getGrid();
        String tileText = "";

        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid.length; j++){
                tileText = "";
                if(values[i][j] != -1)
                    tileText += values[i][j];
//                if(game.grid[i][j].getCondition() == Tile.conditions.UPDOWN)
//                    tileText+="↕";
//                else if(game.grid[i][j].getCondition() == Tile.conditions.LEFTRIGHT)
//                    tileText+="\u2B64";
                grid[i][j].setText(tileText);
            }
        }
    }
}
