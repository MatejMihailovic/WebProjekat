package com.projekat.WebProjekat.dto;

import com.projekat.WebProjekat.entity.Korisnik;
import com.projekat.WebProjekat.entity.Pol;

import java.util.Date;

public class KorisnikDto {
    private String korisnickoIme;

    private String ime;

    private String prezime;

    private Pol pol;

    private Date datumRodjenja;

    public KorisnikDto(String korisnickoIme, String ime, String prezime, Pol pol, Date datumRodjenja) {
        this.korisnickoIme = korisnickoIme;
        this.ime = ime;
        this.prezime = prezime;
        this.pol = pol;
        this.datumRodjenja = datumRodjenja;
    }

    public KorisnikDto(Korisnik korisnik) {
        this.korisnickoIme = korisnik.getKorisnickoIme();
        this.ime = korisnik.getIme();
        this.prezime = korisnik.getPrezime();
        this.pol = korisnik.getPol();
        this.datumRodjenja = korisnik.getDatumRodjenja();
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public Pol getPol() {
        return pol;
    }

    public void setPol(Pol pol) {
        this.pol = pol;
    }

    public Date getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(Date datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }
}
