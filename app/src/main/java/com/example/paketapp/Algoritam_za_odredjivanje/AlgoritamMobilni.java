package com.example.paketapp.Algoritam_za_odredjivanje;

import com.example.paketapp.Paketi.PaketMobilni;

import java.util.ArrayList;
import java.util.Arrays;

public class AlgoritamMobilni
{
    ArrayList<PaketMobilni> paketi;
    int score[];
    boolean kriterijumi[][];
    int minuti;
    int poruke;
    int net;
    boolean roming;
    int bitnop;
    int bitnom;
    int bitnon;
    int bitnor;

    public AlgoritamMobilni(ArrayList<PaketMobilni> pak,int minuti,int poruke,int net,boolean roming,
           int bitnop,int bitnom,int bitnon,int bitnor)
    {
        this.bitnop = bitnop;
        this.bitnon = bitnon;
        this.bitnom = bitnom;
        this.bitnor = bitnor;
        this.paketi = pak;
        this.minuti = minuti;
        this.poruke = poruke;
        this.net = net;
        this.roming = roming;
        kriterijumi = new boolean[pak.size()][10];
        score = new int[pak.size()];
        Arrays.fill(score, 0);
    }

    public int [] runAlgo()
    {
        for(int i=0;i<paketi.size();i++)
        {
            for(int j=0;j<10;j++) kriterijumi[i][j] = false;
        }

        for(int i=0;i< paketi.size();i++)
        {
            PaketMobilni p = paketi.get(i);
            if(p.getSms()>=poruke) kriterijumi[i][0] = true;
            if(p.getMinuti()>=minuti) kriterijumi[i][1] = true;
            if(p.getInternet()>=net) kriterijumi[i][2] = true;
            kriterijumi[i][3] = ((p.getMinutiRoming() > 0) == roming);
            //todo
            //zavrsi
            //ukljuci i cenu u algoritam


            int sc = 0;
            sc+=(kriterijumi[i][0]) ? (bitnop) : (-bitnop);
            sc+=(kriterijumi[i][1]) ? (bitnom) : (-bitnom);
            sc+=(kriterijumi[i][2]) ? (bitnon) : (-bitnon);
            sc+=(kriterijumi[i][3]) ? (bitnor) : (-bitnor);

            score[i] = sc;
        }

        return score;
    }


    public ArrayList<PaketMobilni> getPaketi() {
        return paketi;
    }

    public void setPaketi(ArrayList<PaketMobilni> paketi) {
        this.paketi = paketi;
    }

    public boolean[][] getKriterijumi() {
        return kriterijumi;
    }

    public void setKriterijumi(boolean[][] kriterijumi) {
        this.kriterijumi = kriterijumi;
    }

    public int getMinuti() {
        return minuti;
    }

    public void setMinuti(int minuti) {
        this.minuti = minuti;
    }

    public int getPoruke() {
        return poruke;
    }

    public void setPoruke(int poruke) {
        this.poruke = poruke;
    }

    public int getNet() {
        return net;
    }

    public void setNet(int net) {
        this.net = net;
    }

    public boolean isRoming() {
        return roming;
    }

    public void setRoming(boolean roming) {
        this.roming = roming;
    }
}
