package io.agileintelligence.ppmtool.web;



import io.agileintelligence.ppmtool.domain.Category;
import io.agileintelligence.ppmtool.domain.Role;
import io.agileintelligence.ppmtool.domain.User_Roles;
import io.agileintelligence.ppmtool.domain.Users;
import io.agileintelligence.ppmtool.exceptions.ResourceNotFoundException;
import io.agileintelligence.ppmtool.services.MapValidationErrorService;
import io.agileintelligence.ppmtool.services.UserRoleService;
import io.agileintelligence.ppmtool.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/userroles")
@CrossOrigin
public class UserRoleController {

    @Autowired
    private UserRoleService userRoleService;


    @Autowired
    private UserService userService;
    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @GetMapping("/all")
    public List<User_Roles> getAllUserRoles(){
    	List<User_Roles>  userRoleList = userRoleService.findAll();
    	return userRoleList;
    }
    
    @GetMapping("/all/{user_id}")
    public List<User_Roles> getAllUserRoles(@PathVariable Long user_id){
    	Users usr;
    	
    	List<User_Roles>  userRoleList = userRoleService.findAllByUser_id(user_id);
    	return userRoleList;
    }
    
    @GetMapping("/rolenotin/{user_id}")
    public List<Role> getAllRolesNot(@PathVariable Long user_id){
    	List<Role> roleList = userRoleService.findRolesByUser_idNotIn(user_id);
    	return roleList;
    }
    
    @GetMapping("/allRoles")
    public List<Role> getAllRoles(){
    	List<Role> roleList = userRoleService.findAllRoles();
    	return roleList;
    }
	
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserRoleById(@PathVariable Long id){

    	User_Roles userRole = userRoleService.getUserRoleById(id);

        return new ResponseEntity<User_Roles>(userRole, HttpStatus.OK);
    }
    
    // create Cat rest api
 	//@PostMapping("/user role")
    @PostMapping("")
    public ResponseEntity<?> createUserRole(@Valid @RequestBody User_Roles userRole, BindingResult result){

        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap!=null) return errorMap;

        User_Roles userRol = userRoleService.createUserRole(userRole);
        return new ResponseEntity<User_Roles>(userRol, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserRole(@PathVariable Long id, Principal principal){
    	userRoleService.deleteUserRole(id);
       
    	
        return new ResponseEntity<String>("User role with ID: '"+id+"' was deleted", HttpStatus.OK);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<?> updateUserRole(@Valid @RequestBody User_Roles userroleDetails, BindingResult result,
                                               @PathVariable Long id, Principal principal ){

        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if (errorMap != null) return errorMap;

        User_Roles userRole = userRoleService.getUserRoleById(id);
		
        //userRole.setName(categoryDetails.getName());
		User_Roles updatedUserrole = userRoleService.createUserRole(userRole);
        return new ResponseEntity<User_Roles>(updatedUserrole,HttpStatus.OK);

    }

}
