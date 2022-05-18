package main.java.com.projekat.WebProjekat.service;

import main.java.com.projekat.WebProjekat.dto.LoginDto;
import main.java.com.projekat.WebProjekat.dto.MenadzerDto;
import main.java.com.projekat.WebProjekat.entity.Korisnik;
import main.java.com.projekat.WebProjekat.entity.Menadzer;
import main.java.com.projekat.WebProjekat.entity.Pol;
import main.java.com.projekat.WebProjekat.entity.Uloga;
import main.java.com.projekat.WebProjekat.repository.KorisnikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class KorisnikService{
    @Autowired
    private KorisnikRepository korisnikRepository;

    public Korisnik save(Korisnik korisnik) { return korisnikRepository.save(korisnik); }

    public Korisnik findOne(Long id){
        Optional<Korisnik> pronadjenKorisnik = korisnikRepository.findById(id);
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
