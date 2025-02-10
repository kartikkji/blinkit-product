package blinket.com.product.controller;


import blinket.com.product.dto.requestDto.ProductRequestDto;
import blinket.com.product.enums.ProductCategory;
import blinket.com.product.service.ProductService;
import blinket.com.product.entity.Product;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.apache.el.util.ReflectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.Map;

import static org.yaml.snakeyaml.tokens.Token.ID.Key;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {



    @Autowired
    ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<?> createNewProduct(@RequestBody ProductRequestDto product){
        return productService.saveProduct(product);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getProductBYId(@PathVariable Integer id){
        return  productService.getProductById(id);
    }

    @GetMapping("/get/name")
    public ResponseEntity<?> getProductBYName(@RequestParam String name){
        return productService.getProductByName(name);
    }

    @GetMapping("/get/category/{category}")
    public ResponseEntity<?> getProductBYCategory(@PathVariable String category){
       return productService.getProductByCategory((category));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteBYId(@PathVariable Integer id){
        return  productService.deleteById(id);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Integer id, @RequestBody Map<String, Object> update) {
        return productService.updateProductById(id, update);
    }

    @GetMapping("/get/all")
    public ResponseEntity<?> getAllById(){
        return productService.getAllProductById();
    }

}
