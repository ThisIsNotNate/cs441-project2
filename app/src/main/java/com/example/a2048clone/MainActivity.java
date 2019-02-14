package com.example.a2048clone;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
   //private final ThreadLocal<Grid> game = new ThreadLocal<Grid>();
    private Grid game;
    private int[][] values;
    private TextView[][] grid;
    private TextView gameScore;

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

        gameScore = findViewById(R.id.score);

        game = new Grid(); //Doesn't like this either I guess
        updateGrid();

        Button buttonUp = findViewById(R.id.buttonUP);
        buttonUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                game.move(Tile.directions.UP);
                updateGrid();
            }
        });

        Button buttonRight = findViewById(R.id.buttonRIGHT);
        buttonRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                game.move(Tile.directions.RIGHT);
                updateGrid();
            }
        });

        Button buttonLeft = findViewById(R.id.buttonLEFT);
        buttonLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                game.move(Tile.directions.LEFT);
                updateGrid();
            }
        });

        Button buttonDown = findViewById(R.id.buttonDOWN);
        buttonDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                game.move(Tile.directions.DOWN);
                updateGrid();
            }
        });

        Button buttonReset = findViewById(R.id.buttonRESET);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                game.reset();

                updateGrid();
            }
        });
    }



    public void updateGrid(){
        runOnUiThread(new Thread(new Runnable() {
            @Override
            public void run() {
                values = game.getGrid();
                String tileText;

                for (int i = 0; i < grid.length; i++)
                    for (int j = 0; j < grid.length; j++) {
                        tileText = "";
                        if (values[i][j] != -1)
                            tileText += values[i][j];
                        if(game.grid[i][j] != null && game.grid[i][j].getCondition() == Tile.conditions.UPDOWN)
                            tileText+=" UD";
                        else if(game.grid[i][j] != null && game.grid[i][j].getCondition() == Tile.conditions.LEFTRIGHT)
                            tileText+=" LR";
                        grid[i][j].setText(tileText);
                    }
                String score = "Score: " + Integer.toString(game.getScore());
                gameScore.setText(score);
            }
        }));
    }
}
