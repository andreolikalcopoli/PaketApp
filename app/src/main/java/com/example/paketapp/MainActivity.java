package com.example.paketapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView iTel,iPaket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iPaket=(ImageView)findViewById(R.id.imgPaket);
        iTel=(ImageView)findViewById(R.id.imgTel);

        iPaket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,ConversationActivity.class);
                startActivity(intent);
            }
        });
    }
}
