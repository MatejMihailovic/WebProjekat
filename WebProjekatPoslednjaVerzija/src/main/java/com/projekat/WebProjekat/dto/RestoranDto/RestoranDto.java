package main.java.com.projekat.WebProjekat.dto.RestoranDto;

import main.java.com.projekat.WebProjekat.entity.Lokacija;
import main.java.com.projekat.WebProjekat.entity.Restoran;

public class RestoranDto {
    private Long id;

    private String naziv;

    private String tipRestorana;

   private Lokacija lokacija;

    public RestoranDto(Long id, String naziv, String tipRestorana, Lokacija lokacija) {
        this.id = id;
        this.naziv = naziv;
        this.tipRestorana = tipRestorana;
       this.lokacija = lokacija;
    }

    public RestoranDto(Restoran restoran) {
        this.id = restoran.getId();
        this.naziv = restoran.getNaziv();
        this.tipRestorana = restoran.getTipRestorana();
        this.lokacija = restoran.getLokacija();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getTipRestorana() {
        return tipRestorana;
    }

    public void setTipRestorana(String tipRestorana) {
        this.tipRestorana = tipRestorana;
    }

    public Lokacija getLokacija() {
       return lokacija;
    }

    public void setLokacija(Lokacija lokacija) {
        this.lokacija = lokacija;
    }
}
