package com.example.registerbean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 使用rest请求的方式动态注册bean和验证
 *
 *
 */
@RestController
@Slf4j
public class PersonManagerRegisterController {

    /**
     * The Application context.
     */
    @Autowired
    GenericApplicationContext applicationContext;

    /**
     * The Bean factory.
     */
    @Autowired
    ConfigurableBeanFactory beanFactory;

    /**
     * 动态注册bean，此处注册的bean没有AOP的支持
     * curl http://localhost:8080/registerPersonManager
     */
    @GetMapping("/registerPersonManager")
    public void registerPersonManager() {
        PersonDao personDao = applicationContext.getBean(PersonDao.class);
        PersonManager personManager = new PersonManager();
        personManager.setPersonDao(personDao);
        beanFactory.registerSingleton("personManager3", personManager);

    }

    /**
     * 调用bean personManager1，AOP切面有效
     * curl http://localhost:8080/usePersonManager1
     * @return the string
     */
    @GetMapping("/usePersonManager1")
    public String usePersonManager1() {
        PersonManager personManager = applicationContext.getBean("personManager1", PersonManager.class);
        return personManager.newPerson().toString();
    }

    /**
     * 调用bean personManager2，AOP切面无效
     * curl http://localhost:8080/usePersonManager2
     * @return the string
     */
    @GetMapping("/usePersonManager2")
    public String usePersonManager2() {
        PersonManager personManager = applicationContext.getBean("personManager2", PersonManager.class);
        return personManager.newPerson().toString();
    }

    /**
     * 调用bean personManager3，AOP切面无效
     * 请求该方法之前，应该先请求registerPersonManager注册personManager3
     * curl http://localhost:8080/usePersonManager3
     * @return the string
     */
    @GetMapping("/usePersonManager3")
    public String usePersonManager3() {
        PersonManager personManager = applicationContext.getBean("personManager3", PersonManager.class);
        return personManager.newPerson().toString();
    }
}
