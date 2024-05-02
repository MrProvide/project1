package javau9.ca.db.abc.finalprojectalternativehf.Controllers;

import javau9.ca.db.abc.finalprojectalternativehf.DTO.RecipeDTO;
import javau9.ca.db.abc.finalprojectalternativehf.Models.Recipe;
import javau9.ca.db.abc.finalprojectalternativehf.Service.RecipeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/recipes")
public class RecipeController {

    private final RecipeService recipeService;


    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }


    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/addRecipe")
    public ResponseEntity<RecipeDTO> createRecipe(@RequestBody RecipeDTO recipeDTO) {
        RecipeDTO createdRecipeDTO = recipeService.createRecipe(recipeDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRecipeDTO);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping("/{recipeId}/add-product/{productId}")
    public ResponseEntity<RecipeDTO> addProductToRecipe(@PathVariable Long recipeId, @PathVariable Long productId) {
        RecipeDTO updatedRecipeDTO = recipeService.addProductToRecipe(recipeId, productId);
        if (updatedRecipeDTO != null) {
            return ResponseEntity.ok(updatedRecipeDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/{recipeId}")
    public ResponseEntity<RecipeDTO> getRecipe(@PathVariable Long recipeId) {
        RecipeDTO recipeDTO = recipeService.getRecipeById(recipeId);
        if (recipeDTO != null) {
            return ResponseEntity.ok(recipeDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping("/{recipeId}")
    public ResponseEntity<Void> deleteRecipe(@PathVariable Long recipeId) {
        recipeService.deleteRecipeById(recipeId);
        return ResponseEntity.noContent().build();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping("/updateRecipe/{id}")
    public ResponseEntity<Recipe> updateRecipe(@PathVariable Long id, @RequestBody Recipe recipe) {
        Recipe updatedRecipe = recipeService.updateRecipe(id, recipe);
        return ResponseEntity.ok(updatedRecipe);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/{id}")
    public ResponseEntity<RecipeDTO> getRecipeById(@PathVariable Long id) {
        RecipeDTO recipe = recipeService.getRecipeById(id);
        return ResponseEntity.ok(recipe);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/getAllRecipes")
    public ResponseEntity<List<RecipeDTO>> getAllRecipes() {
        List<RecipeDTO> recipeDTOs = recipeService.getAllRecipes();
        return ResponseEntity.ok(recipeDTOs);
    }

}
