package main.java.com.projekat.WebProjekat.controller;

import main.java.com.projekat.WebProjekat.dao.IUserDAO;
import main.java.com.projekat.WebProjekat.dto.*;
import main.java.com.projekat.WebProjekat.entity.Korisnik;
import main.java.com.projekat.WebProjekat.entity.Menadzer;
import main.java.com.projekat.WebProjekat.entity.Restoran;
import main.java.com.projekat.WebProjekat.entity.Uloga;
import main.java.com.projekat.WebProjekat.service.KorisnikService;
import main.java.com.projekat.WebProjekat.service.SessionService;
import main.java.com.projekat.WebProjekat.util.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
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

    @GetMapping(
            value = "/api/svi-korisnici",
            produces = MediaType.APPLICATION_JSON_VALUE)
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
    @RequestMapping(method = RequestMethod.GET, value = "api/korisnici")
    @ResponseBody
    public ResponseEntity<List<KorisnikDto>> search(@RequestParam(value = "search") String search, HttpSession session) {
        Boolean provera = sessionService.validateRole(session, "Admin");

        if(!provera){
            return new ResponseEntity(null,HttpStatus.BAD_REQUEST);
        }
        System.out.println(search);
        List<SearchCriteria> params = new ArrayList<SearchCriteria>();
        if (search != null) {
            Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
            Matcher matcher = pattern.matcher(search + ",");
            while (matcher.find()) {
                params.add(new SearchCriteria(matcher.group(1), matcher.group(2), matcher.group(3)));
            }
        }
        return new ResponseEntity<>(service.search(params), HttpStatus.OK);
    }

    @GetMapping(
            value ="/api/korisnici/ulogovanKorisnik",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<KorisnikDto> getUlogovan(HttpSession session){
        Korisnik ulogovanKorisnik = (Korisnik) session.getAttribute("user");

        if(ulogovanKorisnik == null){
            return new ResponseEntity("invalid", HttpStatus.FORBIDDEN);
        }

        KorisnikDto korisnikdto = new KorisnikDto(ulogovanKorisnik);

        return ResponseEntity.ok(korisnikdto);
    }

    @GetMapping(value = "/api/korisnici/role",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Uloga> getRole(HttpSession session){
        Korisnik ulogovanKorisnik = (Korisnik) session.getAttribute("user");

        if(ulogovanKorisnik == null){
            return new ResponseEntity("invalid", HttpStatus.FORBIDDEN);
        }

        return new ResponseEntity<>(ulogovanKorisnik.getUloga(), HttpStatus.OK);
    }
    @PutMapping(
            value = "/api/korisnici/ulogovanKorisnik/update",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateProfile(@RequestBody UpdateDto updateDto, HttpSession session) throws ParseException {
        Korisnik korisnik = (Korisnik) session.getAttribute("user");

        if(!updateDto.getKorisnickoIme().isEmpty())
            korisnik.setKorisnickoIme(updateDto.getKorisnickoIme());
        if(!updateDto.getLozinka().isEmpty())
            korisnik.setLozinka(updateDto.getLozinka());
        if(!updateDto.getIme().isEmpty())
            korisnik.setIme(updateDto.getIme());
        if(!updateDto.getPrezime().isEmpty())
            korisnik.setPrezime(updateDto.getPrezime());

        System.out.println(korisnik);
        return new ResponseEntity(korisnikService.save(korisnik, korisnik.getUloga()), HttpStatus.OK);
    }

}
