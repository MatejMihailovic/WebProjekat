package main.java.com.projekat.WebProjekat.dto.PorudzbinaDto;

import main.java.com.projekat.WebProjekat.dto.ArtikalDto.ArtikalKorpaDto;
import main.java.com.projekat.WebProjekat.entity.Artikal;
import main.java.com.projekat.WebProjekat.entity.Porudzbina;

import java.util.ArrayList;
import java.util.List;

public class PorudzbinaKorpaDto {
    List<ArtikalKorpaDto> artikalKorpaDto = new ArrayList<>();

    double ukupnaCena;

    public PorudzbinaKorpaDto(List<ArtikalKorpaDto> artikalKorpaDto, double ukupnaCena) {
        this.artikalKorpaDto = artikalKorpaDto;
        this.ukupnaCena = ukupnaCena;
    }

    public PorudzbinaKorpaDto(Porudzbina porudzbina){
        for(Artikal artikal : porudzbina.getPoruceniArtikli()){
            this.artikalKorpaDto.add(new ArtikalKorpaDto(artikal));
        }
        this.ukupnaCena = porudzbina.getCena();
    }

    public List<ArtikalKorpaDto> getArtikalKorpaDto() {
        return artikalKorpaDto;
    }

    public void setArtikalKorpaDto(List<ArtikalKorpaDto> artikalKorpaDto) {
        this.artikalKorpaDto = artikalKorpaDto;
    }

    public double getUkupnaCena() {
        return ukupnaCena;
    }

    public void setUkupnaCena(double ukupnaCena) {
        this.ukupnaCena = ukupnaCena;
    }
}
