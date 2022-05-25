package main.java.com.projekat.WebProjekat.controller;

import main.java.com.projekat.WebProjekat.dto.PorudzbinaDto;
import main.java.com.projekat.WebProjekat.entity.*;
import main.java.com.projekat.WebProjekat.repository.KupacRepository;
import main.java.com.projekat.WebProjekat.repository.PorudzbinaRepository;
import main.java.com.projekat.WebProjekat.service.ArtikalService;
import main.java.com.projekat.WebProjekat.service.PorudzbinaService;
import main.java.com.projekat.WebProjekat.service.SessionService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.*;

@RestController
public class PorudzbinaRestController {
    @Autowired
    private SessionService sessionService;

    @Autowired
    private ArtikalService artikalService;

    @Autowired PorudzbinaRepository porudzbinaRepository;

    @Autowired
    private PorudzbinaService porudzbinaService;

    @GetMapping("api/porudzbine-kupac")
    public ResponseEntity<List<PorudzbinaDto>> getPorudzbineKupac(HttpSession session){
        Boolean proveraSesije = sessionService.validateRole(session, "Kupac");

        if(!proveraSesije){
            return new ResponseEntity("Nemate potrebne privilegije!", HttpStatus.BAD_REQUEST);
        }

        Kupac kupac = (Kupac) session.getAttribute("user");
        List<Porudzbina> porudzbine = this.porudzbinaService.findAll();
        List<PorudzbinaDto> dtos = new ArrayList<>();

        for(Porudzbina p : porudzbine){
            if(p.getKorisnickoIme().equals(kupac.getKorisnickoIme())){
                PorudzbinaDto dto = new PorudzbinaDto(p);
                dtos.add(dto);
            }

        }

        return ResponseEntity.ok(dtos);

    }

    @GetMapping("api/porudzbine-menadzer")
    public ResponseEntity<List<PorudzbinaDto>> getPorudzbineMenadzer(HttpSession session){
        Boolean proveraSesije = sessionService.validateRole(session, "Menadzer");

        if(!proveraSesije){
            return new ResponseEntity("Nemate potrebne privilegije!", HttpStatus.BAD_REQUEST);
        }
        Menadzer menadzer = (Menadzer) session.getAttribute("user");
        Restoran restoran = menadzer.getRestoran();
        List<Porudzbina> porudzbine = this.porudzbinaService.findAll();
        List<PorudzbinaDto> dtos = new ArrayList<>();

        for(Porudzbina p : porudzbine){
            if(p.getRestoran().getId() == restoran.getId()){
                PorudzbinaDto dto = new PorudzbinaDto(p);
                dtos.add(dto);
            }
        }


        return ResponseEntity.ok(dtos);
    }


    @GetMapping("api/porudzbine-cekaDostavljaca")
    public ResponseEntity<List<PorudzbinaDto>> getPorudzbineCekaDostavljaca(HttpSession session){
        Boolean proveraSesije = sessionService.validateRole(session, "Dostavljac");

        if(!proveraSesije){
            return new ResponseEntity("Nemate potrebne privilegije!", HttpStatus.BAD_REQUEST);
        }


        List<Porudzbina> porudzbine = this.porudzbinaService.findAll();
        List<PorudzbinaDto> porudzbineCekaju = new ArrayList<>();

        for(Porudzbina p : porudzbine){
            if(p.getStatus().equals(Status.ceka)){
                PorudzbinaDto dto = new PorudzbinaDto(p);
                porudzbineCekaju.add(dto);
            }
        }

        return ResponseEntity.ok(porudzbineCekaju);

    }
    @GetMapping("api/porudzbine-dostavljac")
    public ResponseEntity<List<Porudzbina>> getPorudzbineDostavljac(HttpSession session){
        Boolean proveraSesije = sessionService.validateRole(session, "Dostavljac");

        if(!proveraSesije){
            return new ResponseEntity("Nemate potrebne privilegije!", HttpStatus.BAD_REQUEST);
        }
        Dostavljac dostavljac = (Dostavljac) session.getAttribute("user");

        List<Porudzbina> njegovePorudzbine = this.porudzbinaService.findAllForDostavljac(dostavljac);

        return ResponseEntity.ok(njegovePorudzbine);

    }

    @PostMapping("/api/porudzbine-dodajArtikal/{id}")
    public ResponseEntity dodajUKorpu(@PathVariable Long id, HttpSession session){
        Boolean proveraSesije = sessionService.validateRole(session,"Kupac");

        if(!proveraSesije){
            return  new ResponseEntity("Nemate potrebne privilegije!", HttpStatus.BAD_REQUEST);
        }

        Artikal artikal = artikalService.findOne(id);

        Restoran restoran = artikal.getRestoran();

        Kupac kupac = (Kupac) session.getAttribute("user");

        Porudzbina porudzbina = new Porudzbina();

        for(Porudzbina p : kupac.getPorudzbine()){
            if(p.getStatus().equals(Status.u_korpi)){
                porudzbina = p;
                break;
            }
        }

        porudzbina.setKorisnickoIme(kupac.getKorisnickoIme());
        porudzbina.setCena(artikal.getCena() + porudzbina.getCena());
        porudzbina.setDatum(new Date(101, Calendar.AUGUST, 21));
        porudzbina.setStatus(Status.u_korpi);
        porudzbina.getPoruceniArtikli().add(artikal);
        porudzbina.setRestoran(restoran);

        porudzbinaService.save(porudzbina);
        return new ResponseEntity("Dodat artikal", HttpStatus.OK);
    }
    @PostMapping("/api/porudzbine-ukloniArtikal/{id}")
    public ResponseEntity izbaciIzKorpe(@PathVariable Long id, HttpSession session){
        Boolean proveraSesije = sessionService.validateRole(session,"Kupac");

        if(!proveraSesije){
            return  new ResponseEntity("Nemate potrebne privilegije!", HttpStatus.BAD_REQUEST);
        }

        Kupac kupac = (Kupac) session.getAttribute("user");

        Porudzbina porudzbina = new Porudzbina();

        for(Porudzbina p : kupac.getPorudzbine()){
            if(p.getStatus().equals(Status.u_korpi)){
                porudzbina = p;
                break;
            }
        }

        porudzbina.getPoruceniArtikli().remove(artikalService.findOne(id));

        porudzbinaService.save(porudzbina);

        return new ResponseEntity("Uspesno obrisan artikal", HttpStatus.OK);
    }

}
