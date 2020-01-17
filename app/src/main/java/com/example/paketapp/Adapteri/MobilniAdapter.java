package com.example.paketapp.Adapteri;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.paketapp.Paketi.PaketMobilni;
import com.example.paketapp.R;

import java.util.ArrayList;

public class MobilniAdapter extends RecyclerView.Adapter<MobilniAdapter.MobilniHolderr>{
    private Context context;
    private PaketMobilni[] paketMobilni;
    private ArrayList<Integer> ocene;

    public MobilniAdapter(Context context,PaketMobilni[] paketMobilni,ArrayList<Integer> ocene) {
        this.context=context;
        this.paketMobilni = paketMobilni;
        this.ocene=ocene;
    }

    @NonNull
    @Override
    public MobilniHolderr onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View layout= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.mobilni_paket_item,null);
        MobilniHolderr myHolder=new MobilniHolderr(layout);
        return myHolder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final MobilniHolderr viewHolder, final int i) {
        viewHolder.tvIme.setText(paketMobilni[i].getIme());
        viewHolder.tvCena.setText(String.valueOf(paketMobilni[i].getCena()) + " din.");
        viewHolder.tvAppNet.setText(convertApps(paketMobilni[i].getAplikacijeInternet()));
        viewHolder.tvGbProstor.setText(String.valueOf(paketMobilni[i].getGbProstora())+" gb");
        if (paketMobilni[i].getMinuti() > 1000)
            viewHolder.tvMin.setText("Neograniceno");
        else
            viewHolder.tvMin.setText(String.valueOf(paketMobilni[i].getMinuti()));
        viewHolder.tvMinMreza.setText(String.valueOf("Neograniceno"));
        if (paketMobilni[i].getMinutiRoming() > 1000)
            viewHolder.tvMinRom.setText("Neograniceno");
        else
            viewHolder.tvMinRom.setText(String.valueOf(paketMobilni[i].getMinutiRoming()));
        viewHolder.tvNet.setText(String.valueOf(paketMobilni[i].getInternet())+" gb");
        viewHolder.tvNetRom.setText(paketMobilni[i].getInternetRoming());
        if (paketMobilni[i].getSms() > 1000)
            viewHolder.tvSms.setText("Neograniceno");
        else
            viewHolder.tvSms.setText(String.valueOf(paketMobilni[i].getSms()));
        viewHolder.tvPlusGb.setText(paketMobilni[i].getJosJedanGbZaKupovinu());
        viewHolder.tvPorukeRom.setText(paketMobilni[i].getPorukeRoming());
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
    private String convertApps(ArrayList<String> apps){
        StringBuilder sb=new StringBuilder();

        for(int i=0;i<apps.size();i++)
            sb.append(apps.get(i)+",");

        sb.deleteCharAt(sb.length()-1);

        return sb.toString();

    }

    @Override
    public int getItemCount() {
        return paketMobilni.length;
    }

    //Kreiranje holdera.
    public static class MobilniHolderr extends RecyclerView.ViewHolder{

        //Atributi koji se sastoje u grid_layout.
        private TextView tvIme,tvCena,tvMin,tvMinMreza,tvSms,tvNet,tvGbProstor,tvMinRom,tvAppNet,tvPlusGb,tvPorukeRom,tvNetRom,tvOcena;
        private ConstraintLayout itemLayout,constraintLayout;
        private ImageView imgOcena;

        public MobilniHolderr(@NonNull View itemView) {
            super(itemView);
            tvIme=(TextView)itemView.findViewById(R.id.tImeNet);
            tvCena=(TextView)itemView.findViewById(R.id.tCenaNet);
            tvMin=(TextView)itemView.findViewById(R.id.tMinMob);
            tvMinMreza=(TextView)itemView.findViewById(R.id.tMinMrezaMob);
            tvSms=(TextView)itemView.findViewById(R.id.tSmsMob);
            tvNet=(TextView)itemView.findViewById(R.id.tNetMob);
            tvGbProstor=(TextView)itemView.findViewById(R.id.tProstorMob);
            tvMinRom=(TextView)itemView.findViewById(R.id.tMinRoming);
            tvAppNet=(TextView)itemView.findViewById(R.id.tAppNet);
            tvPlusGb=(TextView)itemView.findViewById(R.id.tPlusGb);
            tvPorukeRom=(TextView)itemView.findViewById(R.id.tPorRom);
            tvNetRom=(TextView)itemView.findViewById(R.id.tNetRom);
            imgOcena=(ImageView)itemView.findViewById(R.id.imgOcena3);
            tvOcena=(TextView)itemView.findViewById(R.id.tvOcena3);
            itemLayout=(ConstraintLayout)itemView.findViewById(R.id.mob_item);
            constraintLayout=(ConstraintLayout)itemView.findViewById(R.id.rectbackg);
        }
    }
}