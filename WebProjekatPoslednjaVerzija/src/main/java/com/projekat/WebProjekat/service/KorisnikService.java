package com.projekat.WebProjekat.service;

import com.projekat.WebProjekat.dto.LoginDto;
import com.projekat.WebProjekat.entity.Korisnik;
import com.projekat.WebProjekat.entity.Pol;
import com.projekat.WebProjekat.entity.Uloga;
import com.projekat.WebProjekat.repository.KorisnikRepository;
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
    public Korisnik register(String korisnickoIme, String lozinka, String ime, String prezime, Pol pol, Date datumRodjenja){
        Korisnik korisnik = new Korisnik(korisnickoIme, lozinka, ime, prezime, pol, datumRodjenja);
        korisnik.setUloga(Uloga.Kupac);

        if(this.containsKorisnickoIme(korisnickoIme)){
            return null;
        }
        return this.save(korisnik);
    }

    public Korisnik login(String korisnickoIme, String lozinka){
        Korisnik korisnik = korisnikRepository.getByKorisnickoIme(korisnickoIme);
        if(korisnik == null || !korisnik.getLozinka().equals(lozinka))
            return null;
        return korisnik;



    }

}
