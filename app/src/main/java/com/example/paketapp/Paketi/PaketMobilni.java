package com.example.paketapp.Paketi;

import java.util.ArrayList;

public class PaketMobilni
{
     int cena;
     int minuti;
     int minutiMreza;
     int sms;
     int internet;
     int gbProstora;
     boolean josJedanGbZaKupovinu;
     ArrayList<String> aplikacijeInternet;
     boolean porukeRoming;
     boolean internetRoming;
     int minutiRoming;

     public PaketMobilni(int cena,int minuti,int minutiMreza,int sms,int internet,int gbProstora,int minutiRoming,
                         ArrayList<String> aplikacijeInternet,boolean josJedanGbZaKupovinu,boolean porukeRoming,boolean internetRoming)
     {
         this.cena=cena;
         this.minuti=minuti;
         this.minutiMreza=minutiMreza;
         this.sms=sms;
         this.internet=internet;
         this.gbProstora = gbProstora;
         this.minutiRoming = minutiRoming;
         this.aplikacijeInternet = aplikacijeInternet;
         this.josJedanGbZaKupovinu = josJedanGbZaKupovinu;
         this.porukeRoming = porukeRoming;
         this.internetRoming = internetRoming;
     }

    public int getCena() {
        return cena;
    }

    public void setCena(int cena) {
        this.cena = cena;
    }

    public int getMinuti() {
        return minuti;
    }

    public void setMinuti(int minuti) {
        this.minuti = minuti;
    }

    public int getMinutiMreza() {
        return minutiMreza;
    }

    public void setMinutiMreza(int minutiMreza) {
        this.minutiMreza = minutiMreza;
    }

    public int getSms() {
        return sms;
    }

    public void setSms(int sms) {
        this.sms = sms;
    }

    public int getInternet() {
        return internet;
    }

    public void setInternet(int internet) {
        this.internet = internet;
    }

    public int getGbProstora() {
        return gbProstora;
    }

    public void setGbProstora(int gbProstora) {
        this.gbProstora = gbProstora;
    }

    public boolean isJosJedanGbZaKupovinu() {
        return josJedanGbZaKupovinu;
    }

    public void setJosJedanGbZaKupovinu(boolean josJedanGbZaKupovinu) {
        this.josJedanGbZaKupovinu = josJedanGbZaKupovinu;
    }

    public ArrayList<String> getAplikacijeInternet() {
        return aplikacijeInternet;
    }

    public void setAplikacijeInternet(ArrayList<String> aplikacijeInternet) {
        this.aplikacijeInternet = aplikacijeInternet;
    }

    public boolean isPorukeRoming() {
        return porukeRoming;
    }

    public void setPorukeRoming(boolean porukeRoming) {
        this.porukeRoming = porukeRoming;
    }

    public boolean isInternetRoming() {
        return internetRoming;
    }

    public void setInternetRoming(boolean internetRoming) {
        this.internetRoming = internetRoming;
    }

    public int getMinutiRoming() {
        return minutiRoming;
    }

    public void setMinutiRoming(int minutiRoming) {
        this.minutiRoming = minutiRoming;
    }
}
