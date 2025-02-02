package blinket.com.product.controller;


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
    public ResponseEntity<String> createNewProduct(@RequestBody Product product){

        Boolean success = productService.saveProduct(product);

        if(success){
            return new ResponseEntity<>("product created success fully" , HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>("Invalid product details" , HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getProductBYId(@PathVariable Integer id){
        ResponseEntity<?> product = productService.getProductById(id);

        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
    }

    @GetMapping("/get/name")
    public ResponseEntity<?> getProductBYName(@RequestParam String name){
        ResponseEntity<?> product = productService.getProductByName(name);

        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
    }


    @GetMapping("/get/category/{category}")
    public ResponseEntity<?> getProductBYCategory(@PathVariable String category){

       return productService.getProductByCategory((category));
    }


//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<?> deleteBYId(@PathVariable Integer id){
//        Boolean isDelete = productService.deleteById(id);
//
//        if(isDelete){
//            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Product deleted successfully");
//        }else{
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found with id: " + id);
//        }
//    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Integer id, @RequestBody Map<String, Object> update) {
        return productService.updateProductById(id, update);
    }


}
