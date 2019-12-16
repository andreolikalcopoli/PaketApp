package com.example.paketapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class PrikazNetPaket extends AppCompatActivity {

    TextView ime;
    TextView brzina;
    TextView cena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prikaz_net_paket);

        init();

    }

    private void init()
    {
        ime = (TextView)findViewById(R.id.imeNetPaket);
        brzina = (TextView)findViewById(R.id.brzinaInternet);
        cena = (TextView)findViewById(R.id.cenaNet);
    }
}
