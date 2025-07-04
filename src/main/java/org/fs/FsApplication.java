package org.fs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
//@SpringBootApplication
public class FsApplication {

    public static void main(String[] args) {
        SpringApplication.run(FsApplication.class, args);
    }

}
