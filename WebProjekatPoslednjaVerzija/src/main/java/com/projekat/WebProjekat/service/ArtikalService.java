package main.java.com.projekat.WebProjekat.service;

import main.java.com.projekat.WebProjekat.dto.ArtikalDto.ArtikalDto;
import main.java.com.projekat.WebProjekat.entity.Artikal;
import main.java.com.projekat.WebProjekat.entity.Menadzer;
import main.java.com.projekat.WebProjekat.repository.ArtikalRepository;
import main.java.com.projekat.WebProjekat.repository.RestoranRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArtikalService {

    @Autowired
    private ArtikalRepository artikalRepository;

    @Autowired
    private RestoranRepository restoranRepository;

    public Artikal save(Artikal artikal) { return artikalRepository.save(artikal); }

    public Artikal findOne(Long id){
        Optional<Artikal> pronadjenArtikal = artikalRepository.findById(id);
        if (pronadjenArtikal.isPresent())
            return pronadjenArtikal.get();
        return null;
    }

    public List<Artikal> findAll() { return artikalRepository.findAll(); }

    public Artikal dodajArtikal(ArtikalDto dto, Menadzer menadzer, String fileName){
        Artikal artikal = new Artikal(dto.getNaziv(), dto.getCena(), dto.getTip(), dto.getKolicina(), dto.getOpis(), menadzer.getRestoran());
        artikal.setPhotos(fileName);

        this.save(artikal);

        menadzer.getRestoran().getArtikliUPonudi().add(artikal);

        restoranRepository.save(menadzer.getRestoran());

        return artikal;
    }

    public void delete(Artikal artikal) {
        artikalRepository.delete(artikal);
    }
}
