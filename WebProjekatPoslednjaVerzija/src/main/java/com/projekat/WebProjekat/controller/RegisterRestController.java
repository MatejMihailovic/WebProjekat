package main.java.com.projekat.WebProjekat.controller;

import main.java.com.projekat.WebProjekat.dto.RegisterDto;
import main.java.com.projekat.WebProjekat.entity.Korisnik;
import main.java.com.projekat.WebProjekat.entity.Uloga;
import main.java.com.projekat.WebProjekat.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
public class RegisterRestController {
    @Autowired
    RegisterService registerService;

    @PostMapping("api/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) throws ParseException {
        if(registerDto.getKorisnickoIme().isEmpty() || registerDto.getLozinka().isEmpty() || registerDto.getIme().isEmpty() || registerDto.getPrezime().isEmpty()
                || registerDto.getIme().length() > 20 || registerDto.getPrezime().length() > 20 || registerDto.getKorisnickoIme().length() > 20){
            return new ResponseEntity("Invalid register information!", HttpStatus.BAD_REQUEST);
        }

        Korisnik registeredKorisnik = registerService.register(registerDto.ToKorisnik(), Uloga.Kupac);

        if(registeredKorisnik == null){
            return new ResponseEntity("That username already exists!", HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok("Succesfully registered!");

    }
}
