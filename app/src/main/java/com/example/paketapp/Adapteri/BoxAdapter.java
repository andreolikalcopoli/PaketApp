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

    public BoxAdapter(Context context,BoxPaket[] paketBox) {
        this.context=context;
        this.paketBox = paketBox;
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
        PaketMobilni[]paketMobilnis={paketBox[i].getPaketMobilni()};
        PaketNet[]paketNets={paketBox[i].getPaketNet()};
        PaketTV[]paketTVS={paketBox[i].getPaketTV()};
        viewHolder.mobilniAdapter=new MobilniAdapter(context,paketMobilnis);
        viewHolder.netAdapter=new NetAdapter(context,paketNets);
        viewHolder.tvAdapter=new TvAdapter(context,paketTVS);
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
        private MobilniAdapter mobilniAdapter;
        private TvAdapter tvAdapter;
        private NetAdapter netAdapter;
        private TextView tvIme,tvCena;
        private ConstraintLayout itemLayout;

        public BoxHolderr(@NonNull View itemView) {
            super(itemView);
            tvIme=(TextView)itemView.findViewById(R.id.tImeMob);
            tvCena=(TextView)itemView.findViewById(R.id.tCenaMob);
            itemLayout=(ConstraintLayout)itemView.findViewById(R.id.box_item);
        }
    }
}