package service;

import dao.ProductDao;
import domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {
    @Autowired
    private ProductDao productDao;
    public List<Product> findAllProduct(){
        return productDao.allProduct();
    }
    public void addProduct(Product product){
        String id = UUID.randomUUID().toString();
        product.setId(id);
        productDao.addProduct(product);
    }
}
