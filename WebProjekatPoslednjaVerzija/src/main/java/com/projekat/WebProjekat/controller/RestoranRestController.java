package com.projekat.WebProjekat.controller;

import com.projekat.WebProjekat.dto.KorisnikDto;
import com.projekat.WebProjekat.dto.RestoranDto;
import com.projekat.WebProjekat.entity.Korisnik;
import com.projekat.WebProjekat.entity.Lokacija;
import com.projekat.WebProjekat.entity.Restoran;
import com.projekat.WebProjekat.service.RestoranService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
public class RestoranRestController {

    @Autowired
    private RestoranService restoranService;

    @GetMapping("/api/restorani")
    public ResponseEntity<List<RestoranDto>> getRestorani(){
        List<Restoran> restorani = this.restoranService.findAll();

        List<RestoranDto> dtos = new ArrayList<>();
        for(Restoran r : restorani){
            RestoranDto dto = new RestoranDto(r);
            dtos.add(dto);
        }
        return ResponseEntity.ok(dtos);

    }

    @GetMapping("/api/restorani/{lokacija}")
    public Restoran getRestoranByLokacija(@PathVariable(name = "lokacija") Lokacija lokacija, HttpSession session){

        Restoran restoran = (Restoran) session.getAttribute("user");
        RestoranDto dto = new RestoranDto(restoran);
        System.out.println(dto);
        session.invalidate();
        return restoranService.findOneByLokacija(lokacija);
    }
    @GetMapping("/api/restorani/{tip}")
    public Restoran getRestoranByTip(@PathVariable(name = "tip") String tip, HttpSession session){

        Restoran restoran = (Restoran) session.getAttribute("user");
        RestoranDto dto = new RestoranDto(restoran);
        System.out.println(dto);
        session.invalidate();
        return restoranService.findOneByTipRestorana(tip);
    }
    @GetMapping("/api/restorani/{naziv}")
    public Restoran getRestoranByNaziv(@PathVariable(name = "naziv") String naziv, HttpSession session){

        Restoran restoran = (Restoran) session.getAttribute("user");
        RestoranDto dto = new RestoranDto(restoran);
        System.out.println(dto);
        session.invalidate();
        return restoranService.findOneByNaziv(naziv);
    }
}
