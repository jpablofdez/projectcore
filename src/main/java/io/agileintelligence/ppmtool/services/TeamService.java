package io.agileintelligence.ppmtool.services;

import io.agileintelligence.ppmtool.domain.Backlog;
import io.agileintelligence.ppmtool.domain.Project;
import io.agileintelligence.ppmtool.domain.ProjectTask;
import io.agileintelligence.ppmtool.domain.Users;
import io.agileintelligence.ppmtool.domain.ni_team;
import io.agileintelligence.ppmtool.exceptions.ProjectIdException;
import io.agileintelligence.ppmtool.exceptions.ProjectNotFoundException;
import io.agileintelligence.ppmtool.exceptions.ResourceNotFoundException;
import io.agileintelligence.ppmtool.repositories.BacklogRepository;
import io.agileintelligence.ppmtool.repositories.ProjectRepository;
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
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private UserRepository userRepository;

  
    public List<ni_team> findAllTeam(){
        return teamRepository.findAll();
    }
    
    public ni_team createTeam(ni_team team) {
		return teamRepository.save(team);
	}
	
    public ni_team getTeamById(Long team_id) {
		//ni_team team = teamRepository.fi(team_id);
		
		ni_team team = teamRepository.findById(team_id)
				.orElseThrow(() -> new ResourceNotFoundException("team not exist with id :" + team_id));
		return team;
				
	}
    
 // delete employee rest api
 	@DeleteMapping("/team/{team_id}")
 	public ResponseEntity<Map<String, Boolean>> deleteTeam(@PathVariable Long team_id){
 		ni_team team = teamRepository.findById(team_id)
 				.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + team_id));
 		
 		teamRepository.delete(team);
 		Map<String, Boolean> response = new HashMap<>();
 		response.put("deleted", Boolean.TRUE);
 		return ResponseEntity.ok(response);
 	}
 	@PutMapping("/team/{team_id}")
	public ResponseEntity<ni_team> updateTeam(@PathVariable Long team_id, @RequestBody ni_team teamDetails){
		ni_team team = teamRepository.findById(team_id)
				.orElseThrow(() -> new ResourceNotFoundException("Team not exist with id :" + team_id));
		
		team.setTeam_name(teamDetails.getTeam_name());
		
		
		ni_team updatedEmployee = teamRepository.save(team);
		return ResponseEntity.ok(updatedEmployee);
	}

}
