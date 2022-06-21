package main.java.com.projekat.WebProjekat.dao;

import main.java.com.projekat.WebProjekat.dto.RestoranDto.RestoranPrikazDto;
import main.java.com.projekat.WebProjekat.entity.Restoran;
import main.java.com.projekat.WebProjekat.util.SearchCriteria;

import java.util.List;

public interface IRestaurantDAO {
    List<RestoranPrikazDto> search(List<SearchCriteria> params);

    void save(Restoran entity);
}
