package main.java.com.projekat.WebProjekat.controller;

import main.java.com.projekat.WebProjekat.dto.PorudzbinaDto.PorudzbinaDto;
import main.java.com.projekat.WebProjekat.dto.PorudzbinaDto.PorudzbinaKorpaDto;
import main.java.com.projekat.WebProjekat.entity.*;
import main.java.com.projekat.WebProjekat.repository.PorudzbinaRepository;
import main.java.com.projekat.WebProjekat.service.ArtikalService;
import main.java.com.projekat.WebProjekat.service.KorisnikService;
import main.java.com.projekat.WebProjekat.service.PorudzbinaService;
import main.java.com.projekat.WebProjekat.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.*;

@RestController
public class PorudzbinaRestController {
    @Autowired
    private SessionService sessionService;

    @Autowired
    private ArtikalService artikalService;

    @Autowired
    private KorisnikService korisnikService;

    @Autowired PorudzbinaRepository porudzbinaRepository;

    @Autowired
    private PorudzbinaService porudzbinaService;

    @GetMapping("api/porudzbine-kupac")
    public ResponseEntity<List<PorudzbinaDto>> getPorudzbineKupac(HttpSession session){
        //Boolean proveraSesije = sessionService.validateRole(session, "Kupac");

        /*if(!proveraSesije){
            return new ResponseEntity("Nemate potrebne privilegije!", HttpStatus.BAD_REQUEST);
        }*/

        Kupac kupac = (Kupac) session.getAttribute("user");
        List<Porudzbina> porudzbine = this.porudzbinaService.findAll();
        List<PorudzbinaDto> dtos = new ArrayList<>();

        for(Porudzbina p : porudzbine){
            if(p.getKorisnickoIme().equals(kupac.getKorisnickoIme())){
                PorudzbinaDto dto = new PorudzbinaDto(p);
                dtos.add(dto);
            }

        }

        return ResponseEntity.ok(dtos);

    }




