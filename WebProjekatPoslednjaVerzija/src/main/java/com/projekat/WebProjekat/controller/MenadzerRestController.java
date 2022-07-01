package main.java.com.projekat.WebProjekat.controller;

import main.java.com.projekat.WebProjekat.dto.PorudzbinaDto.PorudzbinaDto;
import main.java.com.projekat.WebProjekat.dto.RestoranDto.RestoranPrikazDto;
import main.java.com.projekat.WebProjekat.entity.Komentar;
import main.java.com.projekat.WebProjekat.entity.Menadzer;
import main.java.com.projekat.WebProjekat.entity.Porudzbina;
import main.java.com.projekat.WebProjekat.entity.Restoran;
import main.java.com.projekat.WebProjekat.service.KomentarService;
import main.java.com.projekat.WebProjekat.service.MenadzerService;
import main.java.com.projekat.WebProjekat.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
public class MenadzerRestController {

    @Autowired
    private MenadzerService menadzerService;

    @Autowired
    private KomentarService komentarService;

    @Autowired
    private SessionService sessionService;

    @GetMapping(value = "api/menadzer-restoran",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestoranPrikazDto> pregledRestorana(HttpSession session){
        Boolean provera = sessionService.validateRole(session, "Menadzer");

        if(!provera){
            return new ResponseEntity("Nemate potrebne privilegije!",HttpStatus.BAD_REQUEST);
        }

        Menadzer menadzer = (Menadzer) session.getAttribute("user");

        List<Komentar> listaKomentara = komentarService.findAll(menadzer.getRestoran());

        RestoranPrikazDto dto = new RestoranPrikazDto(menadzer.getRestoran(), listaKomentara);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("api/menadzer-porudzbine")
    public ResponseEntity<List<Porudzbina>> getPorudzbineMenadzer(HttpSession session){
        Boolean proveraSesije = sessionService.validateRole(session, "Menadzer");

        if(!proveraSesije){
            return new ResponseEntity("Nemate potrebne privilegije!", HttpStatus.BAD_REQUEST);
        }
        Menadzer menadzer = (Menadzer) session.getAttribute("user");

        List<Porudzbina> dtos = menadzerService.returnPorudzbine(menadzer);

        return ResponseEntity.ok(dtos);
    }
}
