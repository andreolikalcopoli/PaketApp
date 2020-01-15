package com.example.paketapp.Adapteri;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
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
    private ArrayList<Integer> ocene;

    public TvAdapter(Context context,PaketTV[] paketTV,ArrayList<Integer> ocene) {
        this.context=context;
        this.paketTV = paketTV;
        this.ocene=ocene;
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
        try {
            viewHolder.tvIme.setText(paketTV[i].getIme());
            viewHolder.tvCena.setText(String.valueOf(paketTV[i].getCena())+" din.");
            viewHolder.tvVideoklub.setText(convertApps(paketTV[i].getVideoKlub()));
            viewHolder.tvBrojKanala.setText(String.valueOf(paketTV[i].getBrojKanala()));
            viewHolder.tvHD.setText(String.valueOf(paketTV[i].getBrojHdKanala()));
            viewHolder.tvPauziranje.setText(paketTV[i].getPauziranje());
            viewHolder.tvSnimanje.setText(paketTV[i].getSnimanjeSadrzaja());
            if(ocene.get(i)==0)
            {
                viewHolder.imgOcena.setVisibility(View.INVISIBLE);
                viewHolder.tvOcena.setVisibility(View.INVISIBLE);
            }
            else
                viewHolder.tvOcena.setText(String.valueOf(ocene.get(i)));
            viewHolder.tvUnazad.setText(String.valueOf(paketTV[i].getGledanjaNazad())+"h");
        } catch (Exception e) {
            Log.d("GRESKA",""+i + paketTV[i].getPauziranje());
        }
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
            sb.append(apps.get(i)+", ");

        sb.deleteCharAt(sb.length()-1);
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
        private TextView tvIme,tvCena,tvUnazad,tvSnimanje,tvHD,tvVideoklub,tvBrojKanala,tvPauziranje,tvOcena;
        private ConstraintLayout itemLayout;
        private ImageView imgOcena;

        public TvHolder(@NonNull View itemView) {
            super(itemView);
            tvIme=(TextView)itemView.findViewById(R.id.tImeTv);
            tvCena=(TextView)itemView.findViewById(R.id.tCenaTv);
            tvUnazad=(TextView)itemView.findViewById(R.id.tGledanjeNazad);
            tvSnimanje=(TextView)itemView.findViewById(R.id.tSnimanjeSadrzaja);
            tvHD=(TextView)itemView.findViewById(R.id.tBrojHdKanala);
            tvVideoklub=(TextView)itemView.findViewById(R.id.tVideoKlub);
            tvBrojKanala=(TextView)itemView.findViewById(R.id.tBrKanala);
            tvPauziranje=(TextView)itemView.findViewById(R.id.tPauziranje);
            tvOcena=(TextView)itemView.findViewById(R.id.tvOcena);
            imgOcena=(ImageView)itemView.findViewById(R.id.imgOcena);
            itemLayout=(ConstraintLayout)itemView.findViewById(R.id.tv_item);
        }
    }
}