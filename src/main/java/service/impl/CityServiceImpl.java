package service.impl;

import base.service.impl.BaseEntityServiceImpl;
import entity.City;
import repository.CityRepository;
import service.CityService;

@SuppressWarnings(value = "unused")
public class CityServiceImpl
        extends BaseEntityServiceImpl<City, Integer, CityRepository>
        implements CityService {


    public CityServiceImpl(CityRepository repository) {
        super(repository);
    }

    @Override
    public City fidById(Integer id) {
        return repository.fidById(id);
    }
}
