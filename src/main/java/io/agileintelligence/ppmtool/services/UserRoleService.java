package io.agileintelligence.ppmtool.services;


import io.agileintelligence.ppmtool.domain.Role;
import io.agileintelligence.ppmtool.domain.User_Roles;
import io.agileintelligence.ppmtool.domain.Users;
import io.agileintelligence.ppmtool.exceptions.ResourceNotFoundException;
import io.agileintelligence.ppmtool.repositories.RoleRepository;
import io.agileintelligence.ppmtool.repositories.UserRoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserRoleService {

    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private RoleRepository roleRepository;

  
    public List<User_Roles> findAll(){
        return userRoleRepository.findAll();
    }
    public List<User_Roles> findAllByUser_id(Long user_id){
    	
        return userRoleRepository.findAllByUser_id(user_id);
    }
    
    public List<Role> findRolesByUser_idNotIn(Long user_id){
    	List<Role> listRoles = roleRepository.findAll();
    	List<User_Roles> listUserroles = userRoleRepository.findAllByUser_id(user_id);
    	
    	for(User_Roles uroles : listUserroles) {
    		listRoles.remove(uroles.getUser().getId().intValue());
    	}
    	
        return listRoles;
    }
    
    // get all roles
    public List<Role> findAllRoles(){
    	Long id = (long) 1;
    	
    	
        return roleRepository.findAll();
    }
    
    public User_Roles createUserRole(User_Roles userRole) {
		return userRoleRepository.save(userRole);
	}
	
    public User_Roles getUserRoleById(Long id) {
		
		User_Roles userRole = userRoleRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("userrole not exist with id :" + id));
		return userRole;
				
	}
    
 
 	public ResponseEntity<Map<String, Boolean>> deleteUserRole(@PathVariable Long id){
 		User_Roles userRole = userRoleRepository.findById(id)
 				.orElseThrow(() -> new ResourceNotFoundException("userrole not exist with id :" + id));
 		
 		userRoleRepository.delete(userRole);
 		Map<String, Boolean> response = new HashMap<>();
 		response.put("deleted", Boolean.TRUE);
 		return ResponseEntity.ok(response);
 	}
 	
 	
	public ResponseEntity<User_Roles> updateUserRole(@PathVariable Long id, @RequestBody User_Roles userRoleDetails){
		User_Roles userRole = userRoleRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("UserRole not exist with id :" + id));
		
		//category.setName(categoryDetails.getName());
		
		
		User_Roles updatedUserRole = userRoleRepository.save(userRole);
		return ResponseEntity.ok(updatedUserRole);
	}

}
