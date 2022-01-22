package io.agileintelligence.ppmtool.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.agileintelligence.ppmtool.domain.ERole;
import io.agileintelligence.ppmtool.domain.Role;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	
	@Override
	List<Role> findAll();
	
	Optional<Role> findByName(ERole name);
}