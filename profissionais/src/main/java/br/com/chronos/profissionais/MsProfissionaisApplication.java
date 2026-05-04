package br.com.chronos.profissionais;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class MsProfissionaisApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsProfissionaisApplication.class, args);
    }
}