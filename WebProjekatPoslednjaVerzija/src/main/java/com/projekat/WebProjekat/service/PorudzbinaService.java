package main.java.com.projekat.WebProjekat.service;

import main.java.com.projekat.WebProjekat.dto.PorudzbinaDto;
import main.java.com.projekat.WebProjekat.entity.*;
import main.java.com.projekat.WebProjekat.repository.DostavljacRepository;
import main.java.com.projekat.WebProjekat.repository.KupacRepository;
import main.java.com.projekat.WebProjekat.repository.PorudzbinaRepository;
import main.java.com.projekat.WebProjekat.repository.RestoranRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.*;

@Service
public class PorudzbinaService {
    @Autowired
    private PorudzbinaRepository porudzbinaRepository;

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
}
