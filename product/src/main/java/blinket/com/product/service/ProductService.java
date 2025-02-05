package blinket.com.product.service;


import blinket.com.product.dto.requestDto.ProductRequestDto;
import blinket.com.product.dto.responseDto.ProductResponseDto;
import blinket.com.product.enums.ProductCategory;
import blinket.com.product.mapper.ProductMapper;
import blinket.com.product.repo.ProductRepository;
import blinket.com.product.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductMapper productMapper;

    public ResponseEntity<?> saveProduct(ProductRequestDto product){

        try{
            productRepository.save(DtoToProduct(product));
            return new ResponseEntity<>("product created success fully" , HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>("Invalid product details" , HttpStatus.BAD_REQUEST);
        }

    }

    public ResponseEntity<?> getProductById(Integer id){

        Optional<Product> product = productRepository.findById(id);

        if (product.isPresent()) {
            return ResponseEntity.ok(ProductToDto(product.get()));
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

    public ResponseEntity<?> getProductByCategory(String category){
        try {

            ProductCategory productCategory = ProductCategory.valueOf(category.toUpperCase());

            List<Product> product = productRepository.findByCategory(productCategory);

            if (!product.isEmpty()) {
                return ResponseEntity.ok(product);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No products available in this category");
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid Category: Product not found");
        }
    }


    public ResponseEntity<?> deleteById(Integer id){
        Optional<Product> product = productRepository.findById(id);

        if(product.isPresent()){
            productRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Product deleted successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found with id: " + id);
    }

    public ResponseEntity<?> updateProductById(Integer id, Map<String, Object> update) {
        try {
            Optional<Product> productOpt = productRepository.findById(id);

            if (productOpt.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid ID: Product not found");
            }

            Product product = productOpt.get();

            update.forEach((key, value) -> {
                Field field = ReflectionUtils.findField(Product.class, key);
                if (field != null) {
                    field.setAccessible(true);
                    ReflectionUtils.setField(field, product, value);
                }
            });

            Product updatedProduct = productRepository.save(product);
            return ResponseEntity.ok(updatedProduct);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Field or Request Data");
        }
    }

    public Product DtoToProduct(ProductRequestDto productRequestDto){
        return ProductMapper.INSTANCE.DtoToEntity(productRequestDto);
    }

    public ProductResponseDto ProductToDto(Product product){
        return ProductMapper.INSTANCE.EntityToDto(product);
    }
}
