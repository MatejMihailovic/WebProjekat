package main.java.com.projekat.WebProjekat.dao;

import main.java.com.projekat.WebProjekat.entity.Restoran;
import main.java.com.projekat.WebProjekat.util.SearchCriteria;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class RestaurantDAO implements IRestaurantDAO{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Restoran> search(final List<SearchCriteria> params) {
        final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<Restoran> query = builder.createQuery(Restoran.class);
        final Root r = query.from(Restoran.class);

        Predicate predicate = builder.conjunction();
        RestoranSearchQueryCriteriaConsumer searchConsumer = new RestoranSearchQueryCriteriaConsumer(predicate, builder, r);
        params.stream().forEach(searchConsumer);
        predicate = searchConsumer.getPredicate();
        query.where(predicate);

        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public void save(final Restoran entity) {
        entityManager.persist(entity);
    }
}
