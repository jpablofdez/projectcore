package io.agileintelligence.ppmtool.repositories;

import io.agileintelligence.ppmtool.domain.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<Users, Long> {


    Users findByUsername(String username);
    Users getById(Long id);
}
