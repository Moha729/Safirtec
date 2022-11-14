package com.mo.safir;

import com.mo.safir.securityLevel.model.User;
import com.mo.safir.securityLevel.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SafirApplication {

    public static void main(String[] args) {
        SpringApplication.run(SafirApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(UserService users, PasswordEncoder encoder) {
        return args -> {
            boolean isUsersEmpty = users.fetchAll().isEmpty();
            if (isUsersEmpty){
            users.addNew(new User("user", encoder.encode("${Mo_pass}"),"ROLE_USER"));
            }
//            users.save(new User("user", encoder.encode("${Mo_pass}"),"ROLE_USER"));
//            users.save(new User("admin",encoder.encode("${Mo_pass}"),"ROLE_USER,ROLE_ADMIN"));

        };

    }

}
