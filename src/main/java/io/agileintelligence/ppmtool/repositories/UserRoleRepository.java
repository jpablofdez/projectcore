package io.agileintelligence.ppmtool.repositories;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import io.agileintelligence.ppmtool.domain.Role;
import io.agileintelligence.ppmtool.domain.User_Roles;
import io.agileintelligence.ppmtool.domain.Users;



@Repository
public interface UserRoleRepository extends CrudRepository<User_Roles, Long> {
//public interface UserRoleRepository extends JpaRepository<User_Roles, Long>{
  
    @Override
    List<User_Roles> findAll();
    
    List<User_Roles> findAllByUser_id(Long userid);
   // Iterable<Project> findAllByProjectLeader(String username);
    //List<Role> findRoleByUser_id(Long userid);
   // @Query("select u from user_roles u where u.user_id = ?1")
   // List<User_Roles> findUserrolesByid(Long userid);
    
}
