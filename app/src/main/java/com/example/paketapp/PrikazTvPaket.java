package com.example.paketapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.paketapp.Paketi.PaketTV;

public class PrikazTvPaket extends AppCompatActivity {

    TextView imePaketa;
    TextView brojKanala;
    TextView brojHD;
    TextView pauziraj;
    TextView unazad;
    TextView snimaj;
    TextView videoKlub;
    TextView cena;
    PaketTV paketTV;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prikaz_tv_paket);

        init();
        ucitaj();
    }

    private void init()
    {
        intent = getIntent();
        //todo
        // da se skupi
        cena  = (TextView)findViewById(R.id.cenaTV);
        imePaketa = (TextView) findViewById(R.id.imeTvPaket);
        brojKanala = (TextView) findViewById(R.id.brojKanala);
        brojHD = (TextView) findViewById(R.id.brojHdKanala);
        pauziraj = (TextView)findViewById(R.id.pauziranje);
        unazad = (TextView)findViewById(R.id.gledanjeUnazad);
        snimaj = (TextView)findViewById(R.id.snimanje);
        videoKlub = (TextView)findViewById(R.id.videoKlub);
    }

    private void ucitaj()
    {
        cena.setText(String.valueOf(paketTV.getCena()));
        imePaketa.setText(paketTV.getIme());
        brojKanala.setText(String.valueOf(paketTV.getBrojKanala()));
        brojHD.setText(String.valueOf(paketTV.getBrojHdKanala()));
        pauziraj.setText(String.valueOf(paketTV.isPauziranje()));
        unazad.setText(String.valueOf(paketTV.getGledanjaNazad()));
        snimaj.setText(String.valueOf(paketTV.isSnimanjeSadrzaja()));
        videoKlub.setText(String.valueOf(paketTV.getVideoKlub()));
    }
}
