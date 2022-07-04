package main.java.com.projekat.WebProjekat.dto.PorudzbinaDto;

import main.java.com.projekat.WebProjekat.entity.Artikal;
import main.java.com.projekat.WebProjekat.entity.Porudzbina;
import main.java.com.projekat.WebProjekat.entity.Restoran;
import main.java.com.projekat.WebProjekat.entity.Status;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class PorudzbinaDto {
    Set<Artikal> poruceniArtikli;
    Restoran restoran;
    String datumIVreme;
    double cena;
    Status status;
    String korisnickoIme;

    public PorudzbinaDto(Set<Artikal> poruceniArtikli, Restoran restoran, double cena, Status status, String korisnickoIme) {
        this.poruceniArtikli = poruceniArtikli;
        this.restoran = restoran;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        this.datumIVreme = formatter.format(date);
        this.cena = cena;
        this.status = status;
        this.korisnickoIme = korisnickoIme;
    }

    public PorudzbinaDto(Porudzbina porudzbina){
        this.poruceniArtikli = porudzbina.getPoruceniArtikli();
        this.restoran = porudzbina.getRestoran();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        this.datumIVreme = formatter.format(porudzbina.getDatum());
        this.cena = porudzbina.getCena();
        this.status = porudzbina.getStatus();
        this.korisnickoIme = porudzbina.getKorisnickoIme();
    }

    public Set<Artikal> getPoruceniArtikli() {
        return poruceniArtikli;
    }

    public void setPoruceniArtikli(Set<Artikal> poruceniArtikli) {
        this.poruceniArtikli = poruceniArtikli;
    }

    public Restoran getRestoran() {
        return restoran;
    }

    public void setRestoran(Restoran restoran) {
        this.restoran = restoran;
    }

    public String getDatumIVreme() {
        return datumIVreme;
    }

    public void setDatumIVreme(String datumIVreme) {
        this.datumIVreme = datumIVreme;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }
}
