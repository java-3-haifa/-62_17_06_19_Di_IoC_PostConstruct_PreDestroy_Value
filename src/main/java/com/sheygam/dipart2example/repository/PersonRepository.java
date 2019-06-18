package com.sheygam.dipart2example.repository;


import com.sheygam.dipart2example.repository.entity.PersonEntity;

public interface PersonRepository {
    void addPerson(PersonEntity person);
    Iterable<PersonEntity> getAll();

}
