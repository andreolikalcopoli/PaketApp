package com.example.paketapp.Algoritam_za_odredjivanje;

import com.example.paketapp.Paketi.PaketMobilni;
import com.example.paketapp.Paketi.PaketNet;
import com.example.paketapp.Paketi.PaketTV;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class Algoritam
{
    ArrayList<PaketTV> paketiTV;
    ArrayList<PaketMobilni> paketiMob;
    ArrayList<PaketNet> paketiNet;

    int brKanala;
    int  unazad;
    boolean snimanje;
    boolean hbo;
    int bitnok;
    int bitnon;
    int bitnos;

    int minuti;
    int poruke;
    int net;
    boolean roming;
    int bitnop;
    int bitnom;
    int bitnona;
    int bitnor;

    int bitnob;
    int brzina;

    int bitanmob,bitantv,bitannet;

    public int [] scoremob;
    public int [] scorenet;
    public int [] scoretv;
    public int [] scorebox;

    public Algoritam(ArrayList<PaketTV> paketiTV, ArrayList<PaketMobilni> paketiMob, ArrayList<PaketNet> paketiNet,
      int minutiDnevno,int porukeDnevno,int gbDnevno,boolean roming, int bitnop,int bitnom,int bitnon,int bitnor,
                     int brzina,int bitnob,
                     int brKanala,int unazad,boolean snimanje,boolean hbo, int bitnok,int bitnona,int bitnos,
                     int bitanmob,int bitantv,int bitannet)
    {
        this.paketiTV = paketiTV;
        this.paketiMob = paketiMob;
        this.paketiNet = paketiNet;

        this.brKanala = brKanala;
        this.unazad= unazad;
        this.snimanje = snimanje;
        this.hbo = hbo;
        this.bitnok = bitnok;
        this.bitnona = bitnona;
        this.bitnos = bitnos;

        this.bitnop = bitnop;
        this.bitnon = bitnon;
        this.bitnom = bitnom;
        this.bitnor = bitnor;
        this.minuti = minutiDnevno;
        this.poruke = porukeDnevno;
        this.net = gbDnevno;
        this.roming = roming;

        this.brzina = brzina;
        this.bitnob=bitnob;

        this.bitanmob = bitanmob;
        this.bitannet = bitannet;
        this.bitantv=bitantv;

        scorebox= new int[40];
    }

    public void runAlgo()
    {
        AlgoritamMobilni amob = new AlgoritamMobilni(paketiMob,minuti,poruke,net,roming,bitnop,bitnom,bitnon,bitnor);
        AlgoritamNet alnet = new AlgoritamNet(paketiNet,brzina,bitnob);
        AlgoritamTV altv = new AlgoritamTV(paketiTV,brKanala,unazad,snimanje,hbo,bitnok,bitnona,bitnos);
        scoremob  = amob.runAlgo();
        scorenet = alnet.runAlgo();
        scoretv = altv.runAlgo();

        for(int i=0;i<paketiMob.size();i++)
        {
            scoremob[i] = scoremob[i]*(bitanmob);
        }

        for(int i=0;i<paketiNet.size();i++)
        {
            scorenet[i] = scorenet[i]*(bitannet);
        }

        for(int i=0;i<paketiTV.size();i++)
        {
            scoretv[i] = scoretv[i]*bitantv;
        }

        scorebox[0] = scoremob[0] + scoretv[0] + scorenet[0];
        scorebox[1] = scoremob[1] + scoretv[1] + scorenet[1];
        scorebox[2] = scoremob[2] + scoretv[2] + scorenet[2];
        scorebox[3] = scoremob[3] + scoretv[2] + scorenet[5];
    }

    public ArrayList<PaketTV> getPaketiTV() {
        return paketiTV;
    }

    public void setPaketiTV(ArrayList<PaketTV> paketiTV) {
        this.paketiTV = paketiTV;
    }

    public ArrayList<PaketMobilni> getPaketiMob() {
        return paketiMob;
    }

    public void setPaketiMob(ArrayList<PaketMobilni> paketiMob) {
        this.paketiMob = paketiMob;
    }

    public ArrayList<PaketNet> getPaketiNet() {
        return paketiNet;
    }

    public void setPaketiNet(ArrayList<PaketNet> paketiNet) {
        this.paketiNet = paketiNet;
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

    public int getBitnok() {
        return bitnok;
    }

    public void setBitnok(int bitnok) {
        this.bitnok = bitnok;
    }

    public int getBitnon() {
        return bitnon;
    }

    public void setBitnon(int bitnon) {
        this.bitnon = bitnon;
    }

    public int getBitnos() {
        return bitnos;
    }

    public void setBitnos(int bitnos) {
        this.bitnos = bitnos;
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

    public int getBitnop() {
        return bitnop;
    }

    public void setBitnop(int bitnop) {
        this.bitnop = bitnop;
    }

    public int getBitnom() {
        return bitnom;
    }

    public void setBitnom(int bitnom) {
        this.bitnom = bitnom;
    }

    public int getBitnona() {
        return bitnona;
    }

    public void setBitnona(int bitnona) {
        this.bitnona = bitnona;
    }

    public int getBitnor() {
        return bitnor;
    }

    public void setBitnor(int bitnor) {
        this.bitnor = bitnor;
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
