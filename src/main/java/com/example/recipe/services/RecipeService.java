package com.example.recipe.services;

import java.util.Set;

import com.example.recipe.domain.Recipe;

import com.example.recipe.commands.RecipeCommand;

public interface RecipeService {
	
	Set<Recipe> getRecipes();
	
	Recipe findById(Long l);
	
	RecipeCommand saveRecipeCommand(RecipeCommand command);
}
