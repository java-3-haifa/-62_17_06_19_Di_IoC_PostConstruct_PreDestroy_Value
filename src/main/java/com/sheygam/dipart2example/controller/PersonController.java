package com.sheygam.dipart2example.controller;


import com.sheygam.dipart2example.controller.dto.PersonDto;
import com.sheygam.dipart2example.repository.PersonRepository;
import com.sheygam.dipart2example.repository.entity.PersonEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
public class PersonController {
    @Autowired
    PersonRepository repository;

    @PostMapping("/person")
    public void addPerson(@RequestBody PersonDto person){
        PersonEntity entity = new PersonEntity();
        entity.name = person.name;
        entity.age = person.age;
        repository.addPerson(entity);
    }

    @GetMapping("/person")
    public List<PersonDto> getPersons(){
        return StreamSupport.stream(repository.getAll().spliterator(),false)
                .map(entity ->{
                    PersonDto dto = new PersonDto();
                    dto.name = entity.name;
                    dto.age = entity.age;
                    return dto;
                }).collect(Collectors.toList());
    }
}
