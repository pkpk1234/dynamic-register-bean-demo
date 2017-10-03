package com.example.registerbean;

import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class PersonManager {
    private PersonDao personDao;

    public Person newPerson() {
        return this.personDao.createDemoPerson();
    }
}
