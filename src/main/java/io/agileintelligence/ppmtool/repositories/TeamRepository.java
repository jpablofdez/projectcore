package io.agileintelligence.ppmtool.repositories;

import io.agileintelligence.ppmtool.domain.Project;
import io.agileintelligence.ppmtool.domain.ni_team;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
//public interface TeamRepository extends CrudRepository<ni_team, Long> {
public interface TeamRepository extends JpaRepository<ni_team, Long>{
  
    @Override
    List<ni_team> findAll();
   
}
