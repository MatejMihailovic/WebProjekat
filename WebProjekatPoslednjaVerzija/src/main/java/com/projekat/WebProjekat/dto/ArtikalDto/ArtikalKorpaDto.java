package main.java.com.projekat.WebProjekat.dto.ArtikalDto;

import main.java.com.projekat.WebProjekat.entity.Artikal;

public class ArtikalKorpaDto {
    Long id;
    private String naziv;

    private double kolicina;

    private double cena;

    public ArtikalKorpaDto(String naziv, double kolicina, double cena) {
        this.naziv = naziv;
        this.kolicina = kolicina;
        this.cena = cena;
    }

    public ArtikalKorpaDto(Artikal artikal) {
        this.id = artikal.getId();
        this.naziv = artikal.getNaziv();
        this.kolicina = artikal.getKolicina();
        this.cena = artikal.getCena();
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public double getKolicina() {
        return kolicina;
    }

    public void setKolicina(double kolicina) {
        this.kolicina = kolicina;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }
}
