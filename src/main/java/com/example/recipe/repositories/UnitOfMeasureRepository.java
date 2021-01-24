package com.example.recipe.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.recipe.domain.UnitOfMeasure;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure,Long>{
	
	Optional<UnitOfMeasure> findByDescription(String description);
}
