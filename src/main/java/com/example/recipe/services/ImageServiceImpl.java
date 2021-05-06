package com.example.recipe.services;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.recipe.domain.Recipe;
import com.example.recipe.repositories.RecipeRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ImageServiceImpl implements ImageService{
	
	private final RecipeRepository recipeRepository;
	
	public ImageServiceImpl(RecipeRepository recipeRepository) {
		// TODO Auto-generated constructor stub
		this.recipeRepository = recipeRepository;
	}

	@Override
	@Transactional
	public void saveImageFile(Long recipeId, MultipartFile file) {
		// TODO Auto-generated method stub
		log.debug("Saving the File");
		
		try {
			Recipe recipe = recipeRepository.findById(recipeId).get();
			
			Byte[] byteObjects = new Byte[file.getBytes().length];
			
			int i=0;
			
			for(byte b:file.getBytes()) {
				byteObjects[i++] = b;
			}
			
			recipe.setImage(byteObjects);
			
			recipeRepository.save(recipe);
		} catch (IOException e) {
			// TODO: handle exception
			log.debug("Error Occured");
			
			e.printStackTrace();
		}
	}

}
