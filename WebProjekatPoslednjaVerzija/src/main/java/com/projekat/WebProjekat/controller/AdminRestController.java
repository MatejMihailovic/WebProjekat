package main.java.com.projekat.WebProjekat.controller;

import main.java.com.projekat.WebProjekat.dto.MenadzerDto.NoviMenadzerDto;
import main.java.com.projekat.WebProjekat.dto.NoviDostavljacDto;
import main.java.com.projekat.WebProjekat.dto.RestoranDto.KreirajRestoranDto;
import main.java.com.projekat.WebProjekat.entity.Dostavljac;
import main.java.com.projekat.WebProjekat.entity.Menadzer;
import main.java.com.projekat.WebProjekat.entity.Restoran;
import main.java.com.projekat.WebProjekat.service.KorisnikService;
import main.java.com.projekat.WebProjekat.service.RestoranService;
import main.java.com.projekat.WebProjekat.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/api/admin/create-dostavljac")
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

    @PostMapping("/api/admin/create-restoran")
    public ResponseEntity createRestoran(@RequestBody KreirajRestoranDto dto, HttpSession session){
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
}
