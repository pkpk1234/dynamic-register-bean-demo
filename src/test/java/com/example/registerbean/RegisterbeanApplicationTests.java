package com.example.registerbean;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RegisterbeanApplicationTests {

    @Autowired
    ApplicationContext applicationContext;

    @Test
    public void testRegisterBeanInBeanFactoryPostProcessor() {
        //在PersonBeanFactoryPostProcessor中动态注册了personManager1和personManager2
        PersonManager personManager1 = applicationContext.getBean("personManager1", PersonManager.class);
        assertNotNull(personManager1);

        PersonManager personManager2 = applicationContext.getBean("personManager2", PersonManager.class);
        assertNotNull(personManager2);

        assertFalse(personManager1 == personManager2);
    }

}
