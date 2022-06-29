package main.java.com.projekat.WebProjekat.controller;

import main.java.com.projekat.WebProjekat.dto.MenadzerDto.NoviMenadzerDto;
import main.java.com.projekat.WebProjekat.dto.NoviDostavljacDto;
import main.java.com.projekat.WebProjekat.dto.RestoranDto.NoviRestoranDto;
import main.java.com.projekat.WebProjekat.entity.Dostavljac;
import main.java.com.projekat.WebProjekat.entity.Lokacija;
import main.java.com.projekat.WebProjekat.entity.Menadzer;
import main.java.com.projekat.WebProjekat.entity.Restoran;
import main.java.com.projekat.WebProjekat.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class AdminRestController {

    @Autowired
    private KorisnikService korisnikService;

    @Autowired
    private RestoranService restoranService;

    @Autowired
    private KomentarService komentarService;

    @Autowired
    private LokacijaService lokacijaService;

    @Autowired
    private SessionService sessionService;

    @PostMapping(
            value = "/api/admin/create-menadzer",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<NoviMenadzerDto> createMenadzer(@RequestBody NoviMenadzerDto dto, HttpSession session) throws ParseException {
        Boolean provera = sessionService.validateRole(session, "Admin");

        if(!provera){
            return new ResponseEntity("Nemate potrebne privilegije!", HttpStatus.BAD_REQUEST);
        }

        if(korisnikService.containsKorisnickoIme(dto.getKorisnickoIme())){
            return new ResponseEntity("Uneto korisnicko ime vec postoji.",HttpStatus.BAD_REQUEST);
        }

        Date datum = new SimpleDateFormat("dd/MM/yyyy").parse(dto.getDatumRodjenja());
        Menadzer menadzer = new Menadzer(dto.getKorisnickoIme(), dto.getLozinka(), dto.getIme(), dto.getPrezime(), dto.getPol(), datum, restoranService.findOneByNaziv(dto.getNazivRestorana()));

        korisnikService.save(menadzer, menadzer.getUloga());

        NoviMenadzerDto noviDto = new NoviMenadzerDto();
        noviDto.setKorisnickoIme(menadzer.getKorisnickoIme());
        noviDto.setLozinka(menadzer.getLozinka());
        noviDto.setIme(menadzer.getIme());
        noviDto.setPrezime(menadzer.getPrezime());
        noviDto.setPol(menadzer.getPol());
        noviDto.setDatumRodjenja(dto.getDatumRodjenja());
        noviDto.setNazivRestorana(dto.getNazivRestorana());
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping(
            value = "/api/admin/create-dostavljac",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createDostavljac(@RequestBody NoviDostavljacDto dto, HttpSession session) throws ParseException {
        Boolean provera = sessionService.validateRole(session, "Admin");

        if(!provera){
            return new ResponseEntity("Nemate potrebne privilegije!",HttpStatus.BAD_REQUEST);
        }

        if(korisnikService.containsKorisnickoIme(dto.getKorisnickoIme())){
            return new ResponseEntity("Uneto korisnicko ime vec postoji.",HttpStatus.BAD_REQUEST);
        }

        Date datum = new SimpleDateFormat("dd/MM/yyyy").parse(dto.getDatumRodjenja());
        Dostavljac dostavljac = new Dostavljac(dto.getKorisnickoIme(), dto.getLozinka(), dto.getIme(), dto.getPrezime(), dto.getPol(), datum);

        korisnikService.save(dostavljac, dostavljac.getUloga());

        return new ResponseEntity("Uspesno dodat dostavljac!" , HttpStatus.OK);
    }

    @PostMapping(
            value = "/api/admin/create-restoran",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createRestoran(@RequestBody NoviRestoranDto dto, HttpSession session){
        Boolean provera = sessionService.validateRole(session, "Admin");

        if(!provera){
            return new ResponseEntity("Nemate potrebne privilegije!",HttpStatus.BAD_REQUEST);
        }

        if(dto.getNaziv().isEmpty() || dto.getTipRestorana().isEmpty() || dto.getAdresa().isEmpty()){
            return new ResponseEntity("Ova polja ne smeju biti prazna!",HttpStatus.BAD_REQUEST);
        }
        Lokacija lokacija = new Lokacija(dto.getGeografskaDuzina(), dto.getGeografskaSirina(), dto.getAdresa());
        lokacijaService.save(lokacija);

        Restoran novi = new Restoran(dto.getNaziv(), dto.getTipRestorana(), lokacija);
        restoranService.save(novi);

        return ResponseEntity.ok("Uspesno kreiran restoran.");
    }

    @DeleteMapping("/api/admin/delete-restoran/{id}")
    public ResponseEntity deleteRestoran(@PathVariable(name = "id") Long id, HttpSession session){
        Boolean provera = sessionService.validateRole(session, "Admin");

        if(!provera){
            return new ResponseEntity("Nemate potrebne privilegije!",HttpStatus.BAD_REQUEST);
        }

        korisnikService.deleteMenadzerRestoran(id);
        komentarService.deleteKomentarRestoran(id);
        restoranService.deleteRestoran(id);

        return ResponseEntity.ok("Uspesno obrisan restoran!");
    }
}
