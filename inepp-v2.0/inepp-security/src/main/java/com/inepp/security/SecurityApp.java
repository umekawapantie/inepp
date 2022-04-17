package com.inepp.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.inepp.domain.entity")
@EnableJpaRepositories(basePackages = "com.inepp.domain.dao")
public class SecurityApp {
    public static void main(String[] args) throws Exception {
      //  new BrowserStartup().run("9091");
        SpringApplication.run(SecurityApp.class);
    }
}
