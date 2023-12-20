package repository.impl;

import base.repository.impl.BaseEntityRepositoryImpl;
import entity.City;
import repository.CityRepository;

import javax.persistence.EntityManager;

@SuppressWarnings("unused")
public class CityRepositoryImpl
        extends BaseEntityRepositoryImpl<City, Integer>
        implements CityRepository {

    public CityRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<City> getEntityClass() {
        return City.class;
    }

    @Override
    public City fidById(Integer id) {
        try {
            return entityManager.createQuery("""
                            FROM City c WHERE c.id = :id
                            """, City.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}