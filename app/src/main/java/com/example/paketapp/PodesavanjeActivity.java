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

    SharedPreferences sharedPreferences;

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

                sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                bb = Integer.parseInt(boxb.getText().toString()); editor.putInt("InternetBitnost",bb);
                mb = Integer.parseInt(mobb.getText().toString());editor.putInt("MobilniBitnost",mb);
                tb = Integer.parseInt(tvb.getText().toString());editor.putInt("TvBitnost",tb);

                mib = Integer.parseInt(minb.getText().toString());editor.putInt("MinutiBitnost",mib);
                pb = Integer.parseInt(porb.getText().toString());editor.putInt("PorukeBitnost",pb);
                nb = Integer.parseInt(netb.getText().toString());editor.putInt("NetBitnost",nb);
                rb = Integer.parseInt(romb.getText().toString());editor.putInt("RomingBitnost",rb);

                kb = Integer.parseInt(kanb.getText().toString());editor.putInt("KanaliBitnost",kb);
                nab = Integer.parseInt(nazb.getText().toString());editor.putInt("NazadBitnost",nab);
                sb = Integer.parseInt(snimb.getText().toString());editor.putInt("SnimajBitnost",sb);
                hb = Integer.parseInt(hbob.getText().toString());editor.putInt("HboBitnost",hb);

                editor.apply();

                startActivity(new Intent(PodesavanjeActivity.this,MainActivity.class));

            }
        });
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
