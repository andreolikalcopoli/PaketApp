package com.example.paketapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity {

    private ImageView iTel, iPaket;

    SharedPreferences prefs = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        init();

        postaviListener();

        prefs = getSharedPreferences("com.example.paketapp",MODE_PRIVATE);

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

    }

    public void saveInt(String s,int zaCuvanje){
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putInt(s,zaCuvanje);

        editor.apply();
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(prefs.getBoolean("firstrun",true))
        {

            saveInt("MobilniBitnost",0);
            saveInt("TvBitnost",0);
            saveInt("NetBitnost",0);
            saveInt("MinutiBitnost",0);
            saveInt("PorukeBitnost",0);
            saveInt("InternetBitnost",0);
            saveInt("RomingBitnost",0);
            saveInt("KanaliBitnost",0);
            saveInt("NazadBitnost",0);
            saveInt("SnimajBitnost",0);
            saveInt("HboBitnost",0);

            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            ViewGroup viewGroup = findViewById(android.R.id.content);
            View dialogView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.pocetnidialog,viewGroup,false);
            builder.setView(dialogView);
            AlertDialog alertDialog = builder.create();
            alertDialog.setCancelable(false);
            alertDialog.show();

            Button  ppok = alertDialog.findViewById(R.id.prviPutOk);

            ppok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent  =new Intent(MainActivity.this,PodesavanjeActivity.class);
                    intent.putExtra("GdeNazad",1);
                    startActivity(intent);

                }
            });

            prefs.edit().putBoolean("firstrun",false).commit();
        }

    }

    private void init()
    {
        iPaket = (ImageView) findViewById(R.id.iPaket);
        iTel = (ImageView) findViewById(R.id.iTelefon);
    }
}
