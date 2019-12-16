package com.example.paketapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class PrikazTvPaket extends AppCompatActivity {

    TextView imePaketa;
    TextView brojKanala;
    TextView brojHD;
    TextView pauziraj;
    TextView unazad;
    TextView snimaj;
    TextView videoKlub;
    TextView cena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prikaz_tv_paket);

        init();
    }

    private void init()
    {
        cena  = (TextView)findViewById(R.id.cenaTV);
        imePaketa = (TextView) findViewById(R.id.imeTvPaket);
        brojKanala = (TextView) findViewById(R.id.brojKanala);
        brojHD = (TextView) findViewById(R.id.brojHdKanala);
        pauziraj = (TextView)findViewById(R.id.pauziranje);
        unazad = (TextView)findViewById(R.id.gledanjeUnazad);
        snimaj = (TextView)findViewById(R.id.snimanje);
        videoKlub = (TextView)findViewById(R.id.videoKlub);
    }
}
