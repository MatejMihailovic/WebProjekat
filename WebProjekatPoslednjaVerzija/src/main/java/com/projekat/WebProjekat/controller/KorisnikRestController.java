package main.java.com.projekat.WebProjekat.controller;

import main.java.com.projekat.WebProjekat.dto.KorisnikDto;
import main.java.com.projekat.WebProjekat.dto.LoginDto;
import main.java.com.projekat.WebProjekat.dto.RegisterDto;
import main.java.com.projekat.WebProjekat.entity.Korisnik;
import main.java.com.projekat.WebProjekat.service.KorisnikService;
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

    @PostMapping("api/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
        if(registerDto.getKorisnickoIme().isEmpty() || registerDto.getLozinka().isEmpty() || registerDto.getIme().isEmpty() || registerDto.getPrezime().isEmpty()
        || registerDto.getIme().length() > 20 || registerDto.getPrezime().length() > 20 || registerDto.getKorisnickoIme().length() > 20){
            return new ResponseEntity("Invalid register information!", HttpStatus.BAD_REQUEST);
        }

        Korisnik registeredKorisnik = korisnikService.register(registerDto.getKorisnickoIme(),registerDto.getLozinka(), registerDto.getIme(), registerDto.getPrezime(), registerDto.getPol(), registerDto.getDatumRodjenja());

        if(registeredKorisnik == null){
            return new ResponseEntity("That username already exists!", HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok("Succesfully registered!");

    }

    @PostMapping("api/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto, HttpSession session){

        if(loginDto.getKorisnickoIme().isEmpty() || loginDto.getLozinka().isEmpty())
            return new ResponseEntity("Invalid login data", HttpStatus.BAD_REQUEST);

        Korisnik ulogovanKorisnik = korisnikService.login(loginDto.getKorisnickoIme(), loginDto.getLozinka());
        if (ulogovanKorisnik == null)
            return new ResponseEntity<>("User doesn't exist!", HttpStatus.NOT_FOUND);

        session.setAttribute("user", ulogovanKorisnik);
        return ResponseEntity.ok("Succesfully login!");
    }

    @PostMapping("api/logout")
    public ResponseEntity Logout(HttpSession session){
        Korisnik loggedKorisnik = (Korisnik) session.getAttribute("korisnik");

        if (loggedKorisnik == null)
            return new ResponseEntity("Forbidden", HttpStatus.FORBIDDEN);

        session.invalidate();
        return new ResponseEntity("Successfully logged out", HttpStatus.OK);
    }

    @GetMapping("/api/korisnici")
    public ResponseEntity<List<KorisnikDto>> getKorisnici(){
        List<Korisnik> korisnici = this.korisnikService.findAll();

        List<KorisnikDto> dtos = new ArrayList<>();
        for(Korisnik k : korisnici){
            KorisnikDto dto = new KorisnikDto(k);
            dtos.add(dto);
        }
        return ResponseEntity.ok(dtos);

    }

    @GetMapping("/api/korisnici/{id}")
    public Korisnik getKorisnik(@PathVariable(name = "id") Long id, HttpSession session){

        Korisnik korisnik = (Korisnik) session.getAttribute("user");
        KorisnikDto dto = new KorisnikDto(korisnik);
        System.out.println(dto);
        session.invalidate();
        return korisnikService.findOne(id);
    }



    @PutMapping("/api/korisnici/izmeniKIme")
    public ResponseEntity izmeniKIme(HttpSession session,@RequestBody String kIme){
        Korisnik korisnik = (Korisnik) session.getAttribute("user");
        korisnik.setKorisnickoIme(kIme);

        return ResponseEntity.ok("Uspesna promena podataka");
    }

    @PutMapping("/api/korisnici/izmeniLozinku")
    public ResponseEntity izmeniLozinku(HttpSession session,@RequestBody String lozinka){
        Korisnik korisnik = (Korisnik) session.getAttribute("user");
        korisnik.setKorisnickoIme(lozinka);

        return ResponseEntity.ok("Uspesna promena podataka");
    }








}
