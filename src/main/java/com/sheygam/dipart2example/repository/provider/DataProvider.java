package com.sheygam.dipart2example.repository.provider;

import com.sheygam.dipart2example.repository.entity.PersonEntity;

import java.util.List;

public interface DataProvider {
    void save(List<PersonEntity> persons);
    List<PersonEntity> getAll();
}
