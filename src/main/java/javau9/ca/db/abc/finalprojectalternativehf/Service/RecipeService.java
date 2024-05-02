package javau9.ca.db.abc.finalprojectalternativehf.Service;

import javau9.ca.db.abc.finalprojectalternativehf.DTO.RecipeDTO;
import javau9.ca.db.abc.finalprojectalternativehf.Models.Recipe;

import java.util.List;

public interface RecipeService {


    Recipe updateRecipe(Long id, Recipe recipe);

    List<RecipeDTO> getAllRecipes();


    void deleteRecipeById(Long recipeId);

    RecipeDTO createRecipe(RecipeDTO recipeDTO);

    RecipeDTO getRecipeById(Long recipeId);

    RecipeDTO addProductToRecipe(Long recipeId, Long productId);

    RecipeDTO convertToDTO(Recipe recipe);

}


