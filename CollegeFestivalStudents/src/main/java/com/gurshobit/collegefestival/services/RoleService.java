package com.gurshobit.collegefestival.services;

import com.gurshobit.collegefestival.entities.Role;
import com.gurshobit.collegefestival.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {
    @Autowired
    RoleRepository roleRepository;

    public Role save(Role role){
        return roleRepository.save(role);
    }

    public Optional<Role> getRoleByName(String name){
        return roleRepository.getByName(name);
    }

}
