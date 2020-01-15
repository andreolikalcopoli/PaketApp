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

    int brojKanala;
    int  nazad;
    boolean snimanje;
    boolean hbo;
    int kanaliBitnost;
    int internetBitnost;
    int snimanjeBitnost;

    int minutiMesecno;
    int porukeMesecno;
    int internetMesecno;
    boolean roming;
    int porukeBitnost;
    int minutiBitnost;
    int nazadBitnost;
    int romingBitnost;

    int brzinaBitnost;
    int brzina;

    int mobilniBitno,tvBitnost,netBitnost;

    public int [] scoremob;
    public int [] scorenet;
    public int [] scoretv;
    public int [] scorebox;
    int hboBitnost;

    public Algoritam(ArrayList<PaketTV> paketiTV, ArrayList<PaketMobilni> paketiMob, ArrayList<PaketNet> paketiNet,
      int minutiMesecno,int porukeDnevno,int internetMesecno,boolean roming, int porukeBitnost,int minutiBitnost,int internetBitnost,int romingBitnost,
                     int brzina,int brzinaBitnost,
                     int brojKanala,int nazad,boolean snimanje,boolean hbo, int kanaliBitnost,int nazadBitnost,int snimanjeBitnost,int hboBitnost,
                     int mobilniBitno,int tvBitnost,int netBitnost)
    {
        this.paketiTV = paketiTV;
        this.paketiMob = paketiMob;
        this.paketiNet = paketiNet;

        this.brojKanala = brojKanala;
        this.nazad= nazad;
        this.snimanje = snimanje;
        this.hbo = hbo;
        this.kanaliBitnost = kanaliBitnost;
        this.nazadBitnost = nazadBitnost;
        this.snimanjeBitnost = snimanjeBitnost;
        this.hboBitnost = hboBitnost;

        this.porukeBitnost = porukeBitnost;
        this.internetBitnost = internetBitnost;
        this.minutiBitnost = minutiBitnost;
        this.romingBitnost = romingBitnost;
        this.minutiMesecno = minutiMesecno;
        this.porukeMesecno = porukeDnevno;
        this.internetMesecno = internetMesecno;
        this.roming = roming;

        this.brzina = brzina;
        this.brzinaBitnost=brzinaBitnost;

        this.mobilniBitno = mobilniBitno;
        this.netBitnost = netBitnost;
        this.tvBitnost=tvBitnost;

        scorebox= new int[40];
    }

    public int [] runAlgo()
    {
        AlgoritamMobilni amob = new AlgoritamMobilni(paketiMob,minutiMesecno,porukeMesecno,internetMesecno,roming,porukeBitnost,minutiBitnost,internetBitnost,romingBitnost);
        AlgoritamNet alnet = new AlgoritamNet(paketiNet,brzina,brzinaBitnost);
        AlgoritamTV altv = new AlgoritamTV(paketiTV,brojKanala,nazad,snimanje,hbo,kanaliBitnost,nazadBitnost,snimanjeBitnost,hboBitnost);
        scoremob  = amob.runAlgo();
        scorenet = alnet.runAlgo();
        scoretv = altv.runAlgo();

        for(int i=0;i<paketiMob.size();i++)
        {
            scoremob[i] = scoremob[i]*(mobilniBitno);
        }

        for(int i=0;i<paketiNet.size();i++)
        {
            scorenet[i] = scorenet[i]*(netBitnost);
        }

        for(int i=0;i<paketiTV.size();i++)
        {
            scoretv[i] = scoretv[i]*tvBitnost;
        }

        scorebox[0] = scoremob[0] + scoretv[0] + scorenet[0];
        scorebox[1] = scoremob[1] + scoretv[1] + scorenet[1];
        scorebox[2] = scoremob[2] + scoretv[2] + scorenet[2];
        scorebox[3] = scoremob[3] + scoretv[2] + scorenet[5];

        return scorebox;
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

    public int getBitnok() {
        return kanaliBitnost;
    }

    public void setBitnok(int kanaliBitnost) {
        this.kanaliBitnost = kanaliBitnost;
    }

    public int getBitnon() {
        return internetBitnost;
    }

    public void setBitnon(int internetBitnost) {
        this.internetBitnost = internetBitnost;
    }

    public int getBitnos() {
        return snimanjeBitnost;
    }

    public void setBitnos(int snimanjeBitnost) {
        this.snimanjeBitnost = snimanjeBitnost;
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

    public int getBitnop() {
        return porukeBitnost;
    }

    public void setBitnop(int porukeBitnost) {
        this.porukeBitnost = porukeBitnost;
    }

    public int getBitnom() {
        return minutiBitnost;
    }

    public void setBitnom(int minutiBitnost) {
        this.minutiBitnost = minutiBitnost;
    }

    public int getBitnona() {
        return nazadBitnost;
    }

    public void setBitnona(int nazadBitnost) {
        this.nazadBitnost = nazadBitnost;
    }

    public int getBitnor() {
        return romingBitnost;
    }

    public void setBitnor(int romingBitnost) {
        this.romingBitnost = romingBitnost;
    }

    public int getBitnob() {
        return brzinaBitnost;
    }

    public void setBitnob(int brzinaBitnost) {
        this.brzinaBitnost = brzinaBitnost;
    }

    public int getBrzina() {
        return brzina;
    }

    public void setBrzina(int brzina) {
        this.brzina = brzina;
    }
}
