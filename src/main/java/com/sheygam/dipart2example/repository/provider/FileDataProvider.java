package com.sheygam.dipart2example.repository.provider;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sheygam.dipart2example.repository.entity.PersonEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

@Component
public class FileDataProvider implements DataProvider{
    @Value("${fileName}")
    private String fileName;

    @Autowired
    private ObjectMapper mapper;


    @Override
    public void save(List<PersonEntity> persons) {
        try {
            mapper.writeValue(Files.newOutputStream(Path.of(fileName)),persons);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("File System error",e);
        }
    }

    @Override
    public List<PersonEntity> getAll() {
        try {
            if(Files.exists(Path.of(fileName))) {
                return mapper.readValue(Files.newBufferedReader(Path.of(fileName)), new TypeReference<List<PersonEntity>>() {
                });
            }else{
                return Collections.EMPTY_LIST;
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("File System error",e);
        }

    }
}
