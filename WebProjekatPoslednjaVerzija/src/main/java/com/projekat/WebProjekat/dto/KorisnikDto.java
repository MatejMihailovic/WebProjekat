package main.java.com.projekat.WebProjekat.dto;

import main.java.com.projekat.WebProjekat.entity.Korisnik;
import main.java.com.projekat.WebProjekat.entity.Pol;
import main.java.com.projekat.WebProjekat.entity.Uloga;

import java.text.SimpleDateFormat;
import java.util.Date;

public class KorisnikDto {
    private Long id;
    private String korisnickoIme;

    private String ime;

    private String prezime;

    private Pol pol;

    private Uloga uloga;

    private String datumRodjenja;

    public KorisnikDto(Long id, String korisnickoIme, String ime, String prezime, Pol pol, Uloga uloga, Date datumRodjenja) {
        this.id = id;
        this.korisnickoIme = korisnickoIme;
        this.ime = ime;
        this.prezime = prezime;
        this.pol = pol;
        this.uloga = uloga;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        this.datumRodjenja = dateFormat.format(datumRodjenja);
    }

    public KorisnikDto(Korisnik korisnik) {
        this.id = korisnik.getId();
        this.korisnickoIme = korisnik.getKorisnickoIme();
        this.ime = korisnik.getIme();
        this.prezime = korisnik.getPrezime();
        this.pol = korisnik.getPol();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        this.uloga = korisnik.getUloga();
        this.datumRodjenja = dateFormat.format(korisnik.getDatumRodjenja());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Uloga getUloga() {
        return uloga;
    }

    public void setUloga(Uloga uloga) {
        this.uloga = uloga;
    }

    public void setDatumRodjenja(String datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
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

    public void setDatumRodjenja(Date datumRodjenja) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        this.datumRodjenja = dateFormat.format(datumRodjenja);
    }
}
