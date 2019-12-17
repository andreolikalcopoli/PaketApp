package com.example.paketapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.paketapp.Paketi.PaketNet;

public class PrikazNetPaket extends AppCompatActivity {

    TextView ime;
    TextView brzina;
    TextView cena;
    PaketNet paketNet;
    //todo
    //da se skupi iz intenta
    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prikaz_net_paket);

        init();
        ucitaj();
    }

    private void init()
    {
        intent = getIntent();

        ime = (TextView)findViewById(R.id.imeNetPaket);
        brzina = (TextView)findViewById(R.id.brzinaInternet);
        cena = (TextView)findViewById(R.id.cenaNet);
    }

    private void ucitaj()
    {
        ime.setText(paketNet.getIme());
        cena.setText(String.valueOf(paketNet.getCena()));
        String s = String.valueOf(paketNet.getDownload())+"/"+String.valueOf(paketNet.getUpload());
        brzina.setText(s);
    }
}
