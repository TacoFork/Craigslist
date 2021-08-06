package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandLineRunnerBean implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    public void run(String...args){
        User bart = new User("bart", "bart", true);
        Role bartRole = new Role("bart", "ROLE_USER");

        User user = new User("user", "user", true);
        Role userRole = new Role("user", "ROLE_USER");

        userRepository.save(bart);
        roleRepository.save(bartRole);

        userRepository.save(user);
        roleRepository.save(userRole);
    }
}
