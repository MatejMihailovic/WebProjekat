package main.java.com.projekat.WebProjekat.controller;

import main.java.com.projekat.WebProjekat.dto.KorisnikDto;
import main.java.com.projekat.WebProjekat.dto.RestoranDto;
import main.java.com.projekat.WebProjekat.entity.Korisnik;
import main.java.com.projekat.WebProjekat.entity.Lokacija;
import main.java.com.projekat.WebProjekat.entity.Restoran;
import main.java.com.projekat.WebProjekat.service.RestoranService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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

    @GetMapping("/api/restorani/pretrazi")
    public ResponseEntity<List<RestoranDto>> getRestoran(@RequestBody RestoranDto dto, HttpSession session){
        List<RestoranDto> restorani = new ArrayList<>();

        if(dto.getNaziv().isEmpty() && dto.getLokacija() == null && dto.getTipRestorana().isEmpty()){
            return new ResponseEntity("Invalid search information!", HttpStatus.BAD_REQUEST);
        }

        if(!dto.getNaziv().isEmpty()) {
            restorani.add(new RestoranDto(restoranService.findOneByNaziv(dto.getNaziv())));
        }

        if(dto.getLokacija() != null && !restorani.contains(new RestoranDto(restoranService.findOneByLokacija(dto.getLokacija())))) {
            restorani.add(new RestoranDto(restoranService.findOneByLokacija(dto.getLokacija())));
        }

        if(!dto.getTipRestorana().isEmpty()) {
            for (RestoranDto rdto : (restoranService.findByTipRestorana(dto.getTipRestorana()))) {
                if (!(restorani.contains(rdto))) {
                    restorani.add(rdto);
                }
            }
        }

        session.invalidate();
        return ResponseEntity.ok(restorani);
    }
}
