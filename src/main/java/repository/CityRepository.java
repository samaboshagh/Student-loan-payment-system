package repository;

import base.repository.BaseEntityRepository;
import entity.City;

public interface CityRepository extends BaseEntityRepository<City,Integer> {

    City fidById(Integer id);
}
