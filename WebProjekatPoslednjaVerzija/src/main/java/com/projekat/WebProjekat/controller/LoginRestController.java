package main.java.com.projekat.WebProjekat.controller;

import main.java.com.projekat.WebProjekat.dto.LoginDto;
import main.java.com.projekat.WebProjekat.entity.Korisnik;
import main.java.com.projekat.WebProjekat.service.LoginService;
import main.java.com.projekat.WebProjekat.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.management.InvalidAttributeValueException;
import javax.security.auth.login.AccountNotFoundException;
import javax.servlet.http.HttpSession;
import java.util.Hashtable;

@RestController
public class LoginRestController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private SessionService sessionService;

    @PostMapping("api/login")
    public ResponseEntity login(@RequestBody LoginDto loginDto, HttpSession session){

        Hashtable<String,String> errorDic = new Hashtable<>();

        if (loginDto.getKorisnickoIme() == null || loginDto.getKorisnickoIme().isEmpty())
            errorDic.put("korisnickoIme" , "Korisnicko ime je obavezno");
        if(loginDto.getLozinka() == null || loginDto.getLozinka().isEmpty())
            errorDic.put("lozinka", "Lozinka je obavezna");

        if(!errorDic.isEmpty())
            return new ResponseEntity(errorDic, HttpStatus.BAD_REQUEST);

        Korisnik korisnik = null;

        try{
            korisnik = loginService.login(loginDto.getKorisnickoIme(),loginDto.getLozinka());
        } catch (AccountNotFoundException e){
            errorDic.put("korisnickoIme", e.getMessage());
        } catch(InvalidAttributeValueException e){
            errorDic.put("lozinka", e.getMessage());
        }

        if(!errorDic.isEmpty() || korisnik==null)
            return new ResponseEntity(errorDic,HttpStatus.BAD_REQUEST);

        session.setAttribute("role",korisnik.getUloga());
        session.setAttribute("user", korisnik);

        return new ResponseEntity("Successfully login!",HttpStatus.OK);


    }

    @PostMapping("api/logout")
    public ResponseEntity Logout(HttpSession session){

        Korisnik ulogovanKorisnik = (Korisnik) session.getAttribute("user");

        if (ulogovanKorisnik == null)
            return new ResponseEntity("Forbidden", HttpStatus.FORBIDDEN);

        session.invalidate();
        return new ResponseEntity("Successfully logged out", HttpStatus.OK);

    }
}
