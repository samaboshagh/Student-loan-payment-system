package service;

import base.service.BaseEntityService;
import entity.City;

@SuppressWarnings("unused")
public interface CityService extends BaseEntityService<City, Integer> {

    City fidById(Integer id);
}
