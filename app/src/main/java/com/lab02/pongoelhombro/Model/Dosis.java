package com.lab02.pongoelhombro.Model;

class Dosis {

    String DosCod, DosUsuCod, DosPri, DosSeg, DosTer, DosVacCod, DosEst;

    public Dosis() {
    }

    public Dosis(String dosUsuCod) {
        DosUsuCod = dosUsuCod;
    }

    public String getDosCod() {
        return DosCod;
    }

    public void setDosCod(String dosCod) {
        DosCod = dosCod;
    }

    public String getDosUsuCod() {
        return DosUsuCod;
    }

    public void setDosUsuCod(String dosUsuCod) {
        DosUsuCod = dosUsuCod;
    }

    public String getDosPri() {
        return DosPri;
    }

    public void setDosPri(String dosPri) {
        DosPri = dosPri;
    }

    public String getDosSeg() {
        return DosSeg;
    }

    public void setDosSeg(String dosSeg) {
        DosSeg = dosSeg;
    }

    public String getDosTer() {
        return DosTer;
    }

    public void setDosTer(String dosTer) {
        DosTer = dosTer;
    }

    public String getDosVacCod() {
        return DosVacCod;
    }

    public void setDosVacCod(String dosVacCod) {
        DosVacCod = dosVacCod;
    }

    public String getDosEst() {
        return DosEst;
    }

    public void setDosEst(String dosEst) {
        DosEst = dosEst;
    }
}
