package main.java.com.projekat.WebProjekat.service;

import main.java.com.projekat.WebProjekat.entity.Artikal;
import main.java.com.projekat.WebProjekat.repository.ArtikalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArtikalService {

    @Autowired
    private ArtikalRepository artikalRepository;

    public Artikal save(Artikal artikal) { return artikalRepository.save(artikal); }

    public Artikal findOne(Long id){
        Optional<Artikal> pronadjenArtikal = artikalRepository.findById(id);
        if (pronadjenArtikal.isPresent())
            return pronadjenArtikal.get();
        return null;
    }

    public List<Artikal> findAll() { return artikalRepository.findAll(); }

    public boolean obrisiArtikal(Long id) {
        if(this.findOne(id) != null){
            artikalRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean updateArtikal(Long id) {
        if(this.findOne(id) != null){
            artikalRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
