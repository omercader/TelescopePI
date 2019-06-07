package com.example.oriol.telescopepi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private boolean connected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button config_bt = (Button)findViewById(R.id.config_button);
        config_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //DO SOMETHING
                startActivity(new Intent(MainActivity.this,ConfigActivity.class));


            }
        });


        Button manual_bt = (Button)findViewById(R.id.manual_button);
        manual_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,ManualActivity.class));
            }
        });

        Button compass_bt = (Button)findViewById(R.id.compass_button);
        compass_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,CompassActivity.class));
            }
        });
    }



}
