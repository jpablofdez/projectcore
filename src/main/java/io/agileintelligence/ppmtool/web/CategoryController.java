package io.agileintelligence.ppmtool.web;


import io.agileintelligence.ppmtool.domain.Category;
import io.agileintelligence.ppmtool.domain.Project;
import io.agileintelligence.ppmtool.domain.ProjectTask;
import io.agileintelligence.ppmtool.domain.ni_team;
import io.agileintelligence.ppmtool.exceptions.ResourceNotFoundException;
import io.agileintelligence.ppmtool.services.CategoryService;
import io.agileintelligence.ppmtool.services.MapValidationErrorService;
import io.agileintelligence.ppmtool.services.ProjectService;
import io.agileintelligence.ppmtool.services.TeamService;

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
@RequestMapping("/api/category")
@CrossOrigin
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @GetMapping("/all")
    public List<Category> getAllCategory(){
    	return categoryService.findAll();
    }
   
	
    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable Long id){

    	Category category = categoryService.getCategoryById(id);

        return new ResponseEntity<Category>(category, HttpStatus.OK);
    }

 	
	// create employee rest api
	//@PostMapping("/team")
    /*
    @PostMapping("")
	public Category createCategory2(@RequestBody Category category,BindingResult result) {
    	
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap!=null) 
        	return errorMap;
		
		return categoryService.createCategory(category);
	}
    */
    
    // create Cat rest api
 	//@PostMapping("/category")
    @PostMapping("")
    public ResponseEntity<?> createCategory(@Valid @RequestBody Category category, BindingResult result){

        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap!=null) return errorMap;

        Category catego = categoryService.createCategory(category);
        return new ResponseEntity<Category>(catego, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id, Principal principal){
    	categoryService.deleteCategory(id);
       
    	
        return new ResponseEntity<String>("Project with ID: '"+id+"' was deleted", HttpStatus.OK);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<?> updateCategory(@Valid @RequestBody Category categoryDetails, BindingResult result,
                                               @PathVariable Long id, Principal principal ){

        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if (errorMap != null) return errorMap;

        Category category = categoryService.getCategoryById(id);
		
		category.setName(categoryDetails.getName());
		Category updatedCategory = categoryService.createCategory(category);
        return new ResponseEntity<Category>(updatedCategory,HttpStatus.OK);

    }

}
