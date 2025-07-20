package com.blt.gestadmin.securities.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;@Configuration
public class JpaConfig {

    
    public BeanFactoryPostProcessor forceLiquibaseBeforJpa(){
        return bf->{
            if(bf.containsBeanDefinition("entityManagerFactory")){
                    bf.getBeanDefinition("entityManagerFactory").setDependsOn("liquibase");
            }
        };
    }

}
