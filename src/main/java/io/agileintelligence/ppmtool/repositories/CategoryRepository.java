package io.agileintelligence.ppmtool.repositories;

import io.agileintelligence.ppmtool.domain.Category;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
//public interface TeamRepository extends CrudRepository<ni_team, Long> {
public interface CategoryRepository extends JpaRepository<Category, Long>{
  
    @Override
    List<Category> findAll();
   
}
