package main.java.com.projekat.WebProjekat.service;

import main.java.com.projekat.WebProjekat.entity.*;
import main.java.com.projekat.WebProjekat.repository.KorisnikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RegisterService {
    @Autowired
    KorisnikRepository kupacRepository;

    public Korisnik register(Korisnik korisnik, Uloga uloga){
        if(postojiLiKorisnik(korisnik.getKorisnickoIme())){
            return null;
        }

        if(uloga.equals(Uloga.Kupac)) {
            korisnik.setUloga(uloga);
            return kupacRepository.save(korisnik);
        }

        return null;
    }

    public boolean postojiLiKorisnik(String korisnickoIme){
        for(Korisnik k : kupacRepository.findAll()){
            if(k.getKorisnickoIme().equals(korisnickoIme)){
                return true;
            }
        }
        return false;
    }
}
