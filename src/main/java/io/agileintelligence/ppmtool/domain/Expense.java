package io.agileintelligence.ppmtool.domain;

import java.time.Instant;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@NoArgsConstructor
@Data
@Table(name="expense")
public class Expense {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonFormat(pattern = "yyyy-mm-dd")
	private Date expensedate;
	
	private String description;
	
	private String location;
	
	@ManyToOne
	private Category category;
	
	@ManyToOne
	private ni_team Team;
	
	@JsonIgnore
	@ManyToOne
	private Users user;
	

}