package com.intern.ambassador;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class AmbassadorApplication {

    public static void main(String[] args) {
        SpringApplication.run(AmbassadorApplication.class, args);
    }

}
