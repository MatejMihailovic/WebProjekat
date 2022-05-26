package main.java.com.projekat.WebProjekat.dto.MenadzerDto;

import main.java.com.projekat.WebProjekat.entity.Menadzer;
import main.java.com.projekat.WebProjekat.entity.Pol;
import main.java.com.projekat.WebProjekat.entity.Restoran;

import java.util.Date;

public class MenadzerDto {
    private String korisnickoIme;

    private String ime;

    private String prezime;

    private Pol pol;

    private Date datumRodjenja;

    private Restoran restoran;

    public MenadzerDto(String korisnickoIme, String ime, String prezime, Pol pol, Date datumRodjenja, Restoran restoran) {
        this.korisnickoIme = korisnickoIme;
        this.ime = ime;
        this.prezime = prezime;
        this.pol = pol;
        this.datumRodjenja = datumRodjenja;
        this.restoran = restoran;
    }

    public MenadzerDto(Menadzer menadzer) {
        this.korisnickoIme = menadzer.getKorisnickoIme();
        this.ime = menadzer.getIme();
        this.prezime = menadzer.getPrezime();
        this.pol = menadzer.getPol();
        this.datumRodjenja = menadzer.getDatumRodjenja();
        this.restoran = menadzer.getRestoran();
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

    public Restoran getRestoran() {
        return restoran;
    }

    public void setRestoran(Restoran restoran) {
        this.restoran = restoran;
    }
}
