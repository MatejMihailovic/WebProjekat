package main.java.com.projekat.WebProjekat.service;


import main.java.com.projekat.WebProjekat.entity.*;
import main.java.com.projekat.WebProjekat.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PorudzbinaService {
    @Autowired
    private PorudzbinaRepository porudzbinaRepository;

    @Autowired
    private ArtikalRepository artikalRepository;

    @Autowired
    private KorisnikService korisnikService;

    public Porudzbina save(Porudzbina porudzbina) { return porudzbinaRepository.save(porudzbina);}
    public List<Porudzbina> findAll() { return porudzbinaRepository.findAll(); }

    public List<Porudzbina> findAllForDostavljac(Dostavljac dostavljac){
        List<Porudzbina> porudzbine = new ArrayList<>();
        for(Porudzbina porudzbina : dostavljac.getPorudzbine()){
            porudzbine.add(porudzbina);
        }
        return porudzbine;
    }

    public Porudzbina findByStatus(Kupac kupac, Status status) {
        for(Porudzbina p : kupac.getPorudzbine()){
            if(p.getStatus() == status){
                return p;
            }
        }
        return new Porudzbina();
    }

    public void ukloniArtikal(Porudzbina porudzbina, Kupac kupac, Long id){
        Artikal artikal = new Artikal();
        for(Artikal a : porudzbina.getPoruceniArtikli()){
                if(a.getId().equals(id)){
                    artikal = a;
                    break;
                }
        }

        porudzbina.getPoruceniArtikli().remove(artikal);


        artikal.getPorudzbine().remove(porudzbina);

        artikalRepository.save(artikal);

        porudzbinaRepository.save(porudzbina);

        korisnikService.save(kupac, Uloga.Kupac);
    }
}
