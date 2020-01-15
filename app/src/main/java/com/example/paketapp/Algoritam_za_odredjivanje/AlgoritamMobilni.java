package com.example.paketapp.Algoritam_za_odredjivanje;

import com.example.paketapp.Paketi.PaketMobilni;

import java.util.ArrayList;
import java.util.Arrays;

public class AlgoritamMobilni
{
    ArrayList<PaketMobilni> paketi;
    int score[];
    boolean kriterijumi[][];
    int minutiMesecno;
    int porukeMesecno;
    int internetMesecno;
    boolean roming;
    int porukeBitnost;
    int minutiBitno;
    int internetBitnost;
    int romingBitnost;

    public AlgoritamMobilni(ArrayList<PaketMobilni> pak,int minutiMesecno,int porukeMesecno,int internetMesecno,boolean roming,
           int porukeBitnost,int minutiBitno,int internetBitnost,int romingBitnost)
    {
        this.porukeBitnost = porukeBitnost;
        this.internetBitnost = internetBitnost;
        this.minutiBitno = minutiBitno;
        this.romingBitnost = romingBitnost;
        this.paketi = pak;
        this.minutiMesecno = minutiMesecno;
        this.porukeMesecno = porukeMesecno;
        this.internetMesecno = internetMesecno;
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
            if(p.getSms()>=porukeMesecno) kriterijumi[i][0] = true;
            if(p.getMinuti()>=minutiMesecno) kriterijumi[i][1] = true;
            if(p.getInternet()>=internetMesecno) kriterijumi[i][2] = true;
            kriterijumi[i][3] = ((p.getMinutiRoming() > 0) == roming);
            //todo
            //zavrsi
            //ukljuci i cenu u algoritam


            int sc = 0;
            sc = sc - p.getCena();
            sc+=(kriterijumi[i][0]) ? (porukeBitnost) : (-porukeBitnost);
            sc+=(kriterijumi[i][1]) ? (minutiBitno) : (-minutiBitno);
            sc+=(kriterijumi[i][2]) ? (internetBitnost) : (-internetBitnost);
            sc+=(kriterijumi[i][3]) ? (romingBitnost) : (-romingBitnost);

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
        return minutiMesecno;
    }

    public void setMinuti(int minutiMesecno) {
        this.minutiMesecno = minutiMesecno;
    }

    public int getPoruke() {
        return porukeMesecno;
    }

    public void setPoruke(int porukeMesecno) {
        this.porukeMesecno = porukeMesecno;
    }

    public int getNet() {
        return internetMesecno;
    }

    public void setNet(int internetMesecno) {
        this.internetMesecno = internetMesecno;
    }

    public boolean isRoming() {
        return roming;
    }

    public void setRoming(boolean roming) {
        this.roming = roming;
    }
}
