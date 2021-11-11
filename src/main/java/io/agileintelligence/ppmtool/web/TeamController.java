package io.agileintelligence.ppmtool.web;


import io.agileintelligence.ppmtool.domain.Project;
import io.agileintelligence.ppmtool.domain.ni_team;
import io.agileintelligence.ppmtool.exceptions.ResourceNotFoundException;
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
@RequestMapping("/api/team")
@CrossOrigin
public class TeamController {

    @Autowired
    private TeamService teamService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @GetMapping("/all")
    public List<ni_team> getAllTeam(){
    	return teamService.findAllTeam();
    }
   
	
    @GetMapping("/{team_id}")
    public ResponseEntity<?> getTeamById(@PathVariable Long team_id){

    	ni_team team = teamService.getTeamById(team_id);

        return new ResponseEntity<ni_team>(team, HttpStatus.OK);
    }

 	
	// create employee rest api
	//@PostMapping("/team")
    @PostMapping("")
	public ni_team createTeam(@RequestBody ni_team team) {
		
		return teamService.createTeam(team);
	}
    @DeleteMapping("/{team_id}")
    public ResponseEntity<?> deleteProject(@PathVariable Long team_id, Principal principal){
    	teamService.deleteTeam(team_id);
       
    	
        return new ResponseEntity<String>("Project with ID: '"+team_id+"' was deleted", HttpStatus.OK);
    }

   // @PutMapping("/team/{team_id}")
    @PatchMapping("/{team_id}")
	public ResponseEntity<ni_team> updateTeam(@PathVariable Long team_id, @RequestBody ni_team teamDetails){
		ni_team team = teamService.getTeamById(team_id);
		
		team.setTeam_name(teamDetails.getTeam_name());
		ni_team updatedEmployee = teamService.createTeam(team);
		return ResponseEntity.ok(updatedEmployee);
	}
	
}
