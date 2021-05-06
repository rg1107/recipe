package com.example.recipe.services;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.recipe.commands.RecipeCommand;
import com.example.recipe.converters.RecipeCommandToRecipe;
import com.example.recipe.converters.RecipeToRecipeCommand;
import com.example.recipe.domain.Recipe;
import com.example.recipe.exceptions.NotFoundException;
import com.example.recipe.repositories.RecipeRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {
	
	private final RecipeRepository recipeRepository;
	private final RecipeCommandToRecipe recipeCommandToRecipe;
    private final RecipeToRecipeCommand recipeToRecipeCommand;

	public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeCommandToRecipe recipeCommandToRecipe, RecipeToRecipeCommand recipeToRecipeCommand) {
		this.recipeRepository = recipeRepository;
		this.recipeCommandToRecipe = recipeCommandToRecipe;
        this.recipeToRecipeCommand = recipeToRecipeCommand;
	}

	@Override
	public Set<Recipe> getRecipes() {
		log.debug("I am in the service");
		
		Set<Recipe> recipeSet = new HashSet<>();
		recipeRepository.findAll().iterator().forEachRemaining(recipeSet::add);
		return recipeSet;
	}
	
	@Override
	public Recipe findById(Long l) {
		
		Optional<Recipe> recipeOptional = recipeRepository.findById(l);
		
		if(!recipeOptional.isPresent()) {
			
			throw new NotFoundException("Recipe Not Found for ID" + l.toString());
		}
		
		return recipeOptional.get();
		
	}
	
	@Override
	@Transactional
	public RecipeCommand findCommandById(Long l) {
		return recipeToRecipeCommand.convert(findById(l));
		
	}
	
	@Override
    @Transactional
    public RecipeCommand saveRecipeCommand(RecipeCommand command) {
        Recipe detachedRecipe = recipeCommandToRecipe.convert(command);

        Recipe savedRecipe = recipeRepository.save(detachedRecipe);
        log.debug("Saved RecipeId:" + savedRecipe.getId());
        return recipeToRecipeCommand.convert(savedRecipe);
    }
	
	@Override
	public void deleteById(Long idToDelete) {
		recipeRepository.deleteById(idToDelete);
	}
	
	
}
