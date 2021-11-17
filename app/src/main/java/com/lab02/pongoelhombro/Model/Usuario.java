package com.lab02.pongoelhombro.Model;

class Usuario {

    private String UsuCod, UsuCor, UsuDni, UsuLon, UsuLat;

    public Usuario(String usuNom, String usuDni) {
        UsuCor = usuNom;
        UsuDni = usuDni;
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

    public String getUsuDni() {
        return UsuDni;
    }

    public void setUsuDni(String usuDni) {
        UsuDni = usuDni;
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
