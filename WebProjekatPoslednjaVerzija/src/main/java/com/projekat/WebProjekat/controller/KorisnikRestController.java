package main.java.com.projekat.WebProjekat.controller;

import main.java.com.projekat.WebProjekat.dto.*;
import main.java.com.projekat.WebProjekat.dto.MenadzerDto.NoviMenadzerDto;
import main.java.com.projekat.WebProjekat.entity.Dostavljac;
import main.java.com.projekat.WebProjekat.entity.Korisnik;
import main.java.com.projekat.WebProjekat.entity.Menadzer;
import main.java.com.projekat.WebProjekat.entity.Restoran;
import main.java.com.projekat.WebProjekat.service.KorisnikService;
import main.java.com.projekat.WebProjekat.service.RestoranService;
import main.java.com.projekat.WebProjekat.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
public class KorisnikRestController {
    @Autowired
    private KorisnikService korisnikService;

    @Autowired
    private RestoranService restoranService;

    @Autowired
    private SessionService sessionService;

    @GetMapping("/api/korisnici")
    public ResponseEntity<List<KorisnikDto>> getKorisnici(HttpSession session){
        Boolean provera = sessionService.validateRole(session, "Admin");

        if(!provera){
            return new ResponseEntity("Nemate potrebne privilegije!",HttpStatus.BAD_REQUEST);
        }
        List<Korisnik> korisnici = this.korisnikService.findAll();

        List<KorisnikDto> dtos = new ArrayList<>();
        for(Korisnik k : korisnici){
            KorisnikDto dto = new KorisnikDto(k);
            dtos.add(dto);
        }
        return ResponseEntity.ok(dtos);

    }

    @GetMapping("/api/korisnici/ulogovanKorisnik")
    public ResponseEntity getUlogovan(HttpSession session){
        Korisnik ulogovanKorisnik = (Korisnik) session.getAttribute("user");

        if(ulogovanKorisnik == null){
            return new ResponseEntity("invalid", HttpStatus.FORBIDDEN);
        }

        KorisnikDto korisnikdto = new KorisnikDto(ulogovanKorisnik);

        return ResponseEntity.ok(korisnikdto);
    }

    @PutMapping("/api/korisnici/ulogovanKorisnik/update")
    public ResponseEntity updateProfile(HttpSession session,@RequestBody UpdateDto updateDto){
        Korisnik korisnik = (Korisnik) session.getAttribute("user");

        if(updateDto.getKorisnickoIme() != null)
            korisnik.setKorisnickoIme(updateDto.getKorisnickoIme());
        if(updateDto.getLozinka() != null)
            korisnik.setLozinka(updateDto.getLozinka());
        if(updateDto.getIme() != null)
            korisnik.setIme(updateDto.getIme());
        if(updateDto.getPrezime() != null)
            korisnik.setPrezime(updateDto.getPrezime());
        if(updateDto.getPol() != null)
            korisnik.setPol(updateDto.getPol());
        if(updateDto.getDatumRodjenja() != null)
            korisnik.setDatumRodjenja(updateDto.getDatumRodjenja());

        return new ResponseEntity(korisnikService.save(korisnik, korisnik.getUloga()), HttpStatus.OK);
    }

    @GetMapping("/api/korisnici/menadzer")
    public ResponseEntity<Restoran> pregledRestorana(HttpSession session){
        Boolean provera = sessionService.validateRole(session, "Menadzer");

        if(!provera){
            return new ResponseEntity("Nemate potrebne privilegije!",HttpStatus.BAD_REQUEST);
        }

        Menadzer menadzer = (Menadzer) session.getAttribute("user");

        return ResponseEntity.ok(menadzer.getRestoran());
    }

    @PostMapping("/api/korisnici/dodaj/menadzer")
    public ResponseEntity dodajMenadzera(HttpSession session,@RequestBody NoviMenadzerDto dto){
        Boolean provera = sessionService.validateRole(session, "Admin");

        if(!provera){
            return new ResponseEntity("Nemate potrebne privilegije!",HttpStatus.BAD_REQUEST);
        }

        if(korisnikService.containsKorisnickoIme(dto.getKorisnickoIme())){
            return new ResponseEntity("Uneto korisnicko ime vec postoji.",HttpStatus.BAD_REQUEST);
        }

        Menadzer menadzer = new Menadzer(dto.getKorisnickoIme(), dto.getLozinka(), dto.getIme(), dto.getPrezime(), dto.getPol(), dto.getDatumRodjenja(), restoranService.findOneByNaziv(dto.getNazivRestorana()));
        return new ResponseEntity(korisnikService.save(menadzer, menadzer.getUloga()), HttpStatus.OK);
    }

    @PostMapping("/api/korisnici/dodaj/dostavljac")
    public ResponseEntity dodajDostavljaca(HttpSession session,@RequestBody NoviDostavljacDto dto){
        Boolean provera = sessionService.validateRole(session, "Admin");

        if(!provera){
            return new ResponseEntity("Nemate potrebne privilegije!",HttpStatus.BAD_REQUEST);
        }

        if(korisnikService.containsKorisnickoIme(dto.getKorisnickoIme())){
            return new ResponseEntity("Uneto korisnicko ime vec postoji.",HttpStatus.BAD_REQUEST);
        }

        Dostavljac dostavljac = new Dostavljac(dto.getKorisnickoIme(), dto.getLozinka(), dto.getIme(), dto.getPrezime(), dto.getPol(), dto.getDatumRodjenja());
        return new ResponseEntity(korisnikService.save(dostavljac, dostavljac.getUloga()), HttpStatus.OK);
    }


}
