package io.agileintelligence.ppmtool.services;

import io.agileintelligence.ppmtool.domain.Users;
import io.agileintelligence.ppmtool.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
   
    

    @Transactional
    public Users loadUserById(Long id){
        Users user = userRepository.getById(id);
        if(user==null) new UsernameNotFoundException("User not found");
        return user;

    }
    
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		  try{
			  Users user = userRepository.findByUsername(username);

			  return UserDetailsImpl.build(user);

	        }catch (Exception e){
	            throw new UsernameNotFoundException("User Not Found with username: " + username);
	        }
	}
}
