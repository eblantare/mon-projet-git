// package com.blt.gestadmin.securities.config;

// import liquibase.integration.spring.SpringLiquibase;

// import org.springframework.beans.factory.annotation.Qualifier;
// import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
// import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
// import org.springframework.boot.context.properties.EnableConfigurationProperties;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;

// import javax.sql.DataSource;

// @Configuration
// @EnableConfigurationProperties(LiquibaseProperties.class)
// public class LiquibaseCustomConfig {

// @Bean
//     @ConditionalOnProperty(name="spring.liquibase.enabled", havingValue = "false",matchIfMissing = false)
//     public SpringLiquibase liquibase(@Qualifier("dataSource") DataSource dataSource) {
//         SpringLiquibase liquibase = new SpringLiquibase();
//         liquibase.setDataSource(dataSource);
//         liquibase.setChangeLog("classpath:db/changelog/db.changelog-master.xml");
//         liquibase.setShouldRun(true); // ou false pour désactiver
//         // liquibase.setContexts("dev"); // facultatif
//         return liquibase;
//     }
// }