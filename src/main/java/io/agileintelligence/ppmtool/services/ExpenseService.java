package io.agileintelligence.ppmtool.services;

import io.agileintelligence.ppmtool.domain.Backlog;
import io.agileintelligence.ppmtool.domain.Expense;
import io.agileintelligence.ppmtool.domain.Project;
import io.agileintelligence.ppmtool.domain.ProjectTask;
import io.agileintelligence.ppmtool.domain.Users;
import io.agileintelligence.ppmtool.domain.ni_team;
import io.agileintelligence.ppmtool.exceptions.ProjectIdException;
import io.agileintelligence.ppmtool.exceptions.ProjectNotFoundException;
import io.agileintelligence.ppmtool.exceptions.ResourceNotFoundException;
import io.agileintelligence.ppmtool.repositories.ExpenseRepository;
import io.agileintelligence.ppmtool.repositories.TeamRepository;
import io.agileintelligence.ppmtool.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;


  
    public List<Expense> findAllExpense(){
        return expenseRepository.findAll();
    }
    
    public Expense createExpense(Expense expense) {
		return expenseRepository.save(expense);
	}
	
    public Expense getExpenseById(Long id) {
		//ni_team team = teamRepository.fi(team_id);
		
		Expense expense = expenseRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("team not exist with id :" + id));
		return expense;
				
	}
    
 // delete employee rest api
 	@DeleteMapping("/expense/{id}")
 	public ResponseEntity<Map<String, Boolean>> deleteExpense(@PathVariable Long id){
 		Expense expense = expenseRepository.findById(id)
 				.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));
 		
 		expenseRepository.delete(expense);
 		Map<String, Boolean> response = new HashMap<>();
 		response.put("deleted", Boolean.TRUE);
 		return ResponseEntity.ok(response);
 	}
 	@PutMapping("/expense/{id}")
	public ResponseEntity<Expense> updateExpense(@PathVariable Long id, @RequestBody Expense expenseDetails){
		Expense expense = expenseRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Team not exist with id :" + id));
		
		//expense.setTeam_name(teamDetails.getTeam_name());
		
		
		Expense updatedExpense = expenseRepository.save(expense);
		return ResponseEntity.ok(updatedExpense);
	}

}
