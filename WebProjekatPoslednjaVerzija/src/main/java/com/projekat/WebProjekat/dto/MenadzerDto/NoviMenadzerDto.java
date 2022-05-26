package main.java.com.projekat.WebProjekat.dto.MenadzerDto;

import main.java.com.projekat.WebProjekat.entity.Menadzer;
import main.java.com.projekat.WebProjekat.entity.Pol;

import java.util.Date;

public class NoviMenadzerDto {
    private String korisnickoIme;

    private String lozinka;

    private String ime;

    private String prezime;

    private Pol pol;

    private String datumRodjenja;

    private String nazivRestorana;

    public NoviMenadzerDto(String korisnickoIme, String lozinka ,String ime, String prezime, Pol pol, String datumRodjenja, String nazivRestorana) {
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
        this.ime = ime;
        this.prezime = prezime;
        this.pol = pol;
        this.datumRodjenja = datumRodjenja;
        this.nazivRestorana = nazivRestorana;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
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

    public String getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(String datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }

    public String getNazivRestorana() {
        return nazivRestorana;
    }

    public void setNazivRestorana(String nazivRestorana) {
        this.nazivRestorana = nazivRestorana;
    }
}
