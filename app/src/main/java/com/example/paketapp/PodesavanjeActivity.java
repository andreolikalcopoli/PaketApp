package com.example.paketapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;

public class PodesavanjeActivity extends AppCompatActivity
{
    SeekBar mobilniSeek,tvSeek,internetSeek;
    SeekBar minutiSeek,porukeSeek,netSeek,romingSeek;
    SeekBar kanaliSeek,nazadSeek,snimanjeSeek,hboSeek;
    Button potvrdi;

    TextView tmobilnibitno,ttvbitno,tnetbitno
            ,tminutibitno,tporukebitno,tinterbitno,tromingbitno,
           tkanalibitno,tnazadbitno,tsnimbitno,thbobitno;

    int boxBitno,mobilniBitno,tvBitno;
    int minutiBitno,porukeBitno,netBitno,romingBitno;
    int kanaliBitno,nazadBitno,snimanjeBitno,hboBitno;
    int internetBitno;

    int gdeNazad;

    private ImageView imgShow,imgMic, imgSound;
    private boolean isUp=false,sound=false,mic=false;
    private ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_podesavanje);
        getSupportActionBar().hide();

        init();

        ucitajPodatke();

        napraviListener();

        postaviListener();
    }

    private void ucitajPodatke()
    {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs",MODE_PRIVATE);

        porukeBitno = sharedPreferences.getInt("PorukeBitnost",0); porukeSeek.setProgress(porukeBitno); tporukebitno.setText("Poruke bitno " + porukeBitno);
        minutiBitno = sharedPreferences.getInt("MinutiBitnost",0); minutiSeek.setProgress(minutiBitno); tminutibitno.setText("Minuti bitno " + minutiBitno);
        internetBitno = sharedPreferences.getInt("InternetBitnost",0);internetSeek.setProgress(internetBitno); tinterbitno.setText("Internet bitno " + internetBitno);
        romingBitno = sharedPreferences.getInt("RomingBitnost",0);romingSeek.setProgress(romingBitno); tromingbitno.setText("Roming bitno " + romingBitno);

        kanaliBitno = sharedPreferences.getInt("KanaliBitnost",0); kanaliSeek.setProgress(kanaliBitno); tkanalibitno.setText("Kanali bitno " + kanaliBitno);
        nazadBitno = sharedPreferences.getInt("NazadBitnost",0); nazadSeek.setProgress(nazadBitno); tnazadbitno.setText("Nazad bitno " + nazadBitno);
        snimanjeBitno = sharedPreferences.getInt("SnimajBitnost",0); snimanjeSeek.setProgress(snimanjeBitno); tsnimbitno.setText("Snimanje bitno " + snimanjeBitno);
        hboBitno = sharedPreferences.getInt("HboBitnost",0); hboSeek.setProgress(hboBitno); thbobitno.setText("Hbo bitno " + hboBitno);

        netBitno = sharedPreferences.getInt("NetBitnost",0); netSeek.setProgress(netBitno); tnetbitno.setText("Net bitno " + netBitno);
        tvBitno = sharedPreferences.getInt("TvBitnost",0); tvSeek.setProgress(tvBitno); ttvbitno.setText("TV bitno " + tvBitno);
        mobilniBitno = sharedPreferences.getInt("MobilniBitnost",0); mobilniSeek.setProgress(mobilniBitno); tmobilnibitno.setText("Mobilni bitno " + mobilniBitno);
    }

    //<editor-fold desc="listeneri">
    private void postaviListener()
    {
        potvrdi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                preuzmiTekst();

                saveAll();

                if(gdeNazad==1)
                {
                    Intent intent;
                    intent = new Intent(PodesavanjeActivity.this,MainActivity.class);
                    startActivity(intent);
                }
                else PodesavanjeActivity.super.onBackPressed();


            }
        });

        imgShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSlideViewButtonClick();
            }
        });

        imgSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!sound) {
                    imgSound.setImageResource(R.drawable.sound);
                    sound=true;
                }
                else{
                    imgSound.setImageResource(R.drawable.soundoff);
                    sound=false;
                }
            }
        });

        imgMic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!mic) {
                    imgMic.setImageResource(R.drawable.mic);
                    mic=true;
                }
                else{
                    imgMic.setImageResource(R.drawable.micoff);
                    mic=false;
                }
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

        mobilniSeek.setOnSeekBarChangeListener(seekBarChangeListener);
        tvSeek.setOnSeekBarChangeListener(seekBarChangeListener);
        netSeek.setOnSeekBarChangeListener(seekBarChangeListener);

        minutiSeek.setOnSeekBarChangeListener(seekBarChangeListener);
        porukeSeek.setOnSeekBarChangeListener(seekBarChangeListener);
        romingSeek.setOnSeekBarChangeListener(seekBarChangeListener);
        internetSeek.setOnSeekBarChangeListener(seekBarChangeListener);

        kanaliSeek.setOnSeekBarChangeListener(seekBarChangeListener);
        snimanjeSeek.setOnSeekBarChangeListener(seekBarChangeListener);
        nazadSeek.setOnSeekBarChangeListener(seekBarChangeListener);
        hboSeek.setOnSeekBarChangeListener(seekBarChangeListener);

    }
    //</editor-fold>

    //<editor-fold desc="citanje">
    private void preuzmiTekst()
    {
        mobilniBitno = mobilniSeek.getProgress();
        tvBitno = tvSeek.getProgress();
        internetBitno = internetSeek.getProgress();

        minutiBitno = minutiSeek.getProgress();
        porukeBitno = porukeSeek.getProgress();
        netBitno = netSeek.getProgress();
        romingBitno = romingSeek.getProgress();

        kanaliBitno = kanaliSeek.getProgress();
        nazadBitno = nazadSeek.getProgress();
        snimanjeBitno = snimanjeSeek.getProgress();
        hboBitno = hboSeek.getProgress();

    }
    //</editor-fold>

    //<editor-fold desc="cuvanje">
    public void saveInt(String s,int zaCuvanje){
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putInt(s,zaCuvanje);

        editor.apply();
    }

    public void saveBool(String s,boolean zaCuvanje){
        SharedPreferences sharedPreferences = getSharedPreferences("SHARED_PREFERENCES",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putBoolean(s,zaCuvanje);

        editor.apply();
    }

    private void saveAll()
    {
        saveInt("MobilniBitnost",mobilniBitno);
        saveInt("TvBitnost",tvBitno);
        saveInt("NetBitnost",internetBitno);
        saveInt("MinutiBitnost",minutiBitno);
        saveInt("PorukeBitnost",porukeBitno);
        saveInt("InternetBitnost",netBitno);
        saveInt("RomingBitnost",romingBitno);
        saveInt("KanaliBitnost",kanaliBitno);
        saveInt("NazadBitnost",nazadBitno);
        saveInt("SnimajBitnost",snimanjeBitno);
        saveInt("HboBitnost",hboBitno);
        saveBool("sound",sound);
        saveBool("mic",mic);
    }
    //</editor-fold>

    //<editor-fold desc="Animations">
    public void slideUp(){
        scrollView.setVisibility(View.VISIBLE);
        TranslateAnimation animate = new TranslateAnimation(
                0,                 // fromXDelta
                0,                 // toXDelta
                scrollView.getHeight(),  // fromYDelta
                0);                // toYDelta
        animate.setDuration(500);
        animate.setFillAfter(true);
        scrollView.startAnimation(animate);
    }

    // slide the view from its current position to below itself
    public void slideDown(){
        TranslateAnimation animate = new TranslateAnimation(
                0,                 // fromXDelta
                0,                 // toXDelta
                0,                 // fromYDelta
                scrollView.getHeight()); // toYDelta
        animate.setDuration(500);
        animate.setFillAfter(true);
        scrollView.startAnimation(animate);
    }

    public void onSlideViewButtonClick() {
        if (isUp) {
            slideDown();
            imgShow.setImageResource(R.drawable.uparrow);
            isUp=false;
        } else {
            slideUp();
            imgShow.setImageResource(R.drawable.downarrow);
            isUp=true;
        }
    }
    //</editor-fold>

    //<editor-fold desc="init">
    private void init()
    {
        //seek bars
        mobilniSeek= (SeekBar)findViewById(R.id.mobilniBitno);
        tvSeek = (SeekBar)findViewById(R.id.tvBitno);
        internetSeek = (SeekBar)findViewById(R.id.netBitno);

        minutiSeek = (SeekBar)findViewById(R.id.minutiBitno);
        porukeSeek = (SeekBar)findViewById(R.id.porukeBitno);
        netSeek = (SeekBar)findViewById(R.id.internetBitno);
        romingSeek = (SeekBar)findViewById(R.id.romingBitno);

        kanaliSeek= (SeekBar)findViewById(R.id.kanaliBitno);
        nazadSeek = (SeekBar)findViewById(R.id.nazadBitno);
        snimanjeSeek = (SeekBar)findViewById(R.id.snimanjeBitno);
        hboSeek = (SeekBar)findViewById(R.id.hboBitno);

        mobilniSeek.getProgressDrawable().setColorFilter(Color.parseColor("#B20D29"), PorterDuff.Mode.SRC_IN);
        mobilniSeek.getThumb().setColorFilter(Color.parseColor("#B20D29"), PorterDuff.Mode.SRC_IN);
        tvSeek.getProgressDrawable().setColorFilter(Color.parseColor("#B20D29"), PorterDuff.Mode.SRC_IN);
        tvSeek.getThumb().setColorFilter(Color.parseColor("#B20D29"), PorterDuff.Mode.SRC_IN);
        internetSeek.getProgressDrawable().setColorFilter(Color.parseColor("#B20D29"), PorterDuff.Mode.SRC_IN);
        internetSeek.getThumb().setColorFilter(Color.parseColor("#B20D29"), PorterDuff.Mode.SRC_IN);
        minutiSeek.getProgressDrawable().setColorFilter(Color.parseColor("#B20D29"), PorterDuff.Mode.SRC_IN);
        minutiSeek.getThumb().setColorFilter(Color.parseColor("#B20D29"), PorterDuff.Mode.SRC_IN);
        porukeSeek.getProgressDrawable().setColorFilter(Color.parseColor("#B20D29"), PorterDuff.Mode.SRC_IN);
        porukeSeek.getThumb().setColorFilter(Color.parseColor("#B20D29"), PorterDuff.Mode.SRC_IN);
        netSeek.getProgressDrawable().setColorFilter(Color.parseColor("#B20D29"), PorterDuff.Mode.SRC_IN);
        netSeek.getThumb().setColorFilter(Color.parseColor("#B20D29"), PorterDuff.Mode.SRC_IN);
        romingSeek.getProgressDrawable().setColorFilter(Color.parseColor("#B20D29"), PorterDuff.Mode.SRC_IN);
        romingSeek.getThumb().setColorFilter(Color.parseColor("#B20D29"), PorterDuff.Mode.SRC_IN);
        kanaliSeek.getProgressDrawable().setColorFilter(Color.parseColor("#B20D29"), PorterDuff.Mode.SRC_IN);
        kanaliSeek.getThumb().setColorFilter(Color.parseColor("#B20D29"), PorterDuff.Mode.SRC_IN);
        nazadSeek.getProgressDrawable().setColorFilter(Color.parseColor("#B20D29"), PorterDuff.Mode.SRC_IN);
        nazadSeek.getThumb().setColorFilter(Color.parseColor("#B20D29"), PorterDuff.Mode.SRC_IN);
        snimanjeSeek.getProgressDrawable().setColorFilter(Color.parseColor("#B20D29"), PorterDuff.Mode.SRC_IN);
        snimanjeSeek.getThumb().setColorFilter(Color.parseColor("#B20D29"), PorterDuff.Mode.SRC_IN);
        hboSeek.getProgressDrawable().setColorFilter(Color.parseColor("#B20D29"), PorterDuff.Mode.SRC_IN);
        hboSeek.getThumb().setColorFilter(Color.parseColor("#B20D29"), PorterDuff.Mode.SRC_IN);


        Intent i = getIntent();
        gdeNazad = i.getIntExtra("GdeNazad",0);

        //text views
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

        imgShow=(ImageView)findViewById(R.id.iShowHide);

        scrollView=(ScrollView)findViewById(R.id.sv);
        scrollView.setVisibility(View.INVISIBLE);

        imgMic=(ImageView)findViewById(R.id.imgMic);
        imgSound=(ImageView)findViewById(R.id.imgSound);
    }
    //</editor-fold>
}
