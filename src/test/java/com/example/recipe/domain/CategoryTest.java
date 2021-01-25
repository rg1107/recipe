package com.example.recipe.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.BeforeEach;

public class CategoryTest {
	
	Category category;
	
	@BeforeEach
	public void setUp() {
		category = new Category();
	}

	@Test
	public void testGetId() {
		Long idValue= 4L;
		
		category.setId(idValue);
		
		assertEquals(idValue, category.getId());
	}

	@Test
	public void testGetDescription() {
		
	}

	@Test
	public void testGetRecipes() {
		
	}

}
