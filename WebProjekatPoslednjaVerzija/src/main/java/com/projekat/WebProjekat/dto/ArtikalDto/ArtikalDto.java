package main.java.com.projekat.WebProjekat.dto.ArtikalDto;

import main.java.com.projekat.WebProjekat.entity.Artikal;
import main.java.com.projekat.WebProjekat.entity.Tip;


public class ArtikalDto {

    private String naziv;

    private double cena;

    private Tip tip;

    private double kolicina;

    private String opis;

    public ArtikalDto() {
    }

    public ArtikalDto(String naziv, double cena, Tip tip, double kolicina, String opis) {
        this.naziv = naziv;
        this.cena = cena;
        this.tip = tip;
        this.kolicina = kolicina;
        this.opis = opis;
    }

    public ArtikalDto(Artikal artikal){
        this.naziv = artikal.getNaziv();
        this.cena = artikal.getCena();
        this.tip = artikal.getTip();
        this.kolicina = artikal.getKolicina();
        this.opis = artikal.getOpis();
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public Tip getTip() {
        return tip;
    }

    public void setTip(Tip tip) {
        this.tip = tip;
    }

    public double getKolicina() {
        return kolicina;
    }

    public void setKolicina(double kolicina) {
        this.kolicina = kolicina;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

}
