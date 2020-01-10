package com.example.paketapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PodesavanjeActivity extends AppCompatActivity {


    EditText boxb,mobb,tvb;
    EditText minb,porb,netb,romb;
    EditText kanb,nazb,snimb,hbob;
    Button potvrdi;

    int bb,mb,tb;
    int mib,pb,nb,rb;
    int kb,nab,sb,hb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_podesavanje);

        init();

        postaviListener();
    }

    private void postaviListener()
    {
        potvrdi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                preuzmiTekst();

                saveAll();

                startActivity(new Intent(PodesavanjeActivity.this,MainActivity.class));

            }
        });
    }

    private void preuzmiTekst() {

        bb = Integer.parseInt(boxb.getText().toString());
        mb = Integer.parseInt(mobb.getText().toString());
        tb = Integer.parseInt(tvb.getText().toString());

        mib = Integer.parseInt(minb.getText().toString());
        pb = Integer.parseInt(porb.getText().toString());
        nb = Integer.parseInt(netb.getText().toString());
        rb = Integer.parseInt(romb.getText().toString());

        kb = Integer.parseInt(kanb.getText().toString());
        nab = Integer.parseInt(nazb.getText().toString());
        sb = Integer.parseInt(snimb.getText().toString());
        hb = Integer.parseInt(hbob.getText().toString());

    }

    public void saveInt(String s,int zaCuvanje){
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putInt(s,zaCuvanje);

        editor.apply();
    }

    private void saveAll()
    {
        saveInt("InternetBitnost",bb);
        saveInt("MobilniBitnost",mb);
        saveInt("TvBitnost",tb);
        saveInt("MinutiBitnost",mib);
        saveInt("PorukeBitnost",pb);
        saveInt("NetBitnost",nb);
        saveInt("RomingBitnost",rb);
        saveInt("KanaliBitnost",kb);
        saveInt("NazadBitnost",nab);
        saveInt("SnimajBitnost",sb);
        saveInt("HboBitnost",hb);
    }

    private void init()
    {
        boxb = (EditText)findViewById(R.id.boxBitno);
        mobb= (EditText)findViewById(R.id.mobilniBitno);
        tvb = (EditText)findViewById(R.id.tvBitno);

        minb = (EditText)findViewById(R.id.minutiBitno);
        porb = (EditText)findViewById(R.id.porukeBitno);
        netb = (EditText)findViewById(R.id.netBitno);
        romb = (EditText)findViewById(R.id.romingBitno);

        kanb= (EditText)findViewById(R.id.kanaliBitno);
        nazb = (EditText)findViewById(R.id.nazadBitno);
        snimb = (EditText)findViewById(R.id.snimanjeBitno);
        hbob = (EditText)findViewById(R.id.hboBitno);

        potvrdi =(Button)findViewById(R.id.dugmeBitnost);
    }
}
