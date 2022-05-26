package main.java.com.projekat.WebProjekat.service;

import main.java.com.projekat.WebProjekat.entity.*;
import main.java.com.projekat.WebProjekat.repository.DostavljacRepository;
import main.java.com.projekat.WebProjekat.repository.KorisnikRepository;
import main.java.com.projekat.WebProjekat.repository.KupacRepository;
import main.java.com.projekat.WebProjekat.repository.MenadzerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KorisnikService{
    @Autowired
    private KorisnikRepository korisnikRepository;

    @Autowired
    private MenadzerRepository menadzerRepository;

    @Autowired
    private KupacRepository kupacRepository;

    @Autowired
    private DostavljacRepository dostavljacRepository;

    public Korisnik save(Korisnik korisnik, Uloga uloga) {
        if(uloga.equals(Uloga.Menadzer)) {
            menadzerRepository.save((Menadzer) korisnik);
        }else if(uloga.equals(Uloga.Kupac)){
            kupacRepository.save((Kupac) korisnik);
        }else if (uloga.equals(Uloga.Dostavljac)){
            dostavljacRepository.save((Dostavljac) korisnik);
        }
        return korisnikRepository.save(korisnik);
    }
    public Korisnik findOne(Long id){
        Optional<Korisnik> pronadjenKorisnik = korisnikRepository.findById(id);
        if (pronadjenKorisnik.isPresent())
            return pronadjenKorisnik.get();
        return null;
    }

    public Korisnik findOne(String korisnickoIme){
        Optional<Korisnik> pronadjenKorisnik = korisnikRepository.getByKorisnickoIme(korisnickoIme);
        if (pronadjenKorisnik.isPresent())
            return pronadjenKorisnik.get();
        return null;
    }

    public List<Korisnik> findAll() { return korisnikRepository.findAll(); }

    public boolean containsKorisnickoIme(String korisnickoIme) {
        List<Korisnik> korisnici = this.findAll();

        for(Korisnik k : korisnici){
            if(k.getKorisnickoIme().equals(korisnickoIme)){
                return true;
            }
        }
        return false;
    }

}
