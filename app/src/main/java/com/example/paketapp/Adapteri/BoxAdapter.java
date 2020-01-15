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

import com.example.paketapp.Paketi.BoxPaket;
import com.example.paketapp.Paketi.PaketMobilni;
import com.example.paketapp.Paketi.PaketNet;
import com.example.paketapp.Paketi.PaketTV;
import com.example.paketapp.R;

import java.util.ArrayList;

public class BoxAdapter extends RecyclerView.Adapter<BoxAdapter.BoxHolderr>{
    private Context context;
    private BoxPaket[] paketBox;
    private ArrayList<Integer> ocene;

    public BoxAdapter(Context context,BoxPaket[] paketBox,ArrayList<Integer> ocene) {
        this.context=context;
        this.paketBox = paketBox;
        this.ocene=ocene;
    }

    @NonNull
    @Override
    public BoxHolderr onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View layout= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.box_paket_item,null);
        BoxHolderr myHolder=new BoxHolderr(layout);
        return myHolder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final BoxHolderr viewHolder, final int i) {
        viewHolder.tvIme.setText(paketBox[i].getIme());
        viewHolder.tvCena.setText(String.valueOf(paketBox[i].getCena()));
        viewHolder.tvMob.setText(paketBox[i].getPaketMobilni().getIme());
        viewHolder.tvTv.setText(paketBox[i].getPaketTV().getIme());
        viewHolder.tvNet.setText(paketBox[i].getPaketNet().getIme());
        if(ocene.get(i)==0)
        {
            viewHolder.imgOcena.setVisibility(View.INVISIBLE);
            viewHolder.tvOcena.setVisibility(View.INVISIBLE);
        }
        else
            viewHolder.tvOcena.setText(String.valueOf(ocene.get(i)));
        viewHolder.itemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Na klik.
            }
        });
    }

    @Override
    public int getItemCount() {
        return paketBox.length;
    }

    //Kreiranje holdera.
    public static class BoxHolderr extends RecyclerView.ViewHolder{

        //Atributi koji se sastoje u grid_layout.
        private TextView tvIme,tvCena,tvMob,tvTv,tvNet,tvOcena;
        private ConstraintLayout itemLayout;
        private ImageView imgOcena;

        public BoxHolderr(@NonNull View itemView) {
            super(itemView);
            tvIme=(TextView)itemView.findViewById(R.id.tImeBox);
            tvCena=(TextView)itemView.findViewById(R.id.tCenaBoxx);
            tvMob=(TextView)itemView.findViewById(R.id.tMobBox);
            tvTv=(TextView)itemView.findViewById(R.id.tTVBox);
            tvNet=(TextView)itemView.findViewById(R.id.tNetBox);
            tvOcena=(TextView)itemView.findViewById(R.id.tvOcena4);
            imgOcena=(ImageView)itemView.findViewById(R.id.imgOcena4);
            itemLayout=(ConstraintLayout)itemView.findViewById(R.id.box_item);
        }
    }
}