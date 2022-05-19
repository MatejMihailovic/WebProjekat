package main.java.com.projekat.WebProjekat.service;

import main.java.com.projekat.WebProjekat.dto.PorudzbinaDto;
import main.java.com.projekat.WebProjekat.entity.Artikal;
import main.java.com.projekat.WebProjekat.entity.Kupac;
import main.java.com.projekat.WebProjekat.entity.Porudzbina;
import main.java.com.projekat.WebProjekat.entity.Restoran;
import main.java.com.projekat.WebProjekat.repository.KupacRepository;
import main.java.com.projekat.WebProjekat.repository.PorudzbinaRepository;
import main.java.com.projekat.WebProjekat.repository.RestoranRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class PorudzbinaService {
    @Autowired
    private PorudzbinaRepository porudzbinaRepository;

    @Autowired
    private KupacRepository kupacRepository;

    @Autowired
    private RestoranRepository restoranRepository;

    public void savePorudzbina(PorudzbinaDto porudzbina, String korisnickoIme) throws Exception{
        Kupac kupac = kupacRepository.findByKorisnickoIme(korisnickoIme);
        Optional<Restoran> restoran = restoranRepository.findById(porudzbina.getRestoran().getId());


        Porudzbina novaPorudzbina = new Porudzbina();
        Set<Artikal> artikli = new HashSet<>();

        for(Artikal art : porudzbina.getPoruceniArtikli()){
            Artikal trazeniArtikal = null;
            for(Artikal a : restoran.get().getArtikliUPonudi()){
                if(a.getId().equals(art.getId())){
                    trazeniArtikal = a;
                    break;
                }
            }
        }

        novaPorudzbina.setPoruceniArtikli(artikli);
        porudzbinaRepository.save(novaPorudzbina);

    }
}
