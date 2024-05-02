package javau9.ca.db.abc.finalprojectalternativehf;

import javau9.ca.db.abc.finalprojectalternativehf.Models.Product;
import javau9.ca.db.abc.finalprojectalternativehf.Models.Recipe;
import javau9.ca.db.abc.finalprojectalternativehf.Repository.ProductRepository;
import javau9.ca.db.abc.finalprojectalternativehf.Repository.RecipeRepository;
import javau9.ca.db.abc.finalprojectalternativehf.Service.Implementations.RecipeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
public class RecipeServiceImplTest {

    @Mock
    private RecipeRepository recipeRepository;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private RecipeServiceImpl recipeService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveRecipe() {

        Recipe recipe = new Recipe();
        recipe.setName("Test Recipe");
        recipe.setDescription("Test Description");

        Product product = new Product();
        product.setId(1L);
        List<Product> products = new ArrayList<>();
        products.add(product);
        recipe.setProducts(products);

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(recipeRepository.save(any(Recipe.class))).thenReturn(recipe);

        Recipe savedRecipe = recipeService.saveRecipe(recipe);
        assertNotNull(savedRecipe);
        assertEquals("Test Recipe", savedRecipe.getName());
        assertEquals("Test Description", savedRecipe.getDescription());
        assertEquals(1, savedRecipe.getProducts().size());
    }

    @Test
    public void testGetAllRecipes() {

        List<Recipe> recipes = new ArrayList<>();
        recipes.add(new Recipe());
        recipes.add(new Recipe());

        when(recipeRepository.findAll()).thenReturn(recipes);


        List<Recipe> retrievedRecipes = recipeService.getAllRecipes();

        assertNotNull(retrievedRecipes);
        assertEquals(2, retrievedRecipes.size());
    }

    @Test
    public void testGetRecipeById() {

        Recipe recipe = new Recipe();
        recipe.setId(1L);

        when(recipeRepository.findById(anyLong())).thenReturn(Optional.of(recipe));


        Recipe retrievedRecipe = recipeService.getRecipeById(1L);


        assertNotNull(retrievedRecipe);
        assertEquals(1L, retrievedRecipe.getId());
    }

    @Test
    public void testDeleteRecipe() {
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        recipe.setName("Test Recipe");

        when(recipeRepository.findById(1L)).thenReturn(Optional.of(recipe));

        recipeService.deleteRecipe(1L);
        verify(recipeRepository, times(1)).deleteById(1L);
    }
}


