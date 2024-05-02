package javau9.ca.db.abc.finalprojectalternativehf.Service;

import javau9.ca.db.abc.finalprojectalternativehf.Models.Product;

import java.util.List;

public interface ProductService {
    Product saveProduct(Product product);

    List<Product> getAllProducts();
}
