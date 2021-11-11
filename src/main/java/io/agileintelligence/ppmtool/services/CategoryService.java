package io.agileintelligence.ppmtool.services;

import io.agileintelligence.ppmtool.domain.Category;
import io.agileintelligence.ppmtool.exceptions.ResourceNotFoundException;
import io.agileintelligence.ppmtool.repositories.CategoryRepository;
import io.agileintelligence.ppmtool.repositories.TeamRepository;
import io.agileintelligence.ppmtool.repositories.UserRepository;
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
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

  
    public List<Category> findAll(){
        return categoryRepository.findAll();
    }
    
    public Category createCategory(Category category) {
		return categoryRepository.save(category);
	}
	
    public Category getCategoryById(Long id) {
		//ni_team team = teamRepository.fi(team_id);
		
		Category category = categoryRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("team not exist with id :" + id));
		return category;
				
	}
    
 
 	public ResponseEntity<Map<String, Boolean>> deleteCategory(@PathVariable Long id){
 		Category category = categoryRepository.findById(id)
 				.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));
 		
 		categoryRepository.delete(category);
 		Map<String, Boolean> response = new HashMap<>();
 		response.put("deleted", Boolean.TRUE);
 		return ResponseEntity.ok(response);
 	}
 	
 	
	public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category categoryDetails){
		Category category = categoryRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Team not exist with id :" + id));
		
		category.setName(categoryDetails.getName());
		
		
		Category updatedCategory = categoryRepository.save(category);
		return ResponseEntity.ok(updatedCategory);
	}

}
