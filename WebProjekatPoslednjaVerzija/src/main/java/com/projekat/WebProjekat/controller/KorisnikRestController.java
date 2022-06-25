package main.java.com.projekat.WebProjekat.controller;

import main.java.com.projekat.WebProjekat.dao.IUserDAO;
import main.java.com.projekat.WebProjekat.dto.*;
import main.java.com.projekat.WebProjekat.entity.Korisnik;
import main.java.com.projekat.WebProjekat.entity.Menadzer;
import main.java.com.projekat.WebProjekat.entity.Restoran;
import main.java.com.projekat.WebProjekat.service.KorisnikService;
import main.java.com.projekat.WebProjekat.service.SessionService;
import main.java.com.projekat.WebProjekat.util.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
public class KorisnikRestController {
    @Autowired
    private KorisnikService korisnikService;

    @Autowired
    private IUserDAO service;

    @Autowired
    private SessionService sessionService;

    @GetMapping("/api/svi-korisnici")
    public ResponseEntity<List<KorisnikDto>> getKorisnici(HttpSession session){
        /*Boolean provera = sessionService.validateRole(session, "Admin");

        if(!provera){
            return new ResponseEntity("Nemate potrebne privilegije!",HttpStatus.BAD_REQUEST);
        }*/
        List<Korisnik> korisnici = this.korisnikService.findAll();

        List<KorisnikDto> dtos = new ArrayList<>();
        for(Korisnik k : korisnici){
            KorisnikDto dto = new KorisnikDto(k);
            dtos.add(dto);
        }
        return ResponseEntity.ok(dtos);

    }
    @RequestMapping(method = RequestMethod.GET, value = "api/korisnici")
    @ResponseBody
    public List<KorisnikDto> search(@RequestParam(value = "search") String search) {
        /*Boolean provera = sessionService.validateRole(session, "Admin");

        if(!provera){
            return new ResponseEntity("Nemate potrebne privilegije!",HttpStatus.BAD_REQUEST);
        }*/
        List<SearchCriteria> params = new ArrayList<SearchCriteria>();
        if (search != null) {
            Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
            Matcher matcher = pattern.matcher(search + ",");
            while (matcher.find()) {
                params.add(new SearchCriteria(matcher.group(1), matcher.group(2), matcher.group(3)));
            }
        }
        return service.search(params);
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



}
