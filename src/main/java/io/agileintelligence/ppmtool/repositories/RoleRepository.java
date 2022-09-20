package io.agileintelligence.ppmtool.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import io.agileintelligence.ppmtool.domain.ERole;
import io.agileintelligence.ppmtool.domain.Role;
import io.agileintelligence.ppmtool.domain.User_Roles;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	
	@Override
	List<Role> findAll();
	
	//Optional<Role> findByName(ERole name);
	//List<Role> findAllByUser_id(Long userid);
	//List<Role> findAllByUser_id(Long userid);
	//@Query("SELECT * FROM roles r where r.id not in(select u.role_id from user_roles u where u.user_id = 1)")
	//List<Role> findAllByRole();
}