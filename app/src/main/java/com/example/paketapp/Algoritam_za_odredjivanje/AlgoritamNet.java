package com.example.paketapp.Algoritam_za_odredjivanje;

import com.example.paketapp.Paketi.PaketMobilni;
import com.example.paketapp.Paketi.PaketNet;

import java.util.ArrayList;
import java.util.Arrays;

public class AlgoritamNet
{
    ArrayList<PaketNet> paketi;
    int score[];
    boolean kriterijumi[][];
    int bitnob;
    int brzina;

    public AlgoritamNet(ArrayList<PaketNet> paketi,int brzina,int bitnob)
    {
        this.paketi=paketi;
        score = new int[paketi.size()];
        kriterijumi = new boolean[paketi.size()][10];
        this.brzina = brzina;
        this.bitnob=bitnob;
       // Arrays.fill(kriterijumi, false);
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
            PaketNet p = paketi.get(i);

            kriterijumi[i][0]  = brzina<=p.getDownload();

            //todo
            //zavrsi
            //ukljuci i cenu u algoritam

            int sc = 0;

            sc += (kriterijumi[i][0]) ? (bitnob) : (-bitnob);

            score[i] = sc;
        }

        return score;
    }

    public ArrayList<PaketNet> getPaketi() {
        return paketi;
    }

    public void setPaketi(ArrayList<PaketNet> paketi) {
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

    public int getBitnob() {
        return bitnob;
    }

    public void setBitnob(int bitnob) {
        this.bitnob = bitnob;
    }

    public int getBrzina() {
        return brzina;
    }

    public void setBrzina(int brzina) {
        this.brzina = brzina;
    }
}
