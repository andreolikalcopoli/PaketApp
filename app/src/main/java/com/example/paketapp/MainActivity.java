package com.example.paketapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity {

    private ImageView iTel, iPaket;

    Button dugme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        init();

        postaviListener();

    }

    private void postaviListener()
    {
        iPaket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ConversationActivity.class);
                startActivity(intent);
            }
        });
        iTel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SviPaketiActivity.class);
                startActivity(intent);
            }
        });

        dugme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainActivity.this,PodesavanjeActivity.class));

            }
        });
    }

    private void init()
    {
        iPaket = (ImageView) findViewById(R.id.iPaket);
        iTel = (ImageView) findViewById(R.id.iTelefon);
        dugme = (Button)findViewById(R.id.dugmePodesavanja);
    }
}
