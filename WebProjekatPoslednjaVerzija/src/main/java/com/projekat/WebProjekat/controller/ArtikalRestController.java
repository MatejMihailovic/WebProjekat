package main.java.com.projekat.WebProjekat.controller;

import main.java.com.projekat.WebProjekat.dto.ArtikalDto;
import main.java.com.projekat.WebProjekat.entity.Artikal;
import main.java.com.projekat.WebProjekat.entity.Menadzer;
import main.java.com.projekat.WebProjekat.entity.Restoran;
import main.java.com.projekat.WebProjekat.service.ArtikalService;
import main.java.com.projekat.WebProjekat.service.RestoranService;
import main.java.com.projekat.WebProjekat.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
public class ArtikalRestController {
    @Autowired
    private ArtikalService artikalService;

    @Autowired
    private RestoranService restoranService;

    @Autowired
    private SessionService sessionService;

    @PostMapping("/api/artikli/dodajArtikal")
    public ResponseEntity dodavanjeArtikla(@RequestBody ArtikalDto artikalDto, HttpSession session){
        Boolean proveraSesije = sessionService.validateRole(session, "Menadzer");

        if(!proveraSesije){
            return new ResponseEntity("Nemate potrebne privilegije!", HttpStatus.BAD_REQUEST);
        }

        if(artikalDto.getNaziv().isEmpty() || artikalDto.getCena() <= 0 || artikalDto.getTip() == null){
            return new ResponseEntity("Ova polja ne smeju biti prazna!", HttpStatus.BAD_REQUEST);
        }

        Menadzer menadzer = (Menadzer) session.getAttribute("user");

        restoranService.dodajArtikal(artikalDto, menadzer);

        return ResponseEntity.ok("Uspesno dodat artikal!");
    }

    @PutMapping("/api/artikli/updateArtikal/{id}")
    public ResponseEntity updateArtikal(@PathVariable(name = "id") Long id, @RequestBody ArtikalDto artikalDto, HttpSession session){
        Boolean proveraSesije = sessionService.validateRole(session, "Menadzer");

        if(!proveraSesije){
            return new ResponseEntity("Nemate potrebne privilegije!",HttpStatus.BAD_REQUEST);
        }

        Artikal artikal = artikalService.findOne(id);
        if(!artikalDto.getNaziv().isEmpty()){
            artikal.setNaziv(artikalDto.getNaziv());
        }
        if(artikalDto.getCena() > 0){
            artikal.setCena(artikalDto.getCena());
        }
        if(artikalDto.getTip() != null){
            artikal.setTip(artikalDto.getTip());
        }
        if(artikalDto.getKolicina() > 0){
            artikal.setKolicina(artikalDto.getKolicina());
        }
        if(!artikalDto.getOpis().isEmpty()){
            artikal.setOpis(artikalDto.getOpis());
        }
        artikalService.save(artikal);

        return ResponseEntity.ok("Uspesno promenjen artikal!");
    }

    @DeleteMapping("/api/artikli/obrisiArtikal/{id}")
    public ResponseEntity obrisiArtikal(@PathVariable(name = "id") Long id, HttpSession session){
        Boolean proveraSesije = sessionService.validateRole(session, "Menadzer");

        if(!proveraSesije){
            return new ResponseEntity("Nemate potrebne privilegije!",HttpStatus.BAD_REQUEST);
        }

        Menadzer menadzer = (Menadzer) session.getAttribute("user");
        Restoran restoran = menadzer.getRestoran();

        for(Artikal artikal : restoran.getArtikliUPonudi()){
            if (artikal.getId().equals(id)){
                restoran.getArtikliUPonudi().remove(artikal);
                artikal.setRestoran(null);
                restoranService.deleteArtikal(artikal);
                restoranService.save(restoran);
                return ResponseEntity.ok("Uspesno obrisan artikal!");
            }
        }
        return ResponseEntity.ok("Neuspesno obrisan artikal!");
    }
}
