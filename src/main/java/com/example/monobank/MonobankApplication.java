package com.example.monobank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.monobank.repositories")
@EnableTransactionManagement
@EntityScan(basePackages = "com.example.monobank.entities")
public class MonobankApplication {

    public static void main(String[] args) {
        SpringApplication.run(MonobankApplication.class, args);
    }

}
