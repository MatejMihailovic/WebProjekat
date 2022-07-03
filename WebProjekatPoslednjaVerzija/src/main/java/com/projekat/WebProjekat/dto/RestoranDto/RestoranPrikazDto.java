package main.java.com.projekat.WebProjekat.dto.RestoranDto;

import main.java.com.projekat.WebProjekat.dto.ArtikalDto.ArtikalPrikazDto;
import main.java.com.projekat.WebProjekat.entity.Artikal;
import main.java.com.projekat.WebProjekat.entity.Komentar;
import main.java.com.projekat.WebProjekat.entity.Lokacija;
import main.java.com.projekat.WebProjekat.entity.Restoran;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RestoranPrikazDto {
    enum Status{
        RADI("Radi"),
        NE_RADI("Ne radi");

        private final String status;

        private Status(String status){
            this.status = status;
        }
        public String getStatus() {
            return status;
        }
    }
    private String naziv;

    private String tipRestorana;

    private Set<ArtikalPrikazDto> artikliUPonudi = new HashSet<>();

    private String lokacija;

    private Status status;

    private double prosek;

    private List<String> komentari = new ArrayList<>();

    public RestoranPrikazDto() {
    }

    public RestoranPrikazDto(Restoran restoran, List<Komentar> komentari) {
        this.naziv = restoran.getNaziv();
        this.tipRestorana = restoran.getTipRestorana();
        for(Artikal artikal : restoran.getArtikliUPonudi()){
            ArtikalPrikazDto prikazDto = new ArtikalPrikazDto(artikal);
            artikliUPonudi.add(prikazDto);
        }
        this.lokacija = restoran.getLokacija().getAdresa();
        this.status = Status.RADI;
        double prosek = 0;
        for(Komentar k : komentari){
            prosek += k.getOcena();
            this.komentari.add(k.getTekstKomentara());
        }
        this.prosek = prosek / komentari.size();
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

    public Set<ArtikalPrikazDto> getArtikliUPonudi() {
        return artikliUPonudi;
    }

    public void setArtikliUPonudi(Set<ArtikalPrikazDto> artikliUPonudi) {
        this.artikliUPonudi = artikliUPonudi;
    }

    public String getLokacija() {
        return lokacija;
    }

    public void setLokacija(String lokacija) {
        this.lokacija = lokacija;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public double getProsek() {
        return prosek;
    }

    public void setProsek(double prosek) {
        this.prosek = prosek;
    }

    public List<String> getKomentari() {
        return komentari;
    }

    public void setKomentari(List<String> komentari) {
        this.komentari = komentari;
    }
}
