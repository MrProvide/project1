package javau9.ca.db.abc.finalprojectalternativehf;

import javau9.ca.db.abc.finalprojectalternativehf.Controllers.RecipeController;
import javau9.ca.db.abc.finalprojectalternativehf.Models.Recipe;
import javau9.ca.db.abc.finalprojectalternativehf.Service.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class RecipeControllerTest {

    @Mock
    private RecipeService recipeService;

    @InjectMocks
    private RecipeController recipeController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void saveRecipe_shouldReturnCreatedStatus() {

        Recipe recipe = new Recipe();
        when(recipeService.saveRecipe(any())).thenReturn(recipe);


        ResponseEntity<Recipe> response = recipeController.saveRecipe(recipe);


        verify(recipeService, times(1)).saveRecipe(any());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void deleteRecipe_shouldReturnNoContent() {

        Long recipeId = 1L;


        ResponseEntity<Void> response = recipeController.deleteRecipe(recipeId);


        verify(recipeService, times(1)).deleteRecipe(recipeId);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void getRecipeById_shouldReturnRecipe() {

        Long recipeId = 1L;
        Recipe recipe = new Recipe();
        when(recipeService.getRecipeById(recipeId)).thenReturn(recipe);


        ResponseEntity<Recipe> response = recipeController.getRecipeById(recipeId);


        verify(recipeService, times(1)).getRecipeById(recipeId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(recipe, response.getBody());
    }
}
