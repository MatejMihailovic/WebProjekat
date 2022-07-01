package main.java.com.projekat.WebProjekat.service;

import main.java.com.projekat.WebProjekat.dto.ArtikalDto.ArtikalDto;
import main.java.com.projekat.WebProjekat.entity.Artikal;
import main.java.com.projekat.WebProjekat.entity.Menadzer;
import main.java.com.projekat.WebProjekat.entity.Porudzbina;
import main.java.com.projekat.WebProjekat.entity.Restoran;
import main.java.com.projekat.WebProjekat.repository.ArtikalRepository;
import main.java.com.projekat.WebProjekat.repository.MenadzerRepository;
import main.java.com.projekat.WebProjekat.repository.PorudzbinaRepository;
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
    private PorudzbinaRepository porudzbinaRepository;


    @Autowired
    private RestoranRepository restoranRepository;

    @Autowired
    private MenadzerRepository menadzerRepository;

    public Artikal save(Artikal artikal) { return artikalRepository.save(artikal); }

    public Artikal findOne(Long id){
        Optional<Artikal> pronadjenArtikal = artikalRepository.findById(id);
        if (pronadjenArtikal.isPresent())
            return pronadjenArtikal.get();
        return null;
    }

    public List<Artikal> findAll() { return artikalRepository.findAll(); }

    public Restoran saveRestoran(Restoran restoran){
        return restoranRepository.save(restoran);
    }

    public Artikal addArtikal(ArtikalDto dto, Menadzer menadzer, String fileName){
        Artikal artikal = new Artikal(dto.getNaziv(), dto.getCena(), dto.getTip(), dto.getKolicina(), dto.getOpis(), menadzer.getRestoran());
        artikal.setPhotos(fileName);

        this.save(artikal);

        menadzer.getRestoran().getArtikliUPonudi().add(artikal);

        restoranRepository.save(menadzer.getRestoran());

        menadzerRepository.save(menadzer);

        return artikal;
    }

    public void update(Long id, ArtikalDto artikalDto, Menadzer menadzer){
        Artikal artikal = this.findOne(id);

        menadzer.getRestoran().getArtikliUPonudi().remove(artikal);

        if(!artikalDto.getNaziv().isEmpty()){
            artikal.setNaziv(artikalDto.getNaziv());
        }
        if(artikalDto.getCena() > 0){
            artikal.setCena(artikalDto.getCena());
        }
        if(artikalDto.getTip() != null){
            artikal.setTip(artikalDto.getTip());
        }
        if(artikalDto.getKolicina() > 0){
            artikal.setKolicina(artikalDto.getKolicina());
        }
        if(!artikalDto.getOpis().isEmpty()){
            artikal.setOpis(artikalDto.getOpis());
        }

        this.save(artikal);

        menadzer.getRestoran().getArtikliUPonudi().add(artikal);

        restoranRepository.save(menadzer.getRestoran());

        menadzerRepository.save(menadzer);
    }

    public void delete(Long id, Restoran restoran) {
        List<Porudzbina> porudzbine = porudzbinaRepository.findAll();

        for(Porudzbina p : porudzbine){
            if(p.getPoruceniArtikli().contains(id)){
                p.getPoruceniArtikli().remove(id);
            }
        }
        for (Artikal artikal : restoran.getArtikliUPonudi()) {
            if (artikal.getId().equals(id)) {
                restoran.getArtikliUPonudi().remove(artikal);
                artikal.setRestoran(null);
                artikalRepository.delete(artikal);
                this.saveRestoran(restoran);
            }
        }
    }

    public void deleteArtikalRestoran(Long id) {
        List<Artikal> listaArtikala = this.findAll();

        for(Artikal a : listaArtikala){
            if(a.getRestoran().equals(restoranRepository.findById(id))){
                a.setRestoran(null);
                artikalRepository.save(a);
            }
        }
    }
}
