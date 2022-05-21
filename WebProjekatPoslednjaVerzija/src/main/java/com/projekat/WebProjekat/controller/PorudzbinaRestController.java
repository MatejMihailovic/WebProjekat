package main.java.com.projekat.WebProjekat.controller;

import main.java.com.projekat.WebProjekat.dto.PorudzbinaDto;
import main.java.com.projekat.WebProjekat.entity.*;
import main.java.com.projekat.WebProjekat.repository.KupacRepository;
import main.java.com.projekat.WebProjekat.repository.PorudzbinaRepository;
import main.java.com.projekat.WebProjekat.service.PorudzbinaService;
import main.java.com.projekat.WebProjekat.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
public class PorudzbinaRestController {
    @Autowired
    private SessionService sessionService;

    @Autowired
    private KupacRepository kupacRepository;

    @Autowired PorudzbinaRepository porudzbinaRepository;

    @Autowired
    private PorudzbinaService porudzbinaService;

    @GetMapping("api/porudzbine")
    public ResponseEntity<List<PorudzbinaDto>> getPorudzbine(HttpSession session){
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

    


}