    @GetMapping(value = "api/porudzbine-cekaDostavljaca",
                produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PorudzbinaDto>> getPorudzbineCekaDostavljaca(HttpSession session){
        /*Boolean proveraSesije = sessionService.validateRole(session, "Dostavljac");

        if(!proveraSesije){
            return new ResponseEntity("Nemate potrebne privilegije!", HttpStatus.BAD_REQUEST);
        }*/


        List<Porudzbina> porudzbine = this.porudzbinaService.findAll();
        List<PorudzbinaDto> porudzbineCekaju = new ArrayList<>();

        for(Porudzbina p : porudzbine){
            if(p.getStatus().equals(Status.ceka)){
                PorudzbinaDto dto = new PorudzbinaDto(p);
                porudzbineCekaju.add(dto);
            }
        }

        return ResponseEntity.ok(porudzbineCekaju);

    }
    @GetMapping("api/porudzbine-dostavljac")
    public ResponseEntity<List<Porudzbina>> getPorudzbineDostavljac(HttpSession session){
        /*Boolean proveraSesije = sessionService.validateRole(session, "Dostavljac");

        if(!proveraSesije){
            return new ResponseEntity("Nemate potrebne privilegije!", HttpStatus.BAD_REQUEST);
        }*/
        Dostavljac dostavljac = (Dostavljac) session.getAttribute("user");

        List<Porudzbina> njegovePorudzbine = this.porudzbinaService.findAllForDostavljac(dostavljac);

        return ResponseEntity.ok(njegovePorudzbine);

    }

    @PostMapping("/api/porudzbine-dodajArtikal/{id}/{korisnickoIme}")

    public ResponseEntity dodajUKorpu(@PathVariable Long id, HttpSession session){
        //Boolean proveraSesije = sessionService.validateRole(session,"Kupac");

        /*if(!proveraSesije){
            return  new ResponseEntity("Nemate potrebne privilegije!", HttpStatus.BAD_REQUEST);
        }*/

        Artikal artikal = artikalService.findOne(id);

        Restoran restoran = artikal.getRestoran();

        Kupac kupac = (Kupac) session.getAttribute("user");


        if(porudzbinaService.findByStatus(kupac,Status.u_korpi)==null){
            Porudzbina porudzbina = new Porudzbina();
            porudzbina.setKorisnickoIme(kupac.getKorisnickoIme());
            porudzbina.setCena(artikal.getCena() + porudzbina.getCena());
            porudzbina.setDatum(new Date(101, Calendar.AUGUST, 21));
            porudzbina.setStatus(Status.u_korpi);
            porudzbina.getPoruceniArtikli().add(artikal);
            porudzbina.setRestoran(restoran);
            kupac.getPorudzbine().add(porudzbina);
            porudzbinaService.save(porudzbina);
        } else {
            Porudzbina porudzbina = porudzbinaService.findByStatus(kupac, Status.u_korpi);
            porudzbina.getPoruceniArtikli().add(artikal);
            porudzbinaService.save(porudzbina);
        }

        korisnikService.save(kupac,Uloga.Kupac);
        return new ResponseEntity("Dodat artikal", HttpStatus.OK);
    }

    @PutMapping("/api/porudzbine-ukloniArtikal/{id}")
    public ResponseEntity izbaciIzKorpe(@PathVariable Long id, HttpSession session){
        Boolean proveraSesije = sessionService.validateRole(session,"Kupac");

        if(!proveraSesije){
            return  new ResponseEntity("Nemate potrebne privilegije!", HttpStatus.BAD_REQUEST);
        }

        Kupac kupac = (Kupac) session.getAttribute("user");

        Porudzbina porudzbina = porudzbinaService.findByStatus(kupac, Status.u_korpi);

        porudzbinaService.ukloniArtikal(porudzbina, kupac, id);
        
        return new ResponseEntity("Uspesno obrisan artikal", HttpStatus.OK);
    }

    @GetMapping("/api/porudzbine-pregledKorpe")
    public ResponseEntity<PorudzbinaKorpaDto> pregledKorpe(HttpSession session){
        Boolean proveraSesije = sessionService.validateRole(session,"Kupac");

        if(!proveraSesije){
            return  new ResponseEntity("Nemate potrebne privilegije!", HttpStatus.BAD_REQUEST);
        }

        Kupac kupac = (Kupac) session.getAttribute("user");

        Porudzbina porudzbina = porudzbinaService.findByStatus(kupac,Status.u_korpi);

        PorudzbinaKorpaDto porudzbinaKorpaDto = new PorudzbinaKorpaDto(porudzbina);

        return ResponseEntity.ok(porudzbinaKorpaDto);
    }

    @PutMapping("/api/porudzbine-poruci")
    public ResponseEntity poruci(HttpSession session){
        Boolean proveraSesije = sessionService.validateRole(session,"Kupac");

        if(!proveraSesije){
            return  new ResponseEntity("Nemate potrebne privilegije!", HttpStatus.BAD_REQUEST);
        }

        Kupac kupac = (Kupac) session.getAttribute("user");

        Porudzbina porudzbina = porudzbinaService.findByStatus(kupac,Status.u_korpi);

        porudzbina.setStatus(Status.obrada);


        porudzbinaService.save(porudzbina);
        korisnikService.save(kupac, kupac.getUloga());

        return new ResponseEntity("Uspesno poruceno.", HttpStatus.OK);
    }

    @PutMapping("/api/porudzbine-menadzerStatus")
    public ResponseEntity priprema(HttpSession session, @RequestParam("korisnicko_ime") String korisnickoIme){
        Boolean proveraSesije = sessionService.validateRole(session,"Menadzer");

        if(!proveraSesije){
            return  new ResponseEntity("Nemate potrebne privilegije!", HttpStatus.BAD_REQUEST);
        }

        Kupac kupac = (Kupac) korisnikService.findOne(korisnickoIme);

        if(porudzbinaService.findByStatus(kupac,Status.obrada) != null){
            Porudzbina porudzbina = porudzbinaService.findByStatus(kupac,Status.obrada);
            porudzbina.setStatus(Status.priprema);
            porudzbinaService.save(porudzbina);
            korisnikService.save(kupac, kupac.getUloga());
            return new ResponseEntity("Porudzbina se priprema.", HttpStatus.OK);
        }

        if(porudzbinaService.findByStatus(kupac,Status.priprema) != null){
            Porudzbina porudzbina = porudzbinaService.findByStatus(kupac,Status.priprema);
            porudzbina.setStatus(Status.ceka);
            porudzbinaService.save(porudzbina);
            korisnikService.save(kupac, kupac.getUloga());
            return new ResponseEntity("Porudzbina ceka dostavljaca.", HttpStatus.OK);
        }
        return new ResponseEntity("Porudzbina nije spremna!", HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/api/porudzbine-dostavljacStatus")
    public ResponseEntity preuzmiPorudzbinu(HttpSession session, @RequestParam("korisnicko_ime") String korisnickoIme){
        Boolean proveraSesije = sessionService.validateRole(session,"Dostavljac");

        if(!proveraSesije){
            return  new ResponseEntity("Nemate potrebne privilegije!", HttpStatus.BAD_REQUEST);
        }

        Kupac kupac = (Kupac) korisnikService.findOne(korisnickoIme);

        if(porudzbinaService.findByStatus(kupac,Status.ceka) != null){
            Porudzbina porudzbina = porudzbinaService.findByStatus(kupac,Status.obrada);
            porudzbina.setStatus(Status.transport);
            porudzbinaService.save(porudzbina);
            korisnikService.save(kupac, kupac.getUloga());
            return new ResponseEntity("Porudzbina je u transportu.", HttpStatus.OK);
        }

        if(porudzbinaService.findByStatus(kupac,Status.transport) != null){
            Porudzbina porudzbina = porudzbinaService.findByStatus(kupac,Status.priprema);
            porudzbina.setStatus(Status.dostavljeno);
            porudzbinaService.save(porudzbina);
            kupac.setBrojSakupljenihBodova((int) (kupac.getBrojSakupljenihBodova() + (porudzbina.getCena() / 1000) * 133));
            korisnikService.save(kupac, kupac.getUloga());
            return new ResponseEntity("Porudzbina je dostavljena.", HttpStatus.OK);
        }
        return new ResponseEntity("Porudzbina nije spremna!", HttpStatus.BAD_REQUEST);
    }
}
