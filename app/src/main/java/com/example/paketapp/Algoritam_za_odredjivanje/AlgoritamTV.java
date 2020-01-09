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
    int brKanala;
    int  unazad;
    boolean snimanje;
    boolean hbo;
    int bitnok;
    int bitnon;
    int bitnos;

    public AlgoritamTV(ArrayList<PaketTV> pak,int brKanala,int unazad,boolean snimanje,boolean hbo,
                 int bitnok,int bitnon,int bitnos)
    {
        this.paketi = pak;
        score= new int[pak.size()];
        kriterijumi = new boolean[pak.size()][5];
        this.brKanala = brKanala;
        this.unazad= unazad;
        this.snimanje = snimanje;
        this.hbo = hbo;
        this.bitnok = bitnok;
        this.bitnon = bitnon;
        this.bitnos = bitnos;
        Arrays.fill(score, 0);
        Arrays.fill(kriterijumi, false);
    }

    public int[] runAlgo()
    {

        for(int i=0;i<paketi.size();i++)
        {
            PaketTV p = paketi.get(i);
            kriterijumi[i][0] = p.getBrojKanala()>=brKanala;
            kriterijumi[i][1] = (unazad == p.getGledanjaNazad());
            kriterijumi[i][2] = (snimanje == p.isSnimanjeSadrzaja());
            //todo
            //zavrsi
            //ukljuci i cenu u algoritam

            int sc = 0;
            sc+=(kriterijumi[i][0]) ? (bitnok) : (-bitnok);
            sc+=(kriterijumi[i][1]) ? (bitnon) : (-bitnon);
            sc+=(kriterijumi[i][2]) ? (bitnos) : (-bitnos);

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
        return brKanala;
    }

    public void setBrKanala(int brKanala) {
        this.brKanala = brKanala;
    }

    public int getUnazad() {
        return unazad;
    }

    public void setUnazad(int unazad) {
        this.unazad = unazad;
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
