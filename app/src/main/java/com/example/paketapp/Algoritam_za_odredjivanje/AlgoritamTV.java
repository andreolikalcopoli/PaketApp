package com.example.paketapp.Algoritam_za_odredjivanje;

import com.example.paketapp.Paketi.PaketMobilni;
import com.example.paketapp.Paketi.PaketTV;

import java.util.ArrayList;
import java.util.Arrays;

public class AlgoritamTV
{
    ArrayList<PaketTV> paketi;
    int score[];
    boolean kriterijumi[][];
    int brojKanala;
    int  nazad;
    boolean snimanje;
    boolean hbo;
    int kanaliBitnost;
    int nazadBitnost;
    int snimanjeBitnost;
    int hboBitnost;

    public AlgoritamTV(ArrayList<PaketTV> pak,int brojKanala,int nazad,boolean snimanje,boolean hbo,
                 int kanaliBitnost,int nazadBitnost,int snimanjeBitnost,int hboBitnost)
    {
        this.hboBitnost = hboBitnost;
        this.paketi = pak;
        score= new int[pak.size()];
        kriterijumi = new boolean[pak.size()][10];
        this.brojKanala = brojKanala;
        this.nazad= nazad;
        this.snimanje = snimanje;
        this.hbo = hbo;
        this.kanaliBitnost = kanaliBitnost;
        this.nazadBitnost = nazadBitnost;
        this.snimanjeBitnost = snimanjeBitnost;
        Arrays.fill(score, 0);
    }

    public int[] runAlgo()
    {
        for(int i=0;i<paketi.size();i++)
        {
            for(int j=0;j<10;j++) kriterijumi[i][j] = false;
        }

        for(int i=0;i<paketi.size();i++)
        {
            PaketTV p = paketi.get(i);
            kriterijumi[i][0] = p.getBrojKanala()>=brojKanala;
            kriterijumi[i][1] = (nazad == p.getGledanjaNazad());
            kriterijumi[i][2] = (snimanje == p.isSnimanjeSadrzaja());
            boolean x = p.getVideoKlub().contains("Hbo");
            kriterijumi[i][3] = (x==hbo);
            //todo
            //zavrsi
            //ukljuci i cenu u algoritam

            int sc = 0;
            sc = sc - p.getCena()/1000;
            sc+=(kriterijumi[i][0]) ? (kanaliBitnost) : (-kanaliBitnost);
            sc+=(kriterijumi[i][1]) ? (nazadBitnost) : (-nazadBitnost);
            sc+=(kriterijumi[i][2]) ? (snimanjeBitnost) : (-snimanjeBitnost);
            sc+=(kriterijumi[i][3]) ? (hboBitnost) : (-hboBitnost);

            score[i] = sc;
        }

        return score;
    }

    public ArrayList<PaketTV> getPaketi() {
        return paketi;
    }

    public void setPaketi(ArrayList<PaketTV> paketi) {
        this.paketi = paketi;
    }

    public int[] getScore() {
        return score;
    }

    public void setScore(int[] score) {
        this.score = score;
    }

    public boolean[][] getKriterijumi() {
        return kriterijumi;
    }

    public void setKriterijumi(boolean[][] kriterijumi) {
        this.kriterijumi = kriterijumi;
    }

    public int getBrKanala() {
        return brojKanala;
    }

    public void setBrKanala(int brojKanala) {
        this.brojKanala = brojKanala;
    }

    public int getUnazad() {
        return nazad;
    }

    public void setUnazad(int nazad) {
        this.nazad = nazad;
    }

    public boolean isSnimanje() {
        return snimanje;
    }

    public void setSnimanje(boolean snimanje) {
        this.snimanje = snimanje;
    }

    public boolean isHbo() {
        return hbo;
    }

    public void setHbo(boolean hbo) {
        this.hbo = hbo;
    }
}
