package main.java.com.projekat.WebProjekat.dto.MenadzerDto;

import main.java.com.projekat.WebProjekat.entity.Menadzer;
import main.java.com.projekat.WebProjekat.entity.Pol;
import main.java.com.projekat.WebProjekat.entity.Restoran;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PrikazMenadzerDto {
    private String korisnickoIme;

    private String ime;

    private String prezime;

    private Pol pol;

    private String datumRodjenja;

    private Restoran restoran;

    public PrikazMenadzerDto(String korisnickoIme, String ime, String prezime, Pol pol, String datumRodjenja, Restoran restoran) {
        this.korisnickoIme = korisnickoIme;
        this.ime = ime;
        this.prezime = prezime;
        this.pol = pol;
        this.datumRodjenja = datumRodjenja;
        this.restoran = restoran;
    }

    public PrikazMenadzerDto(Menadzer menadzer) {
        this.korisnickoIme = menadzer.getKorisnickoIme();
        this.ime = menadzer.getIme();
        this.prezime = menadzer.getPrezime();
        this.pol = menadzer.getPol();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String datumRodjenja = dateFormat.format(menadzer.getDatumRodjenja());
        this.datumRodjenja = datumRodjenja;
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

    public String getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(String datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }

    public Restoran getRestoran() {
        return restoran;
    }

    public void setRestoran(Restoran restoran) {
        this.restoran = restoran;
    }
}
