package com.example.registerbean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class PersonManagerRegisterController {

    @Autowired
    GenericApplicationContext applicationContext;

    @Autowired
    ConfigurableBeanFactory beanFactory;

    @GetMapping("/registerPersonManager")
    public void registerPersonManager() {
        PersonDao personDao = applicationContext.getBean(PersonDao.class);
        PersonManager personManager = new PersonManager();
        personManager.setPersonDao(personDao);
        beanFactory.registerSingleton("personManager3", personManager);
    }

    @GetMapping("/usePersonManager1")
    public String usePersonManager1() {
        PersonManager personManager = applicationContext.getBean("personManager1", PersonManager.class);
        return personManager.newPerson().toString();
    }

    @GetMapping("/usePersonManager2")
    public String usePersonManager2() {
        PersonManager personManager = applicationContext.getBean("personManager2", PersonManager.class);
        return personManager.newPerson().toString();
    }

    @GetMapping("/usePersonManager3")
    public String usePersonManager3() {
        PersonManager personManager = applicationContext.getBean("personManager3", PersonManager.class);
        return personManager.newPerson().toString();
    }
}
