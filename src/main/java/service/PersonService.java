package service;

import base.service.BaseEntityService;
import entity.person.Person;

@SuppressWarnings("unused")
public interface PersonService<T extends Person> extends BaseEntityService<T, Integer> {

    T findByUserName(String userName);

    boolean existByUserNameAndPassword(String username , String password);

}