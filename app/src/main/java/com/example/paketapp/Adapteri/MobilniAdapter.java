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

import com.example.paketapp.Paketi.PaketMobilni;
import com.example.paketapp.R;

import java.util.ArrayList;

public class MobilniAdapter extends RecyclerView.Adapter<MobilniAdapter.MobilniHolderr>{
    private Context context;
    private PaketMobilni[] paketMobilni;

    public MobilniAdapter(Context context,PaketMobilni[] paketMobilni) {
        this.context=context;
        this.paketMobilni = paketMobilni;
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
        viewHolder.tvCena.setText(String.valueOf(paketMobilni[i].getCena()));
        viewHolder.tvAppNet.setText("Internet aplikacije:"+"\n"+convertApps(paketMobilni[i].getAplikacijeInternet()));
        viewHolder.tvGbProstor.setText("Prostor na disku:"+"\n"+String.valueOf(paketMobilni[i].getGbProstora()));
        viewHolder.tvMin.setText("Minuti:"+"\n"+String.valueOf(paketMobilni[i].getMinuti()));
        viewHolder.tvMinMreza.setText("Minuti u mrezi:"+"\n"+String.valueOf(paketMobilni[i].getMinutiMreza()));
        viewHolder.tvMinRom.setText("Minuti u romingu:"+"\n"+String.valueOf(paketMobilni[i].getMinutiRoming()));
        viewHolder.tvNet.setText("Internet:"+"\n"+String.valueOf(paketMobilni[i].getInternet()));
        viewHolder.tvNetRom.setText("Internet u romingu:"+"\n"+paketMobilni[i].getInternetRoming());
        viewHolder.tvSms.setText("SMS:"+"\n"+String.valueOf(paketMobilni[i].getSms()));
        viewHolder.tvPlusGb.setText("Jos jedan GB za kupovinu:"+"\n"+paketMobilni[i].getJosJedanGbZaKupovinu());
        viewHolder.tvPorukeRom.setText("Poruke u roming:"+"\n"+paketMobilni[i].getPorukeRoming());
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
        private TextView tvIme,tvCena,tvMin,tvMinMreza,tvSms,tvNet,tvGbProstor,tvMinRom,tvAppNet,tvPlusGb,tvPorukeRom,tvNetRom;
        private ImageView imgMob;
        private ConstraintLayout itemLayout;

        public MobilniHolderr(@NonNull View itemView) {
            super(itemView);
            tvIme=(TextView)itemView.findViewById(R.id.tImeMob);
            tvCena=(TextView)itemView.findViewById(R.id.tCenaMob);
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
            imgMob=(ImageView)itemView.findViewById(R.id.iMob);
            itemLayout=(ConstraintLayout)itemView.findViewById(R.id.mob_item);
        }
    }
}