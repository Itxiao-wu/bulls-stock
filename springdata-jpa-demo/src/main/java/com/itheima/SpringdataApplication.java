package com.itheima;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = {"com.itheima"})
@EnableJpaRepositories(basePackages = {"com.itheima"})
@SpringBootApplication
public class SpringdataApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringdataApplication.class,args);
    }
}
