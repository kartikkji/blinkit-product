package blinket.com.product.controller;


import blinket.com.product.dto.requestDto.ProductRequestDto;
import blinket.com.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/api/v1/products")
public class ProductController {



    @Autowired
    ProductService productService;

    @PostMapping("/create/{id}")
    public ResponseEntity<?> createNewProduct(@PathVariable Integer id , @RequestBody ProductRequestDto product){
        return productService.saveProduct(product , id);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getProductBYId(@PathVariable Integer id){
        return  productService.getProductDtoById(id);
    }

    @GetMapping("/get/name")
    public ResponseEntity<?> getProductBYName(@RequestParam String name){
        return productService.getProductByName(name);
    }

    @GetMapping("/get/category/{category}")
    public ResponseEntity<?> getProductBYCategory(
            @PathVariable String category,
            @RequestParam(defaultValue = "0") Integer page){
       return productService.getProductByCategory(category, page, 5);
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
    public ResponseEntity<?> getAllById(
            @RequestParam(defaultValue = "0") Integer page){
        return productService.getAllProductById( page, 10);
    }

}
