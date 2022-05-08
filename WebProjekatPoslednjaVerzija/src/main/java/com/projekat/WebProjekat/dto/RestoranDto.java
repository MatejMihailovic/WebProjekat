package com.projekat.WebProjekat.dto;

import com.projekat.WebProjekat.entity.Lokacija;
import com.projekat.WebProjekat.entity.Restoran;

public class RestoranDto {
    private String naziv;

    private String tipRestorana;

    private Lokacija lokacija;

    public RestoranDto(String naziv, String tipRestorana, Lokacija lokacija) {
        this.naziv = naziv;
        this.tipRestorana = tipRestorana;
        this.lokacija = lokacija;
    }

    public RestoranDto(Restoran restoran) {
        this.naziv = naziv;
        this.tipRestorana = tipRestorana;
        this.lokacija = lokacija;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getTipRestorana() {
        return tipRestorana;
    }

    public void setTipRestorana(String tipRestorana) {
        this.tipRestorana = tipRestorana;
    }

    public Lokacija getLokacija() {
        return lokacija;
    }

    public void setLokacija(Lokacija lokacija) {
        this.lokacija = lokacija;
    }
}
