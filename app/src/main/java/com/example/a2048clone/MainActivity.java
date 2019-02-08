package com.example.a2048clone;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //
        TextView[][] grid = new TextView[4][4];
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
        grid[3][2] = findViewById(R.id.box_42);
        grid[3][3] = findViewById(R.id.box_42);
    }
}
