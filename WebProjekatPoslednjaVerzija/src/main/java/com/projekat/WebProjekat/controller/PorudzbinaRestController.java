package main.java.com.projekat.WebProjekat.controller;

import main.java.com.projekat.WebProjekat.dto.PorudzbinaDto;
import main.java.com.projekat.WebProjekat.entity.Kupac;
import main.java.com.projekat.WebProjekat.entity.Porudzbina;
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
    public ResponseEntity getPorudzbine(HttpSession session){
        if(!sessionService.validateSession(session))
            return new ResponseEntity(HttpStatus.FORBIDDEN);

        Kupac trenutniKupac = kupacRepository.findByKorisnickoIme(sessionService.getUsername(session));
        List<Porudzbina> svePorudzbine = this.porudzbinaRepository.findAll();
        List<PorudzbinaDto> trenutniKupacPorudzbine = new ArrayList<>();
        for(Porudzbina p : svePorudzbine){
            if(p.getKorisnickoIme().equals(trenutniKupac.getKorisnickoIme())){
                PorudzbinaDto pDto = new PorudzbinaDto(p);
                trenutniKupacPorudzbine.add(pDto);
            }
        }

        if(trenutniKupacPorudzbine.isEmpty())
            return new ResponseEntity("Nemate nijednu porudzbinu.",HttpStatus.OK);
        else
            return new ResponseEntity(trenutniKupacPorudzbine,HttpStatus.OK);
    }

}
