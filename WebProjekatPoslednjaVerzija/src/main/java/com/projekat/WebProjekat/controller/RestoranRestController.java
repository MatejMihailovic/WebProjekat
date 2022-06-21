package main.java.com.projekat.WebProjekat.controller;

import main.java.com.projekat.WebProjekat.dto.RestoranDto.KreirajRestoranDto;
import main.java.com.projekat.WebProjekat.dto.RestoranDto.RestoranDto;
import main.java.com.projekat.WebProjekat.dto.RestoranDto.RestoranPrikazDto;
import main.java.com.projekat.WebProjekat.entity.Komentar;
import main.java.com.projekat.WebProjekat.entity.Menadzer;
import main.java.com.projekat.WebProjekat.entity.Restoran;
import main.java.com.projekat.WebProjekat.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
public class RestoranRestController {

    @Autowired
    private RestoranService restoranService;

    @Autowired
    private KorisnikService korisnikService;

    @Autowired
    private KomentarService komentarService;

    @Autowired
    private SessionService sessionService;

    @GetMapping("api/restorani")
    public ResponseEntity<List<RestoranDto>> getRestorani(){
        List<Restoran> restorani = this.restoranService.findAll();

        List<RestoranDto> dtos = new ArrayList<>();
        for(Restoran r : restorani){
            RestoranDto dto = new RestoranDto(r);
            dtos.add(dto);
        }
        return ResponseEntity.ok(dtos);

    }
    @PostMapping("api/restorani/kreiraj")
    public ResponseEntity kreirajRestoran(@RequestBody KreirajRestoranDto dto, HttpSession session){
        Boolean provera = sessionService.validateRole(session, "Admin");

        if(!provera){
            return new ResponseEntity("Nemate potrebne privilegije!",HttpStatus.BAD_REQUEST);
        }

        if(dto.getNaziv().isEmpty() || dto.getTipRestorana().isEmpty() || dto.getLokacija() == null || dto.getkImeMenadzera().isEmpty()){
            return new ResponseEntity("Ova polja ne smeju biti prazna!",HttpStatus.BAD_REQUEST);
        }
        Restoran novi = new Restoran(dto.getNaziv(), dto.getTipRestorana(), dto.getLokacija());
        Menadzer menadzer = (Menadzer) korisnikService.findOne(dto.getkImeMenadzera());

        menadzer.setRestoran(novi);
        restoranService.save(novi);
        korisnikService.save(menadzer, menadzer.getUloga());

        return ResponseEntity.ok("Uspesno kreiran restoran.");
    }

    @GetMapping("/api/restorani/pretrazi")
    public ResponseEntity<List<RestoranDto>> getRestoran(@RequestBody RestoranDto dto){

        if(dto.getNaziv().isEmpty() && dto.getLokacija() == null && dto.getTipRestorana().isEmpty()){
            return new ResponseEntity("Invalid search information!", HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(restoranService.getRestorani(dto));
    }

    @GetMapping("/api/restorani/pretrazi-izbor/{id}")
    public ResponseEntity<RestoranPrikazDto> izborRestorana(@PathVariable(name = "id") Long id){

        Restoran restoran = restoranService.findOne(id);

        List<Komentar> listaKomentara = komentarService.findAll(restoran);

        RestoranPrikazDto prikazDto = new RestoranPrikazDto(restoran);

        prikazDto.setKomentari(listaKomentara);

        prikazDto.setProsek(komentarService.prosecnaOcena(listaKomentara));

        return ResponseEntity.ok(prikazDto);
    }
}
