package main.java.com.projekat.WebProjekat.dto;

import main.java.com.projekat.WebProjekat.entity.Lokacija;

public class NoviRestoranDto {
    private String naziv;

    private String tipRestorana;

    private Lokacija lokacija;

    private String korisnickoImeMenadzera;

    public NoviRestoranDto(String naziv, String tipRestorana, Lokacija lokacija, String korisnickoImeMenadzera) {
        this.naziv = naziv;
        this.tipRestorana = tipRestorana;
        this.lokacija = lokacija;
        this.korisnickoImeMenadzera = korisnickoImeMenadzera;
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

    public String getKorisnickoImeMenadzera() {
        return korisnickoImeMenadzera;
    }

    public void setKorisnickoImeMenadzera(String korisnickoImeMenadzera) {
        this.korisnickoImeMenadzera = korisnickoImeMenadzera;
    }
}
