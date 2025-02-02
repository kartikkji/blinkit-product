package blinket.com.product.service;


import blinket.com.product.enums.ProductCategory;
import blinket.com.product.repo.ProductRepository;
import blinket.com.product.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public Boolean saveProduct(Product product){

        try{
            productRepository.save(product);
            return true;
        } catch (Exception e){
            return false;
        }

    }

    public ResponseEntity<?> getProductById(Integer id){

        Optional<Product> product = productRepository.findById(id);

        if (product.isPresent()) {
            return ResponseEntity.ok(product.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid ID: Product not found");
        }
    }

    public ResponseEntity<?> getProductByName(String name){

        Product product = productRepository.findByName(name);

        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid Name: Product not found");
        }
    }

    public ResponseEntity<?> getProductByCategory(ProductCategory category){

        List<Product> product = productRepository.findByCategory(category);

        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid Name: Product not found");
        }
    }


    public Boolean deleteById(Integer id){
        Optional<Product> product = productRepository.findById(id);

        if(product.isPresent()){
            productRepository.deleteById(id);
            return true;
        }

        return false;
    }
}
