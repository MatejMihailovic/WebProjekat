package com.projekat.WebProjekat.service;

import com.projekat.WebProjekat.entity.Korisnik;
import com.projekat.WebProjekat.entity.Lokacija;
import com.projekat.WebProjekat.entity.Restoran;
import com.projekat.WebProjekat.repository.RestoranRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestoranService {
    @Autowired
    private RestoranRepository restoranRepository;

    public List<Restoran> findAll() { return restoranRepository.findAll(); }

    public Restoran findOneByLokacija(Lokacija lokacija){
        Optional<Restoran> pronadjenRestoran = restoranRepository.findByLokacija(lokacija);

        if (pronadjenRestoran.isPresent())
            return pronadjenRestoran.get();
        return null;
    }
    public Restoran findOneByTipRestorana(String tipRestorana){
        Optional<Restoran> pronadjenRestoran = restoranRepository.findByTipRestorana(tipRestorana);

        if (pronadjenRestoran.isPresent())
            return pronadjenRestoran.get();
        return null;
    }
    public Restoran findOneByNaziv(String naziv){
        Optional<Restoran> pronadjenRestoran = restoranRepository.findByNaziv(naziv);

        if (pronadjenRestoran.isPresent())
            return pronadjenRestoran.get();
        return null;
    }
}
