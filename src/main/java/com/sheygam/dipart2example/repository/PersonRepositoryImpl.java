package com.sheygam.dipart2example.repository;

import com.sheygam.dipart2example.repository.entity.PersonEntity;
import com.sheygam.dipart2example.repository.provider.DataProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Repository
public class PersonRepositoryImpl implements PersonRepository {
    @Autowired
    DataProvider dataProvider;

    private CopyOnWriteArrayList<PersonEntity> list;

//    public PersonRepositoryImpl() {
//        list = new CopyOnWriteArrayList<>(dataProvider.getAll());
//    }

    @PostConstruct
    private void postConstruct(){
        list = new CopyOnWriteArrayList<>(dataProvider.getAll());
    }

    @PreDestroy
    private void preDestroy(){
        dataProvider.save(list);
    }

    @Override
    public void addPerson(PersonEntity person) {
        list.add(person);
    }

    @Override
    public List<PersonEntity> getAll() {
        return list;
    }
}
