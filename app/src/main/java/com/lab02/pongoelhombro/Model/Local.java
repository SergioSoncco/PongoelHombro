package com.lab02.pongoelhombro.Model;

public class Local {

    int LocCenVacCod, LocUbiCod;
    double LocLon, LocLat;
    String LonNom;
    public Local(int locCenVacCod, int locUbiCod, double locLat, double locLon, String lonNom) {
        LocCenVacCod = locCenVacCod;
        LocUbiCod = locUbiCod;
        LocLon = locLon;
        LocLat = locLat;
        LonNom = lonNom;
    }

    public int getLocCenVacCod() {
        return LocCenVacCod;
    }

    public void setLocCenVacCod(int locCenVacCod) {
        LocCenVacCod = locCenVacCod;
    }

    public int getLocUbiCod() {
        return LocUbiCod;
    }

    public void setLocUbiCod(int locUbiCod) {
        LocUbiCod = locUbiCod;
    }

    public double getLocLon() {
        return LocLon;
    }

    public void setLocLon(double locLon) {
        LocLon = locLon;
    }

    public double getLocLat() {
        return LocLat;
    }

    public void setLocLat(double locLat) {
        LocLat = locLat;
    }

    public String getLonNom() {
        return LonNom;
    }

    public void setLonNom(String lonNom) {
        LonNom = lonNom;
    }
}
