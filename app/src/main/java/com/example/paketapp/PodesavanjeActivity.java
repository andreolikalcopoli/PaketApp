package com.example.paketapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;

public class PodesavanjeActivity extends AppCompatActivity {


    SeekBar boxb,mobb,tvb,interb;
    SeekBar minb,porb,netb,romb;
    SeekBar kanb,nazb,snimb,hbob;
    Button potvrdi;

    TextView tboxbitno,tmobilnibitno,ttvbitno,tnetbitno
            ,tminutibitno,tporukebitno,tinterbitno,tromingbitno,
           tkanalibitno,tnazadbitno,tsnimbitno,thbobitno;

    int bb,mb,tb;
    int mib,pb,nb,rb;
    int kb,nab,sb,hb;
    int intb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_podesavanje);

        init();

        napraviListener();

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

    private void napraviListener()
    {
        SeekBar.OnSeekBarChangeListener seekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

               switch (seekBar.getId())
               {
                   case R.id.boxBitno:
                       tboxbitno.setText("Box bitno " + String.valueOf(i));
                       break;
                   case R.id.mobilniBitno:
                       tmobilnibitno.setText("Mobilni bitno " + String.valueOf(i));
                       break;
                   case R.id.netBitno:
                       tnetbitno.setText("Net bitno " + String.valueOf(i));
                       break;
                   case R.id.tvBitno:
                       ttvbitno.setText("TV bitno " + String.valueOf(i));
                       break;
                   case R.id.minutiBitno:
                       tminutibitno.setText("Minuti bitno " + String.valueOf(i));
                       break;
                   case R.id.porukeBitno:
                       tporukebitno.setText("Poruke bitno " + String.valueOf(i));
                       break;
                   case R.id.romingBitno:
                       tromingbitno.setText("Roming bitno " + String.valueOf(i));
                       break;
                   case R.id.internetBitno:
                       tinterbitno.setText("Internet bitno " + String.valueOf(i));
                       break;
                   case R.id.kanaliBitno:
                       tkanalibitno.setText("Kanali bitno " + String.valueOf(i));
                       break;
                   case R.id.snimanjeBitno:
                       tsnimbitno.setText("Snimanje bitno " + String.valueOf(i));
                       break;
                   case R.id.nazadBitno:
                       tnazadbitno.setText("Nazad bitno " + String.valueOf(i));
                       break;
                   case R.id.hboBitno:
                       thbobitno.setText("Hbo bitno " + String.valueOf(i));
                       break;
               }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        };

        boxb.setOnSeekBarChangeListener(seekBarChangeListener);
        mobb.setOnSeekBarChangeListener(seekBarChangeListener);
        tvb.setOnSeekBarChangeListener(seekBarChangeListener);
        netb.setOnSeekBarChangeListener(seekBarChangeListener);

        minb.setOnSeekBarChangeListener(seekBarChangeListener);
        porb.setOnSeekBarChangeListener(seekBarChangeListener);
        romb.setOnSeekBarChangeListener(seekBarChangeListener);
        interb.setOnSeekBarChangeListener(seekBarChangeListener);

        kanb.setOnSeekBarChangeListener(seekBarChangeListener);
        snimb.setOnSeekBarChangeListener(seekBarChangeListener);
        nazb.setOnSeekBarChangeListener(seekBarChangeListener);
        hbob.setOnSeekBarChangeListener(seekBarChangeListener);

    }

    private void preuzmiTekst() {

        bb = boxb.getProgress();
        mb = mobb.getProgress();
        tb = tvb.getProgress();
        intb = interb.getProgress();

        mib = minb.getProgress();
        pb = porb.getProgress();
        nb = netb.getProgress();
        rb = romb.getProgress();

        kb = kanb.getProgress();
        nab = nazb.getProgress();
        sb = snimb.getProgress();
        hb = hbob.getProgress();

    }

    public void saveInt(String s,int zaCuvanje){
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putInt(s,zaCuvanje);

        editor.apply();
    }

    private void saveAll()
    {
        saveInt("BoxBitnost",bb);
        saveInt("MobilniBitnost",mb);
        saveInt("TvBitnost",tb);
        saveInt("NetBitnost",intb);
        saveInt("MinutiBitnost",mib);
        saveInt("PorukeBitnost",pb);
        saveInt("InternetBitnost",nb);
        saveInt("RomingBitnost",rb);
        saveInt("KanaliBitnost",kb);
        saveInt("NazadBitnost",nab);
        saveInt("SnimajBitnost",sb);
        saveInt("HboBitnost",hb);
    }

    private void init()
    {
        //seek bars
        boxb = (SeekBar)findViewById(R.id.boxBitno);
        mobb= (SeekBar)findViewById(R.id.mobilniBitno);
        tvb = (SeekBar)findViewById(R.id.tvBitno);
        interb = (SeekBar)findViewById(R.id.netBitno);

        minb = (SeekBar)findViewById(R.id.minutiBitno);
        porb = (SeekBar)findViewById(R.id.porukeBitno);
        netb = (SeekBar)findViewById(R.id.internetBitno);
        romb = (SeekBar)findViewById(R.id.romingBitno);

        kanb= (SeekBar)findViewById(R.id.kanaliBitno);
        nazb = (SeekBar)findViewById(R.id.nazadBitno);
        snimb = (SeekBar)findViewById(R.id.snimanjeBitno);
        hbob = (SeekBar)findViewById(R.id.hboBitno);

        //text views
        tboxbitno = findViewById(R.id.tboxbitno);
        tmobilnibitno = findViewById(R.id.tmobilnibitno);
        ttvbitno = findViewById(R.id.ttvbitno);
        tnetbitno = findViewById(R.id.tnetbitno );

        tminutibitno = findViewById(R.id.tminutibitno);
        tporukebitno = findViewById(R.id.tporukebitno);
        tinterbitno = findViewById(R.id.tinterbitno);
        tromingbitno = findViewById(R.id.tromingbitno);

        tkanalibitno = findViewById(R.id.tkanalibitno);
        tsnimbitno = findViewById(R.id.tsnimbitno);
        tnazadbitno = findViewById(R.id.tnazadbitno);
        thbobitno = findViewById(R.id.thbobitno);

        potvrdi =(Button)findViewById(R.id.dugmeBitnost);
    }
}
