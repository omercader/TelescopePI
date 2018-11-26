package com.example.oriol.telescopepi;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private boolean connected = false;
    private Handler h = null;

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


        //Creating the handler in order to communicate with bluetooth thread
        h = new Handler(){
            @Override
            public void handleMessage(Message msg){
                if(msg.what == 0){
                    updateUI(msg);
                }else{
                    //showErrorDialog();
                }
            }
        };

    }

    private void updateUI(Message msg){



    }



}
