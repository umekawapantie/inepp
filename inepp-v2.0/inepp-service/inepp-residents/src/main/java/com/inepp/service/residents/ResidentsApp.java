package com.inepp.service.residents;

import com.inepp.common.startup.BrowserStartup;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.inepp.domain.entity")
@EnableJpaRepositories(basePackages = "com.inepp.domain.dao")
@ComponentScan(basePackages = {
        "com.inepp.common",
        "com.inepp.security.service",
        "com.inepp.service.residents"})

public class ResidentsApp {
    private static final String PORT = "9092";
    public static void main(String[] args) {
        SpringApplication.run(ResidentsApp.class);
        new BrowserStartup().run(PORT);
    }
}
