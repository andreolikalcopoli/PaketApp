package com.example.paketapp.Paketi;

public class PaketNet
{
    int download;
    int upload;
    boolean wifiRuter;
    boolean dinamickiIp;
    boolean jedanMailNalog;
    int cena;

    public PaketNet(int cena,int download,int upload)
    {
        this.cena=cena;
        this.download=download;
        this.upload=upload;
        this.wifiRuter=true;
        this.dinamickiIp=true;
        this.jedanMailNalog=true;
    }

    public int getDownload() {
        return download;
    }

    public void setDownload(int download) {
        this.download = download;
    }

    public int getUpload() {
        return upload;
    }

    public void setUpload(int upload) {
        this.upload = upload;
    }

    public boolean isWifiRuter() {
        return wifiRuter;
    }

    public void setWifiRuter(boolean wifiRuter) {
        this.wifiRuter = wifiRuter;
    }

    public boolean isDinamickiIp() {
        return dinamickiIp;
    }

    public void setDinamickiIp(boolean dinamickiIp) {
        this.dinamickiIp = dinamickiIp;
    }

    public boolean isJedanMailNalog() {
        return jedanMailNalog;
    }

    public void setJedanMailNalog(boolean jedanMailNalog) {
        this.jedanMailNalog = jedanMailNalog;
    }

    public int getCena() {
        return cena;
    }

    public void setCena(int cena) {
        this.cena = cena;
    }
}
