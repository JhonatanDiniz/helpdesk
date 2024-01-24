package com.jhonatan.helpdesk.config;

import com.jhonatan.helpdesk.services.DBService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class Config {

    @Autowired
    private DBService dbService;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String value;

    @Bean
    public boolean instaciaDB(){
        if (value.equalsIgnoreCase("create")){
            this.dbService.instaciaDB();
        }
        return false;
    }
}
