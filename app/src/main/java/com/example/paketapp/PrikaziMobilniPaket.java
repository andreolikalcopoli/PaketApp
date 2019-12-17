package com.example.paketapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.paketapp.Paketi.PaketMobilni;

import org.w3c.dom.Text;

public class PrikaziMobilniPaket extends AppCompatActivity {

    TextView nazivPaketa;
    TextView minutiZemlja;
    TextView minutiMreza;
    TextView porukeZemlja;
    TextView internetZemlja;
    TextView mtsDisk;
    TextView kupovina;
    TextView dodatakMreze;
    TextView romingNet;
    TextView cena;
    Intent intent;
    PaketMobilni paketMobilni;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prikazi_mobilni_paket);

        init();
        ucitaj();
    }

    private void ucitaj()
    {
        cena.setText(String.valueOf(paketMobilni.getCena()));
        nazivPaketa.setText(paketMobilni.getIme());
        minutiMreza.setText(String.valueOf(paketMobilni.getMinutiMreza()));
        minutiZemlja.setText(String.valueOf(paketMobilni.getMinuti()));
        porukeZemlja.setText(String.valueOf(paketMobilni.getSms()));
        internetZemlja.setText(String.valueOf(paketMobilni.getInternet()));
        mtsDisk.setText(String.valueOf(paketMobilni.getGbProstora()));
        kupovina.setText(String.valueOf(paketMobilni.isJosJedanGbZaKupovinu()));
        dodatakMreze.setText(String.valueOf(paketMobilni.getAplikacijeInternet()));
        romingNet.setText(String.valueOf(paketMobilni.isInternetRoming()));
    }

    private void init()
    {
        intent = getIntent();
        //todo
        //skupi iz intenta
        cena = (TextView)findViewById(R.id.cenaMobilni);
        nazivPaketa = (TextView)findViewById(R.id.imeMobPaket);
        minutiZemlja = (TextView)findViewById(R.id.brojMinutaZemlja);
        minutiMreza = (TextView)findViewById(R.id.minutiUMrezi);
        porukeZemlja = (TextView)findViewById(R.id.brojPorukaZemlja);
        internetZemlja = (TextView)findViewById(R.id.internetZemlja);
        mtsDisk = (TextView)findViewById(R.id.mtsDisk);
        kupovina = (TextView)findViewById(R.id.netKupovina);
        dodatakMreze = (TextView)findViewById(R.id.dodatakMreze);
        romingNet = (TextView)findViewById(R.id.romingNet);
    }
}
