package blinket.com.product.controller;


import blinket.com.product.enums.ProductCategory;
import blinket.com.product.service.ProductService;
import blinket.com.product.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

        try {

            ProductCategory categoryEnum = ProductCategory.valueOf(category.toUpperCase());

            ResponseEntity<?> product = productService.getProductByCategory(categoryEnum);

            if (product != null) {
                return ResponseEntity.ok(product);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No products found for the given category");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid category: " + category);
        }
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteBYId(@PathVariable Integer id){
        Boolean isDelete = productService.deleteById(id);

        if(isDelete){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Product deleted successfully");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found with id: " + id);
        }
    }
}
