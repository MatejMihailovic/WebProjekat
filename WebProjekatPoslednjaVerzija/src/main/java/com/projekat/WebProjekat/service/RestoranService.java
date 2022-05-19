package main.java.com.projekat.WebProjekat.service;

import main.java.com.projekat.WebProjekat.dto.ArtikalDto;
import main.java.com.projekat.WebProjekat.dto.NoviRestoranDto;
import main.java.com.projekat.WebProjekat.dto.RestoranDto;
import main.java.com.projekat.WebProjekat.entity.*;
import main.java.com.projekat.WebProjekat.repository.ArtikalRepository;
import main.java.com.projekat.WebProjekat.repository.RestoranRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RestoranService {
    @Autowired
    private RestoranRepository restoranRepository;

    public Restoran save(Restoran restoran) { return restoranRepository.save(restoran); }

    public Restoran findOne(Long id){
        Optional<Restoran> pronadjenRestoran = restoranRepository.findById(id);
        if (pronadjenRestoran.isPresent())
            return pronadjenRestoran.get();
        return null;
    }

    public List<Restoran> findAll() { return restoranRepository.findAll(); }

    public Restoran findOneByLokacija(Lokacija lokacija){
        Optional<Restoran> pronadjenRestoran = restoranRepository.findByLokacija(lokacija);

        if (pronadjenRestoran.isPresent())
            return pronadjenRestoran.get();
        return null;
    }
    public List<RestoranDto> findByTipRestorana(String tipRestorana){
        List<Restoran> restorani = this.findAll();

        List<RestoranDto> restoranidto = new ArrayList<>();
        for(Restoran r : restorani){
            if(r.getTipRestorana().equals(tipRestorana)){
                restoranidto.add(new RestoranDto(r));
            }
        }

        return restoranidto;
    }
    public Restoran findOneByNaziv(String naziv){
        Optional<Restoran> pronadjenRestoran = restoranRepository.findByNaziv(naziv);

        if (pronadjenRestoran.isPresent())
            return pronadjenRestoran.get();
        return null;
    }

    public Restoran dodajArtikal(ArtikalDto dto, Menadzer menadzer){
        Artikal artikal = new Artikal(dto.getNaziv(), dto.getCena(), dto.getTip(), dto.getKolicina(), dto.getOpis(), menadzer.getRestoran());

        menadzer.getRestoran().getArtikliUPonudi().add(artikal);

        return this.save(menadzer.getRestoran());
    }

    public boolean obrisiArtikalURestoranu(Long id, Restoran restoran){
        restoran.getArtikliUPonudi().remove(id);
        if(!restoran.getArtikliUPonudi().contains(id)) {
            restoranRepository.save(restoran);
            return true;
        }
        return false;
    }

    public void kreirajRestoran(NoviRestoranDto dto){
        Restoran restoran = new Restoran(dto.getNaziv(), dto.getTipRestorana(), dto.getLokacija());

    }
}
