package javau9.ca.db.abc.finalprojectalternativehf;

import javau9.ca.db.abc.finalprojectalternativehf.Models.Product;
import javau9.ca.db.abc.finalprojectalternativehf.Repository.ProductRepository;
import javau9.ca.db.abc.finalprojectalternativehf.Service.Implementations.ProductServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


@RunWith(MockitoJUnitRunner.class)

public class ProductServiceTest {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    public void testSaveProduct() {

        Product product = new Product();
        product.setId(1L);
        product.setName("Test Product");
        product.setDescription("Sample description");
        product.setCategory("Sample category");


        Mockito.when(productRepository.save(Mockito.any(Product.class))).thenReturn(product);


        Product savedProduct = productService.saveProduct(product);


        assertNotNull(savedProduct);
        assertEquals("Test Product", savedProduct.getName());
        assertEquals("Sample description", savedProduct.getDescription());
        assertEquals("Sample category", savedProduct.getCategory());
    }
}

