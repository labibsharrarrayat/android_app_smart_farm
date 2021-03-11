package com.example.myapplication;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    private Button press1, press2, press3, press4;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        press1 = (Button)findViewById(R.id.bot1);
        press2 = (Button)findViewById(R.id.bot2);
        press3 = (Button)findViewById(R.id.bot3);
        press4 = (Button)findViewById(R.id.bot4);

        press1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Cucumber_1.class));
            }
        });

        press2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Cucumber_2.class));
            }
        });

        press3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Cucumber_3.class));
            }
        });

        press4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Cucumber_4.class));
            }
        });




    }


}
