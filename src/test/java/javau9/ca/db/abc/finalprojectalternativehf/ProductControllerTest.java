package javau9.ca.db.abc.finalprojectalternativehf;

import javau9.ca.db.abc.finalprojectalternativehf.Controllers.ProductController;
import javau9.ca.db.abc.finalprojectalternativehf.Models.Product;
import javau9.ca.db.abc.finalprojectalternativehf.Service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Test
    public void testAddProduct() throws Exception {

        String productJson = "{\"name\":\"Test Product\",\"description\":\"Sample description\",\"category\":\"Sample category\"}";


        Product product = new Product();
        product.setId(1L);
        product.setName("Test Product");
        product.setDescription("Sample description");
        product.setCategory("Sample category");


        Mockito.when(productService.saveProduct(Mockito.any(Product.class))).thenReturn(product);


        mockMvc.perform(post("/api/products/addProduct")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Test Product")))
                .andExpect(jsonPath("$.description", is("Sample description")))
                .andExpect(jsonPath("$.category", is("Sample category")));
    }

}
