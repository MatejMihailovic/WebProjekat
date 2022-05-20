package main.java.com.projekat.WebProjekat.dto;

import main.java.com.projekat.WebProjekat.entity.Lokacija;

public class KreirajRestoranDto {
    private String kImeMenadzera;

    private String naziv;

    private String tipRestorana;

    private Lokacija lokacija;

    public KreirajRestoranDto(String kImeMenadzera, String naziv, String tipRestorana, Lokacija lokacija) {
        this.kImeMenadzera = kImeMenadzera;
        this.naziv = naziv;
        this.tipRestorana = tipRestorana;
        this.lokacija = lokacija;
    }

    public String getkImeMenadzera() {
        return kImeMenadzera;
    }

    public void setkImeMenadzera(String kImeMenadzera) {
        this.kImeMenadzera = kImeMenadzera;
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
