package com.lcwaikiki.advertservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration;

@SpringBootApplication(exclude = LiquibaseAutoConfiguration.class)
public class AdvertServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(AdvertServiceApplication.class, args);
  }
}
