package main.java.com.projekat.WebProjekat.controller;

import main.java.com.projekat.WebProjekat.dto.ArtikalDto;
import main.java.com.projekat.WebProjekat.dto.RestoranDto;
import main.java.com.projekat.WebProjekat.dto.RestoranPrikazDto;
import main.java.com.projekat.WebProjekat.entity.Artikal;
import main.java.com.projekat.WebProjekat.entity.Komentar;
import main.java.com.projekat.WebProjekat.entity.Menadzer;
import main.java.com.projekat.WebProjekat.entity.Restoran;
import main.java.com.projekat.WebProjekat.service.ArtikalService;
import main.java.com.projekat.WebProjekat.service.KomentarService;
import main.java.com.projekat.WebProjekat.service.RestoranService;
import main.java.com.projekat.WebProjekat.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
public class RestoranRestController {

    @Autowired
    private RestoranService restoranService;

    @Autowired
    private ArtikalService artikalService;

    @Autowired
    private KomentarService komentarService;

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

        if(dto.getLokacija() != null) {
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

    @GetMapping("/api/restorani/pretrazi/izbor/{id}")
    public ResponseEntity<RestoranPrikazDto> izborRestorana(@PathVariable(name = "id") Long id){

        Restoran restoran = restoranService.findOne(id);

        List<Komentar> listaKomentara = komentarService.findAll(restoran);

        RestoranPrikazDto prikazDto = new RestoranPrikazDto(restoran);

        prikazDto.setKomentari(listaKomentara);

        prikazDto.setProsek(komentarService.prosecnaOcena(listaKomentara));

        return ResponseEntity.ok(prikazDto);
    }
}
