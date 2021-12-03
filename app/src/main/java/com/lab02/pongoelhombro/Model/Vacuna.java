package com.lab02.pongoelhombro.Model;

public class Vacuna {

    String VacCod, VacLab, VacInf, VacPai, VacSin, VacEst;

    public Vacuna(String VacPai, String VacLab, String VacSin) {
        this.setVacPai(VacPai);
        this.setVacLab(VacLab);
        this.setVacSin(VacSin);
    }

    public String getVacCod() {
        return VacCod;
    }

    public void setVacCod(String vacCod) {
        VacCod = vacCod;
    }

    public String getVacLab() {
        return VacLab;
    }

    public void setVacLab(String vacLab) {
        VacLab = vacLab;
    }

    public String getVacInf() {
        return VacInf;
    }

    public void setVacInf(String vacInf) {
        VacInf = vacInf;
    }

    public String getVacPai() {
        return VacPai;
    }

    public void setVacPai(String vacPai) {
        VacPai = vacPai;
    }

    public String getVacSin() {
        return VacSin;
    }

    public void setVacSin(String vacSin) {
        VacSin = vacSin;
    }

    public String getVacEst() {
        return VacEst;
    }

    public void setVacEst(String vacEst) {
        VacEst = vacEst;
    }
}
