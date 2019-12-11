package com.example.paketapp.Paketi;

public class BoxPaket
{
    String ime;
     int cena;
     PaketNet paketNet;
     PaketTV paketTV;
     PaketMobilni paketMobilni;

     public BoxPaket(String ime,int cena,PaketNet paketNet,PaketTV paketTV,PaketMobilni paketMobilni)
     {
         this.ime = ime;
         this.cena=cena;
         this.paketMobilni=paketMobilni;
         this.paketNet=paketNet;
         this.paketTV=paketTV;
     }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public int getCena() {
        return cena;
    }

    public void setCena(int cena) {
        this.cena = cena;
    }

    public PaketNet getPaketNet() {
        return paketNet;
    }

    public void setPaketNet(PaketNet paketNet) {
        this.paketNet = paketNet;
    }

    public PaketTV getPaketTV() {
        return paketTV;
    }

    public void setPaketTV(PaketTV paketTV) {
        this.paketTV = paketTV;
    }

    public PaketMobilni getPaketMobilni() {
        return paketMobilni;
    }

    public void setPaketMobilni(PaketMobilni paketMobilni) {
        this.paketMobilni = paketMobilni;
    }
}
