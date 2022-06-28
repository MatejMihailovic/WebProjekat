package main.java.com.projekat.WebProjekat.dto;

import main.java.com.projekat.WebProjekat.entity.Korisnik;
import main.java.com.projekat.WebProjekat.entity.Pol;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RegisterDto {
    private String korisnickoIme;
    private String lozinka;
    private String ime;
    private String prezime;
    private Pol pol;
    private String datumRodjenja;

    public RegisterDto(String korisnickoIme, String lozinka, String ime, String prezime, Pol pol, Date datumRodjenja) {
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
        this.ime = ime;
        this.prezime = prezime;
        this.pol = pol;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        this.datumRodjenja = dateFormat.format(datumRodjenja);
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
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

    public Korisnik ToKorisnik() throws ParseException {
        Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(datumRodjenja);
        return new Korisnik(korisnickoIme, lozinka, ime, prezime, pol, date1);
    }
}
