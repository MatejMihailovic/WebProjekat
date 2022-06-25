package main.java.com.projekat.WebProjekat.dao;

import main.java.com.projekat.WebProjekat.dto.KorisnikDto;
import main.java.com.projekat.WebProjekat.entity.Korisnik;
import main.java.com.projekat.WebProjekat.util.SearchCriteria;

import java.util.List;

public interface IUserDAO {

    List<KorisnikDto> search(List<SearchCriteria> params);

    void save(Korisnik entity);
}
