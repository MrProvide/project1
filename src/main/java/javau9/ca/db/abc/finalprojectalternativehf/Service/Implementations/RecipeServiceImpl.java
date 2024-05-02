package javau9.ca.db.abc.finalprojectalternativehf.Service.Implementations;

import jakarta.transaction.Transactional;
import javau9.ca.db.abc.finalprojectalternativehf.DTO.RecipeDTO;
import javau9.ca.db.abc.finalprojectalternativehf.Models.Product;
import javau9.ca.db.abc.finalprojectalternativehf.Models.Recipe;
import javau9.ca.db.abc.finalprojectalternativehf.Repository.ProductRepository;
import javau9.ca.db.abc.finalprojectalternativehf.Repository.RecipeRepository;
import javau9.ca.db.abc.finalprojectalternativehf.Service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final ProductRepository productRepository;

    @Autowired
    public RecipeServiceImpl(RecipeRepository recipeRepository, ProductRepository productRepository) {
        this.recipeRepository = recipeRepository;
        this.productRepository = productRepository;
    }


    @Override
    public Recipe updateRecipe(Long id, Recipe recipe) {
        return null;
    }

    @Override
    public List<RecipeDTO> getAllRecipes() {
        List<Recipe> recipes = recipeRepository.findAll();
        return recipes.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteRecipeById(Long recipeId) {
        recipeRepository.deleteById(recipeId);

    }


    @Override
    @Transactional
    public RecipeDTO createRecipe(RecipeDTO recipeDTO) {
        Recipe recipe = new Recipe();
        recipe.setName(recipeDTO.getName());
        recipe.setDescription(recipeDTO.getDescription());

        List<Product> products = new ArrayList<>();
        if (recipeDTO.getProductIds() != null) {
            for (Long productId : recipeDTO.getProductIds()) {
                Product product = productRepository.findById(productId).orElse(null);
                if (product != null) {
                    products.add(product);
                }
            }
        }
        recipe.setProducts(products);

        recipe = recipeRepository.save(recipe);

        RecipeDTO createdRecipeDTO = new RecipeDTO();
        createdRecipeDTO.setId(recipe.getId());
        createdRecipeDTO.setName(recipe.getName());
        createdRecipeDTO.setDescription(recipe.getDescription());


        return createdRecipeDTO;
    }

    @Override
    public RecipeDTO getRecipeById(Long recipeId) {
        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new NoSuchElementException("Recipe not found with ID: " + recipeId));
        return convertToDTO(recipe);
    }

    @Override
    @Transactional
    public RecipeDTO addProductToRecipe(Long recipeId, Long productId) {
        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new NoSuchElementException("Recipe not found with ID: " + recipeId));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NoSuchElementException("Product not found with ID: " + productId));
        recipe.addProduct(product);
        Recipe savedRecipe = recipeRepository.save(recipe);
        return convertToDTO(savedRecipe);
    }

    @Override
    public RecipeDTO convertToDTO(Recipe recipe) {
        RecipeDTO recipeDTO = new RecipeDTO();
        recipeDTO.setId(recipe.getId());
        recipeDTO.setName(recipe.getName());
        recipeDTO.setDescription(recipe.getDescription());
        recipeDTO.setProductIds(recipe.getProducts().stream()
                .map(Product::getId)
                .collect(Collectors.toList()));
        return recipeDTO;
    }
}





