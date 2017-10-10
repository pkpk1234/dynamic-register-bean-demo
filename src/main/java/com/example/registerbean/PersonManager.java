package com.example.registerbean;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.stereotype.Service;

@Data
public class PersonManager {

    private PersonDao personDao;

    public Person newPerson() {
        return this.personDao.createDemoPerson();
    }

}
