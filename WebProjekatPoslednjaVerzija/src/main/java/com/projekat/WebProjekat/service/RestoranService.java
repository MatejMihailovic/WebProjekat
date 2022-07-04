package main.java.com.projekat.WebProjekat.service;

import main.java.com.projekat.WebProjekat.dto.RestoranDto.RestoranDto;
import main.java.com.projekat.WebProjekat.entity.*;
import main.java.com.projekat.WebProjekat.repository.LokacijaRepository;
import main.java.com.projekat.WebProjekat.repository.PorudzbinaRepository;
import main.java.com.projekat.WebProjekat.repository.RestoranRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RestoranService {
    @Autowired
    private RestoranRepository restoranRepository;;

    @Autowired
    private PorudzbinaRepository porudzbinaRepository;;

    public Restoran save(Restoran restoran) { return restoranRepository.save(restoran); }

    public Restoran findOne(Long id){
        Optional<Restoran> pronadjenRestoran = restoranRepository.findById(id);
        if (pronadjenRestoran.isPresent())
            return pronadjenRestoran.get();
        return null;
    }

    public List<Restoran> findAll() { return restoranRepository.findAll(); }

    public List<Restoran> findAll(Specification<Restoran> spec) { return restoranRepository.findAll(spec); }

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

    public void deleteRestoran(Long id){
        Restoran restoran = this.findOne(id);

        List<Porudzbina> porudzbine = this.porudzbinaRepository.findAll();

        

        restoranRepository.delete(restoran);

    }

}
