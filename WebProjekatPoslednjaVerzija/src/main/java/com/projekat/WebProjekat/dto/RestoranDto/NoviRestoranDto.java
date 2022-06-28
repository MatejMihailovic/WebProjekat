package main.java.com.projekat.WebProjekat.dto.RestoranDto;

import main.java.com.projekat.WebProjekat.entity.Lokacija;

public class NoviRestoranDto {
    private String naziv;

    private String tipRestorana;

    //private Lokacija lokacija;
    private double geografskaDuzina;

    private double geografskaSirina;

    private String adresa;

    public NoviRestoranDto(String naziv, String tipRestorana, double geografskaDuzina, double geografskaSirina, String adresa) {
        this.naziv = naziv;
        this.tipRestorana = tipRestorana;
        this.geografskaDuzina = geografskaDuzina;
        this.geografskaSirina = geografskaSirina;
        this.adresa = adresa;
    }

    public double getGeografskaDuzina() {
        return geografskaDuzina;
    }

    public void setGeografskaDuzina(double geografskaDuzina) {
        this.geografskaDuzina = geografskaDuzina;
    }

    public double getGeografskaSirina() {
        return geografskaSirina;
    }

    public void setGeografskaSirina(double geografskaSirina) {
        this.geografskaSirina = geografskaSirina;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
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

}
