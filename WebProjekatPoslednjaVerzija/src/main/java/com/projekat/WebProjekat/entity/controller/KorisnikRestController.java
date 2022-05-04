package com.projekat.WebProjekat.controller;

import com.projekat.WebProjekat.dto.KorisnikDto;
import com.projekat.WebProjekat.dto.LoginDto;
import com.projekat.WebProjekat.dto.RegisterDto;
import com.projekat.WebProjekat.entity.Korisnik;
import com.projekat.WebProjekat.service.KorisnikService;
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
        if(registerDto.getKorisnickoIme().isEmpty() || registerDto.getLozinka().isEmpty() || registerDto.getIme().isEmpty() || registerDto.getPrezime().isEmpty()){
            return new ResponseEntity("Invalid register information!", HttpStatus.BAD_REQUEST);
        }

        Korisnik registeredKorisnik = korisnikService.register(registerDto.getKorisnickoIme(),registerDto.getLozinka(), registerDto.getIme(), registerDto.getPrezime(), registerDto.getPol(), registerDto.getDatumRodjenja());

        //Provera da li postoji vec takav korisnik
        return ResponseEntity.ok("Succesfully registered!");
    }

    @PostMapping("api/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto, HttpSession session){

        if(loginDto.getKorisnickoIme().isEmpty() || loginDto.getLozinka().isEmpty())
            return new ResponseEntity("Invalid login data", HttpStatus.BAD_REQUEST);

        Korisnik ulogovanKorisnik = korisnikService.login(loginDto.getKorisnickoIme(), loginDto.getLozinka());
        if (ulogovanKorisnik == null)
            return new ResponseEntity<>("Korisnik ne postoji!", HttpStatus.NOT_FOUND);

        session.setAttribute("korisnik", ulogovanKorisnik);
        return ResponseEntity.ok("Uspesno logovanje!");
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
}
