package main.java.com.projekat.WebProjekat.service;

import main.java.com.projekat.WebProjekat.entity.Komentar;
import main.java.com.projekat.WebProjekat.entity.Restoran;
import main.java.com.projekat.WebProjekat.repository.KomentarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class KomentarService {
    @Autowired
    private KomentarRepository komentarRepository;

    public Komentar save(Komentar komentar) { return komentarRepository.save(komentar); }

    public Komentar findOne(Long id){
        Optional<Komentar> pronadjenKomentar = komentarRepository.findById(id);
        if (pronadjenKomentar.isPresent())
            return pronadjenKomentar.get();
        return null;
    }

    public List<Komentar> findAll() { return komentarRepository.findAll(); }

    public List<Komentar> findAll(Restoran restoran) {
        List<Komentar> listaKomentara = new ArrayList<>();

        for(Komentar k : this.findAll()){
           if(k.getRestoranKomentar() == restoran){
               listaKomentara.add(k);
         }
      }
        return listaKomentara;
    }

    public double prosecnaOcena(List<Komentar> listaKomentara){
        double rezultat = 0;
        for(Komentar k : listaKomentara){
            rezultat += k.getOcena();
        }

        return rezultat / listaKomentara.size();
    }
}
