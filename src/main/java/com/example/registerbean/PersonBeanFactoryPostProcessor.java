package com.example.registerbean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PersonBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        log.info("register personManager1>>>>>>>>>>>>>>>>");
        DefaultListableBeanFactory defaultListableBeanFactory
                = (DefaultListableBeanFactory) beanFactory;

        BeanDefinitionBuilder beanDefinitionBuilder =
                BeanDefinitionBuilder.genericBeanDefinition(PersonManager.class);
        beanDefinitionBuilder.addPropertyReference("personDao","personDao");
        BeanDefinition personManagerBeanDefinition = beanDefinitionBuilder.getRawBeanDefinition();
        defaultListableBeanFactory.registerBeanDefinition("personManager1", personManagerBeanDefinition);
        log.info("register personManager2>>>>>>>>>>>>>>>>");
        PersonDao personDao = beanFactory.getBean(PersonDao.class);
        PersonManager personManager = new PersonManager();
        personManager.setPersonDao(personDao);
        beanFactory.registerSingleton("personManager2",personManager);

    }
}
