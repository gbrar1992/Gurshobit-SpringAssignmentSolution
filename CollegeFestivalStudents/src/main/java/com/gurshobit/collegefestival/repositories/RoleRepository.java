package com.gurshobit.collegefestival.repositories;

import com.gurshobit.collegefestival.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> getByName(String name);
}
