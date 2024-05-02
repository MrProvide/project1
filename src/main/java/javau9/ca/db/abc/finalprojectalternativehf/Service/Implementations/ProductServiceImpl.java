package javau9.ca.db.abc.finalprojectalternativehf.Service.Implementations;

import javau9.ca.db.abc.finalprojectalternativehf.Models.Product;
import javau9.ca.db.abc.finalprojectalternativehf.Repository.ProductRepository;
import javau9.ca.db.abc.finalprojectalternativehf.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {


    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
