package com.example.paketapp.Adapteri;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.paketapp.Paketi.PaketMobilni;
import com.example.paketapp.Paketi.PaketNet;
import com.example.paketapp.Paketi.PaketTV;
import com.example.paketapp.R;

import java.util.ArrayList;

public class NetAdapter extends RecyclerView.Adapter<NetAdapter.NetHolder>{
    private Context context;
    private PaketNet[] paketNet;

    public NetAdapter(Context context,PaketNet[] paketNet) {
        this.context=context;
        this.paketNet = paketNet;
    }

    @NonNull
    @Override
    public NetHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View layout= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.net_item,null);
        NetHolder myHolder=new NetHolder(layout);
        return myHolder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final NetHolder viewHolder, final int i) {
        viewHolder.tvIme.setText("Paket " + paketNet[i].getIme());
        viewHolder.tvCena.setText(String.valueOf(paketNet[i].getCena())+" din.");
        viewHolder.tvDown.setText(String.valueOf(paketNet[i].getDownload())+ " mb/s");
        viewHolder.tvUp.setText(String.valueOf(paketNet[i].getUpload())+ " mb/s");
        viewHolder.tvDin.setText("Da");
        viewHolder.tvWifi.setText("Da");
        viewHolder.tvMail.setText("Da");
        viewHolder.itemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Na klik.

            }
        });
    }
    @Override
    public int getItemCount() {
        return paketNet.length;
    }

    //Kreiranje holdera.
    public static class NetHolder extends RecyclerView.ViewHolder{

        //Atributi koji se sastoje u grid_layout.
        private TextView tvIme,tvCena,tvDown,tvUp,tvWifi,tvDin,tvMail;
        private ConstraintLayout itemLayout;

        public NetHolder(@NonNull View itemView) {
            super(itemView);
            tvIme=(TextView)itemView.findViewById(R.id.tImeNet);
            tvCena=(TextView)itemView.findViewById(R.id.tCenaNet);
            tvDown=(TextView)itemView.findViewById(R.id.tDownload);
            tvUp=(TextView)itemView.findViewById(R.id.tUpload);
            tvWifi=(TextView)itemView.findViewById(R.id.tWifi);
            tvDin=(TextView)itemView.findViewById(R.id.tDinamicki);
            tvMail=(TextView)itemView.findViewById(R.id.tJedanMail);
            itemLayout=(ConstraintLayout)itemView.findViewById(R.id.net_item);
        }
    }
}
