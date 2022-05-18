package main.java.com.projekat.WebProjekat.dto;

import main.java.com.projekat.WebProjekat.entity.Artikal;

public class ArtikalPrikazDto {
    String naziv;
    String opis;
    double cena;

    public ArtikalPrikazDto(String naziv, String opis, double cena) {
        this.opis = opis;
        this.cena = cena;
    }

    public ArtikalPrikazDto(Artikal artikal) {
        this.naziv = artikal.getNaziv();
        this.opis = artikal.getOpis();
        this.cena = artikal.getCena();
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }
}
