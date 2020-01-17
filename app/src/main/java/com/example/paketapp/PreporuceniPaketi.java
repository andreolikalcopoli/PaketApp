package com.example.paketapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.paketapp.Adapteri.BoxAdapter;
import com.example.paketapp.Adapteri.MobilniAdapter;
import com.example.paketapp.Adapteri.NetAdapter;
import com.example.paketapp.Adapteri.TvAdapter;
import com.example.paketapp.Paketi.BoxPaket;
import com.example.paketapp.Paketi.PaketMobilni;
import com.example.paketapp.Paketi.PaketNet;
import com.example.paketapp.Paketi.PaketTV;

import java.util.ArrayList;

public class PreporuceniPaketi extends AppCompatActivity {

    private RecyclerView recyclerView;

    private PaketTV[] tvs;
    private PaketNet[] nets;
    private PaketMobilni[] paketMobilnis;
    private BoxPaket[] boxs;
    private ArrayList<Integer> ocene=new ArrayList<>();
    private Button btnNoviPaket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preporuceni_paketi);
        getSupportActionBar().hide();

        recyclerView=(RecyclerView)findViewById(R.id.recPreporuka);
        btnNoviPaket=(Button)findViewById(R.id.btnNoviPaket);

        ocene=getIntent().getIntegerArrayListExtra("OcenePrenos");

        btnNoviPaket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(PreporuceniPaketi.this,MainActivity.class);
                startActivity(intent);
            }
        });

        //<editor-fold desc="switch">
        switch (getIntent().getIntExtra("Tip",1)) {
            case 1: {
                RecyclerView.LayoutManager layoutManager = new GridLayoutManager(PreporuceniPaketi.this, 1);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(layoutManager);
                BoxAdapter boxAdapter = new BoxAdapter(PreporuceniPaketi.this, konvertujBox(ConversationActivity.boxeviPreporuci),ocene);
                recyclerView.setAdapter(boxAdapter);
            }
            break;
            case 2: {
                RecyclerView.LayoutManager layoutManager = new GridLayoutManager(PreporuceniPaketi.this, 1);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(layoutManager);
                MobilniAdapter mobAdapter = new MobilniAdapter(PreporuceniPaketi.this, konvertujMob(ConversationActivity.mobilniPreporuci),ocene);
                recyclerView.setAdapter(mobAdapter);
            }
            break;
            case 3: {
                RecyclerView.LayoutManager layoutManager = new GridLayoutManager(PreporuceniPaketi.this, 1);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(layoutManager);
                TvAdapter tvAdapter = new TvAdapter(PreporuceniPaketi.this, konvertujTv(ConversationActivity.tvPreporuci),ocene);
                recyclerView.setAdapter(tvAdapter);
            }
            break;
            case 4: {
                RecyclerView.LayoutManager layoutManager = new GridLayoutManager(PreporuceniPaketi.this, 1);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(layoutManager);
                NetAdapter netAdapter = new NetAdapter(PreporuceniPaketi.this, konvertujNet(ConversationActivity.netPreporuci),ocene);
                recyclerView.setAdapter(netAdapter);
            }
            break;

        }
        //</editor-fold>
    }

    //<editor-fold desc="Konvertovanje">
    private PaketNet[] konvertujNet(ArrayList<PaketNet> netPaketi)
    {
        nets=new PaketNet[netPaketi.size()];
        for(int i=0;i<netPaketi.size();i++)
            nets[i]=netPaketi.get(i);

        return nets;
    }
    private PaketMobilni[] konvertujMob(ArrayList<PaketMobilni> mobilniPaketi)
    {
        paketMobilnis=new PaketMobilni[mobilniPaketi.size()];
        for(int i=0;i<mobilniPaketi.size();i++)
            paketMobilnis[i]=mobilniPaketi.get(i);

        return paketMobilnis;
    }
    private PaketTV[] konvertujTv(ArrayList<PaketTV> tvPaketi)
    {
        tvs=new PaketTV[tvPaketi.size()];
        for(int i=0;i<tvPaketi.size();i++)
            tvs[i]=tvPaketi.get(i);

        return tvs;
    }
    private BoxPaket[] konvertujBox(ArrayList<BoxPaket> boxPaketi)
    {
        boxs=new BoxPaket[boxPaketi.size()];
        for(int i=0;i<boxPaketi.size();i++)
            boxs[i]=boxPaketi.get(i);

        return boxs;
    }
    //</editor-fold>
}
