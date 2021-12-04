package com.lab02.pongoelhombro.Model;

public class Vacuna {

    String VacLab, VacPai, VacSin;

    public Vacuna(String VacPai, String VacLab, String VacSin) {
        this.setVacPai(VacPai);
        this.setVacLab(VacLab);
        this.setVacSin(VacSin);
    }



    public String getVacLab() {
        return VacLab;
    }

    public void setVacLab(String vacLab) {
        VacLab = vacLab;
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


}
