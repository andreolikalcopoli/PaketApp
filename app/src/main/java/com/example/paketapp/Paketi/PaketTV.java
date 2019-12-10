package com.example.paketapp.Paketi;

import java.util.ArrayList;

public class PaketTV
{
    int cena;
    int brojKanala;
    boolean snimanjeSadrzaja;
    int gledanjaNazad;
    boolean pauziranje;
    ArrayList<String> videoKlub;

    public PaketTV(int cena,int brojKanala,boolean snimanjeSadrzaja,int gledanjaNazad,boolean pauziranje,ArrayList<String> videoKlub)
    {
        this.cena= cena;
        this.brojKanala=brojKanala;
        this.snimanjeSadrzaja=snimanjeSadrzaja;
        this.gledanjaNazad=gledanjaNazad;
        this.pauziranje=pauziranje;
        this.videoKlub=videoKlub;
    }

    public int getCena() {
        return cena;
    }

    public void setCena(int cena) {
        this.cena = cena;
    }

    public int getBrojKanala() {
        return brojKanala;
    }

    public void setBrojKanala(int brojKanala) {
        this.brojKanala = brojKanala;
    }

    public boolean isSnimanjeSadrzaja() {
        return snimanjeSadrzaja;
    }

    public void setSnimanjeSadrzaja(boolean snimanjeSadrzaja) {
        this.snimanjeSadrzaja = snimanjeSadrzaja;
    }

    public int getGledanjaNazad() {
        return gledanjaNazad;
    }

    public void setGledanjaNazad(int gledanjaNazad) {
        this.gledanjaNazad = gledanjaNazad;
    }

    public boolean isPauziranje() {
        return pauziranje;
    }

    public void setPauziranje(boolean pauziranje) {
        this.pauziranje = pauziranje;
    }

    public ArrayList<String> getVideoKlub() {
        return videoKlub;
    }

    public void setVideoKlub(ArrayList<String> videoKlub) {
        this.videoKlub = videoKlub;
    }
}
