package main.java.com.projekat.WebProjekat.controller;

import main.java.com.projekat.WebProjekat.dto.RestoranDto.KreirajRestoranDto;
import main.java.com.projekat.WebProjekat.dto.RestoranDto.RestoranDto;
import main.java.com.projekat.WebProjekat.dto.RestoranDto.RestoranPrikazDto;
import main.java.com.projekat.WebProjekat.entity.Komentar;
import main.java.com.projekat.WebProjekat.entity.Menadzer;
import main.java.com.projekat.WebProjekat.entity.Restoran;
import main.java.com.projekat.WebProjekat.service.*;
import main.java.com.projekat.WebProjekat.dao.IRestaurantDAO;
import main.java.com.projekat.WebProjekat.util.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
public class RestoranRestController {

    @Autowired
    private RestoranService restoranService;

    @Autowired
    private KorisnikService korisnikService;

    @Autowired
    private IRestaurantDAO service;

    @Autowired
    private KomentarService komentarService;

    @Autowired
    private SessionService sessionService;

    @GetMapping("api/svi-restorani")
    public ResponseEntity<List<RestoranDto>> getRestorani(){
        List<Restoran> restorani = this.restoranService.findAll();

        List<RestoranDto> dtos = new ArrayList<>();
        for(Restoran r : restorani){
            RestoranDto dto = new RestoranDto(r);
            dtos.add(dto);
        }
        return ResponseEntity.ok(dtos);

    }
    @RequestMapping(method = RequestMethod.GET, value = "api/restorani")
    @ResponseBody
    public ResponseEntity<List<RestoranPrikazDto>> search(@RequestParam(value = "search") String search) {
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

    @GetMapping("/api/restorani/{id}")
    public ResponseEntity izborRestorana(@PathVariable(name = "id") Long id){

        Restoran restoran = restoranService.findOne(id);

        if(restoran == null){
            return new ResponseEntity<>("Restoran ne postoji!", HttpStatus.BAD_REQUEST);
        }
        List<Komentar> listaKomentara = komentarService.findAll(restoran);

        List<Komentar> komentari = new ArrayList<>();

        for(Komentar komentar : listaKomentara){
            komentari.add(komentar);
        }

        RestoranPrikazDto prikazDto = new RestoranPrikazDto(restoran, komentari);

        return ResponseEntity.ok(prikazDto);
    }
}
