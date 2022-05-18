package main.java.com.projekat.WebProjekat.dto;

import main.java.com.projekat.WebProjekat.entity.Artikal;

public class ArtikalPrikazDto {
    String opis;
    double cena;

    public ArtikalPrikazDto(String opis, double cena) {
        this.opis = opis;
        this.cena = cena;
    }

    public ArtikalPrikazDto(Artikal artikal) {
        this.opis = artikal.getOpis();
        this.cena = artikal.getCena();
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
