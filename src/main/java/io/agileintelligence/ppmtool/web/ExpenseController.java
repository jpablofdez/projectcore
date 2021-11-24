package io.agileintelligence.ppmtool.web;


import io.agileintelligence.ppmtool.domain.Expense;
import io.agileintelligence.ppmtool.domain.Project;
import io.agileintelligence.ppmtool.domain.ProjectTask;
import io.agileintelligence.ppmtool.domain.ni_team;
import io.agileintelligence.ppmtool.domain.Category;
import io.agileintelligence.ppmtool.exceptions.ResourceNotFoundException;
import io.agileintelligence.ppmtool.services.ExpenseService;
import io.agileintelligence.ppmtool.services.MapValidationErrorService;
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
@RequestMapping("/api/expense")
@CrossOrigin
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @GetMapping("/all")
    public List<Expense> getAllExpense(){
    	return expenseService.findAllExpense();
    }
   
	
    @GetMapping("/{id}")
    public ResponseEntity<?> getExpenseById(@PathVariable Long id){

    	Expense expense = expenseService.getExpenseById(id);

        return new ResponseEntity<Expense>(expense, HttpStatus.OK);
    }

	//@PostMapping("/expense")
    /*
    @PostMapping("")
	public Expense createExpense(@RequestBody Expense expense) {
    	
    	
		return expenseService.createExpense(expense);
	}
    */
    @PostMapping("")
    public ResponseEntity<?> createExpense(@Valid @RequestBody Expense expense,
                                            BindingResult result){
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if (errorMap != null) return errorMap;

        Expense expen = expenseService.createExpense(expense);

        return new ResponseEntity<Expense>(expen, HttpStatus.CREATED);

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteExpense(@PathVariable Long id, Principal principal){
    	expenseService.deleteExpense(id);
       
    	
        return new ResponseEntity<String>("Project with ID: '"+id+"' was deleted", HttpStatus.OK);
    }

   // @PutMapping("/expense/{id}")
    @PatchMapping("/{id}")
	public ResponseEntity<Expense> updateExpense(@PathVariable Long id, @RequestBody Expense expenseDetails){
		Expense exp = expenseService.getExpenseById(id);
		Category cat = new Category();
		ni_team nt = new ni_team();
		
		//exp.setTeam_name(expenseDetails.getTeam_name());
		exp.setDescription(expenseDetails.getDescription());
		exp.setExpensedate(expenseDetails.getExpensedate());
		exp.setLocation(expenseDetails.getLocation());
		exp.setCategory(expenseDetails.getCategory());
		exp.setTeam(expenseDetails.getTeam());
		
		Expense updatedExpense = expenseService.createExpense(exp);
		return ResponseEntity.ok(updatedExpense);	
	}
    
	
	
}
