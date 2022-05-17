package main.java.com.projekat.WebProjekat.controller;

import main.java.com.projekat.WebProjekat.dto.ArtikalDto;
import main.java.com.projekat.WebProjekat.dto.RestoranDto;
import main.java.com.projekat.WebProjekat.entity.Menadzer;
import main.java.com.projekat.WebProjekat.entity.Restoran;
import main.java.com.projekat.WebProjekat.service.RestoranService;
import main.java.com.projekat.WebProjekat.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
public class RestoranRestController {

    @Autowired
    private RestoranService restoranService;

    @Autowired
    private SessionService sessionService;

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

        if(dto.getLokacija() != null && !restorani.contains(dto)) {
            restorani.add(new RestoranDto(restoranService.findOneByLokacija(dto.getLokacija())));
        }

        if(!dto.getTipRestorana().isEmpty()) {
            for (RestoranDto rdto : (restoranService.findByTipRestorana(dto.getTipRestorana()))) {
                    restorani.add(rdto);
            }
        }
        //Uklanjamo duplikate
        for(int i = 0; i < restorani.size(); i++){
            for(int j = i + 1; j < restorani.size(); j++){
                if(restorani.get(i).getNaziv().equals(restorani.get(j).getNaziv()) || restorani.get(i).getLokacija() == restorani.get(j).getLokacija()){
                    restorani.remove(j);
                    j--;
                }
            }
        }

        session.invalidate();
        return ResponseEntity.ok(restorani);
    }

    @PutMapping("/api/restorani/dodajArtikal")
    public ResponseEntity<Restoran> dodavanjeArtikla(@RequestBody ArtikalDto artikalDto, HttpSession sesija){
        boolean proveraSesije = sessionService.validateRole(sesija, "Menadzer");

        if(!proveraSesije){
            return new ResponseEntity("Nemate potrebne privilegije!",HttpStatus.BAD_REQUEST);
        }

        if(artikalDto.getNaziv().isEmpty() || artikalDto.getCena() <= 0 || artikalDto.getTip() == null){
            return new ResponseEntity("Ova polja ne smeju biti prazna!", HttpStatus.BAD_REQUEST);
        }

        Menadzer menadzer = (Menadzer) sesija.getAttribute("user");

        return ResponseEntity.ok(restoranService.dodajArtikal(artikalDto, menadzer));
    }
}
