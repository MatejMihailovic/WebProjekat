package main.java.com.projekat.WebProjekat.dao;

import main.java.com.projekat.WebProjekat.dto.KorisnikDto;
import main.java.com.projekat.WebProjekat.dto.RestoranDto.RestoranPrikazDto;
import main.java.com.projekat.WebProjekat.entity.Komentar;
import main.java.com.projekat.WebProjekat.entity.Korisnik;
import main.java.com.projekat.WebProjekat.entity.Restoran;
import main.java.com.projekat.WebProjekat.repository.KomentarRepository;
import main.java.com.projekat.WebProjekat.util.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDAO implements IUserDAO{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<KorisnikDto> search(final List<SearchCriteria> params) {
        final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<Korisnik> query = builder.createQuery(Korisnik.class);
        final Root r = query.from(Korisnik.class);

        Predicate predicate = builder.conjunction();
        UserSearchQueryCriteriaConsumer searchConsumer = new UserSearchQueryCriteriaConsumer(predicate, builder, r);
        params.stream().forEach(searchConsumer);
        predicate = searchConsumer.getPredicate();
        query.where(predicate);

        List<KorisnikDto> list = new ArrayList<>();

        for(Korisnik k : entityManager.createQuery(query).getResultList()){
            list.add(new KorisnikDto(k));
        }

        return list;
    }


    @Override
    public void save(final Korisnik entity) {
        entityManager.persist(entity);
    }
}

