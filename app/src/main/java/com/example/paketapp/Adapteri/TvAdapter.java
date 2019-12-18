package com.example.paketapp.Adapteri;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.paketapp.Paketi.PaketTV;
import com.example.paketapp.R;

import java.util.ArrayList;

public class TvAdapter extends RecyclerView.Adapter<TvAdapter.TvHolder>{
    private Context context;
    private PaketTV[] paketTV;

    public TvAdapter(Context context,PaketTV[] paketTV) {
        this.context=context;
        this.paketTV = paketTV;
    }

    @NonNull
    @Override
    public TvHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View layout= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.tv_paket_item,null);
        TvHolder myHolder=new TvHolder(layout);
        return myHolder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final TvHolder viewHolder, final int i) {
        viewHolder.tvIme.setText(paketTV[i].getIme());
        viewHolder.tvCena.setText(String.valueOf(paketTV[i].getCena()));
        viewHolder.tvVideoklub.setText("Videoklub:"+"\n"+convertApps(paketTV[i].getVideoKlub()));
        viewHolder.tvBrojKanala.setText("Broj kanala:"+"\n"+String.valueOf(paketTV[i].getBrojKanala()));
        viewHolder.tvHD.setText("Broj HD kanala:"+"\n"+String.valueOf(paketTV[i].getBrojHdKanala()));
        viewHolder.tvPauziranje.setText("Pauziranje:"+"\n"+paketTV[i].getPauziranje());
        viewHolder.tvSnimanje.setText("Snimanje sadrzaja:"+"\n"+paketTV[i].getSnimanjeSadrzaja());
        viewHolder.tvUnazad.setText("Gledanje unazad:"+"\n"+String.valueOf(paketTV[i].getGledanjaNazad()));
        viewHolder.itemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Na klik.
            }
        });
    }

    private String convertApps(ArrayList<String> apps){
        StringBuilder sb=new StringBuilder();

        for(int i=0;i<apps.size();i++)
            sb.append(apps.get(i)+",");

        sb.deleteCharAt(sb.length()-1);

        return sb.toString();
    }

    @Override
    public int getItemCount() {
        return paketTV.length;
    }

    //Kreiranje holdera.
    public static class TvHolder extends RecyclerView.ViewHolder{

        //Atributi koji se sastoje u grid_layout.
        private TextView tvIme,tvCena,tvUnazad,tvSnimanje,tvHD,tvVideoklub,tvBrojKanala,tvPauziranje;
        private ConstraintLayout itemLayout;

        public TvHolder(@NonNull View itemView) {
            super(itemView);
            tvIme=(TextView)itemView.findViewById(R.id.tImeMob);
            tvCena=(TextView)itemView.findViewById(R.id.tCenaMob);
            tvUnazad=(TextView)itemView.findViewById(R.id.tGledanjeNazad);
            tvSnimanje=(TextView)itemView.findViewById(R.id.tSnimanjeSadrzaja);
            tvHD=(TextView)itemView.findViewById(R.id.tBrojHdKanala);
            tvVideoklub=(TextView)itemView.findViewById(R.id.tVideoKlub);
            tvBrojKanala=(TextView)itemView.findViewById(R.id.tBrKanala);
            tvPauziranje=(TextView)itemView.findViewById(R.id.tPauziranje);
            itemLayout=(ConstraintLayout)itemView.findViewById(R.id.tv_item);
        }
    }
}