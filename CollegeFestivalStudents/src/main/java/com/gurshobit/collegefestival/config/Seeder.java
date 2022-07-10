package com.gurshobit.collegefestival.config;

import com.gurshobit.collegefestival.entities.Role;
import com.gurshobit.collegefestival.entities.User;
import com.gurshobit.collegefestival.repositories.RoleRepository;
import com.gurshobit.collegefestival.repositories.UserRepository;
import com.gurshobit.collegefestival.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
public class Seeder implements CommandLineRunner {
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;

    @Override
    public void run(String... args) throws Exception {
        List<Role> roleList = roleRepository.findAll();

        if(roleList.size() == 0){
            Role roleOne = new Role();
            roleOne.setName("ROLE_ADMIN");

            Role roleTwo = new Role();
            roleTwo.setName("ROLE_USER");

            roleRepository.save(roleOne);
            roleRepository.save(roleTwo);
        }

        List<User> userList = userRepository.findAll();

        if (userList.size() == 0){
            final String textPassword = "12345678";

            User userOne = new User();
            userOne.setUsername("admin@dummymail.com");
            userOne.setPassword(textPassword);

            User savedUserOne = userService.save(userOne);

            Optional<User> userOptionalOne = userRepository.findById(savedUserOne.getId());
            User insertedUserOne = userOptionalOne.get();

            Set<Role> newRolesUserOne = new HashSet<>();
            Optional<Role> roleOptionalOne = roleRepository.findById(1);

            if(roleOptionalOne.isPresent()){
                Role userOneRole = roleOptionalOne.get();
                newRolesUserOne.add(userOneRole);
            }

            insertedUserOne.setRoles(newRolesUserOne);
            userRepository.save(insertedUserOne);


            User userTwo = new User();
            userTwo.setUsername("user@dummymail.com");
            userTwo.setPassword(textPassword);

            User savedUserTwo = userService.save(userTwo);

            Optional<User> userOptionalTwo = userRepository.findById(savedUserTwo.getId());
            User insertedUserTwo = userOptionalTwo.get();

            Set<Role> newRolesUserTwo = new HashSet<>();
            Optional<Role> roleOptionalTwo = roleRepository.findById(2);

            if(roleOptionalTwo.isPresent()){
                Role userTwoRole = roleOptionalTwo.get();
                newRolesUserTwo.add(userTwoRole);
            }

            insertedUserTwo.setRoles(newRolesUserTwo);
            userRepository.save(insertedUserTwo);

        }
    }
}
