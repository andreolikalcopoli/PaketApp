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

    private ImageView imgShow,imgMic, imgSound;
    private boolean isUp=false,sound=false,mic=false;
    private ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_podesavanje);
        getSupportActionBar().hide();

        init();

        napraviListener();

        postaviListener();
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

                startActivity(new Intent(PodesavanjeActivity.this,MainActivity.class));

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
    //</editor-fold>

    //<editor-fold desc="citanje">
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

        boxb.getProgressDrawable().setColorFilter(Color.parseColor("#B20D29"), PorterDuff.Mode.SRC_IN);
        boxb.getThumb().setColorFilter(Color.parseColor("#B20D29"), PorterDuff.Mode.SRC_IN);
        mobb.getProgressDrawable().setColorFilter(Color.parseColor("#B20D29"), PorterDuff.Mode.SRC_IN);
        mobb.getThumb().setColorFilter(Color.parseColor("#B20D29"), PorterDuff.Mode.SRC_IN);
        tvb.getProgressDrawable().setColorFilter(Color.parseColor("#B20D29"), PorterDuff.Mode.SRC_IN);
        tvb.getThumb().setColorFilter(Color.parseColor("#B20D29"), PorterDuff.Mode.SRC_IN);
        interb.getProgressDrawable().setColorFilter(Color.parseColor("#B20D29"), PorterDuff.Mode.SRC_IN);
        interb.getThumb().setColorFilter(Color.parseColor("#B20D29"), PorterDuff.Mode.SRC_IN);
        minb.getProgressDrawable().setColorFilter(Color.parseColor("#B20D29"), PorterDuff.Mode.SRC_IN);
        minb.getThumb().setColorFilter(Color.parseColor("#B20D29"), PorterDuff.Mode.SRC_IN);
        porb.getProgressDrawable().setColorFilter(Color.parseColor("#B20D29"), PorterDuff.Mode.SRC_IN);
        porb.getThumb().setColorFilter(Color.parseColor("#B20D29"), PorterDuff.Mode.SRC_IN);
        netb.getProgressDrawable().setColorFilter(Color.parseColor("#B20D29"), PorterDuff.Mode.SRC_IN);
        netb.getThumb().setColorFilter(Color.parseColor("#B20D29"), PorterDuff.Mode.SRC_IN);
        romb.getProgressDrawable().setColorFilter(Color.parseColor("#B20D29"), PorterDuff.Mode.SRC_IN);
        romb.getThumb().setColorFilter(Color.parseColor("#B20D29"), PorterDuff.Mode.SRC_IN);
        kanb.getProgressDrawable().setColorFilter(Color.parseColor("#B20D29"), PorterDuff.Mode.SRC_IN);
        kanb.getThumb().setColorFilter(Color.parseColor("#B20D29"), PorterDuff.Mode.SRC_IN);
        nazb.getProgressDrawable().setColorFilter(Color.parseColor("#B20D29"), PorterDuff.Mode.SRC_IN);
        nazb.getThumb().setColorFilter(Color.parseColor("#B20D29"), PorterDuff.Mode.SRC_IN);
        snimb.getProgressDrawable().setColorFilter(Color.parseColor("#B20D29"), PorterDuff.Mode.SRC_IN);
        snimb.getThumb().setColorFilter(Color.parseColor("#B20D29"), PorterDuff.Mode.SRC_IN);
        hbob.getProgressDrawable().setColorFilter(Color.parseColor("#B20D29"), PorterDuff.Mode.SRC_IN);
        hbob.getThumb().setColorFilter(Color.parseColor("#B20D29"), PorterDuff.Mode.SRC_IN);


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

        imgShow=(ImageView)findViewById(R.id.iShowHide);

        scrollView=(ScrollView)findViewById(R.id.sv);
        scrollView.setVisibility(View.INVISIBLE);

        imgMic=(ImageView)findViewById(R.id.imgMic);
        imgSound=(ImageView)findViewById(R.id.imgSound);
    }
    //</editor-fold>
}
