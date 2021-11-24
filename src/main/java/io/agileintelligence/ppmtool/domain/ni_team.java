package io.agileintelligence.ppmtool.domain;


import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;


@Entity
@Table(name = "ni_team")
public class ni_team {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long team_id;
	
	@NotBlank(message = "Team name is required")
	@Column(name = "team_name")
	private String team_name;

	public ni_team() {
		
	}
	
	public ni_team(long team_id) {
		super();
		this.team_id = team_id;
		
	}
	
	public long getTeam_id() {
		return team_id;
	}

	public void setTeam_id(long team_id) {
		this.team_id = team_id;
	}

	public String getTeam_name() {
		return team_name;
	}

	public void setTeam_name(String team_name) {
		this.team_name = team_name;
	}

	
}