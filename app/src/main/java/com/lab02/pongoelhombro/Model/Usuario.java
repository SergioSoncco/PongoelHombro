package com.lab02.pongoelhombro.Model;

class Usuario {

    private String UsuCod, UsuCor, UsuCon, UsuLon, UsuLat;

    public Usuario(String usuNom, String usuDni) {
        UsuCor = usuNom;
        UsuCon = usuDni;
    }

    public String getUsuCod() {
        return UsuCod;
    }

    public void setUsuCod(String usuCod) {
        UsuCod = usuCod;
    }

    public String getUsuCor() {
        return UsuCor;
    }

    public void setUsuCor(String usuNom) {
        UsuCor = usuNom;
    }

    public String getUsuCon() {
        return UsuCon;
    }

    public void setUsuCon(String usuDni) {
        UsuCon = usuDni;
    }

    public String getUsuLon() {
        return UsuLon;
    }

    public void setUsuLon(String usuLon) {
        UsuLon = usuLon;
    }

    public String getUsuLat() {
        return UsuLat;
    }

    public void setUsuLat(String usuLat) {
        UsuLat = usuLat;
    }
}
