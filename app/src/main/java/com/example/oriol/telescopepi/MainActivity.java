package com.example.oriol.telescopepi;

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

        Button connect_bt = (Button)findViewById(R.id.connect_bt);
        connect_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //DO SOMETHING

            }
        });

    }



}
