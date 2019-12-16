package com.example.paketapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prikazi_mobilni_paket);

        init();

    }

    private void init()
    {
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
