package blinket.com.product.service;


import blinket.com.product.dto.requestDto.ProductRequestDto;
import blinket.com.product.dto.responseDto.ProductResponseDto;
import blinket.com.product.entity.Image;
import blinket.com.product.enums.ProductCategory;
import blinket.com.product.exception.productException.ProductNotFoundException;
import blinket.com.product.repo.ProductRepository;
import blinket.com.product.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Lazy
    @Autowired
    private  ImageService imageService;

    @Autowired
    private IdentityServiceClient identityServiceClient;

    public ResponseEntity<?> saveProduct(ProductRequestDto product , Integer id){

        try{
            Product product1 =  productRepository.save(DtoToProduct(product,id));

            List<String> imageList = imageService.productImageListToImage(product.getImageList(), product1);

            return new ResponseEntity<>("product created success fully with id " + product1.getId(), HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>("Invalid product details" , HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> getProductDtoById(Integer id) {
            Product product = productRepository.findById(id)
                    .orElseThrow(() -> new ProductNotFoundException("Invalid ID: Product not found with id "+ id));

            return ResponseEntity.ok(productResponseDtoList(product));

    }

    public Optional<Product> getProductById(Integer id) {
        Optional<Product> product = productRepository.findById(id);
        return  product;

    }

    public ResponseEntity<?> getProductByName(String name){

        Product product = productRepository.findByName(name);

        if (product != null) {
            return ResponseEntity.ok(productResponseDtoList(product));
        } else {
            throw new ProductNotFoundException("Invalid Name: Product not found with name : "+ name);
        }
    }

    public ResponseEntity<?> getProductByCategory(String category, Integer page , Integer size) {
        try {

            Pageable pageable = PageRequest.of(page,size);
            ProductCategory productCategory = ProductCategory.valueOf(category.toUpperCase());

            Page<Product> product = productRepository.findByCategory(productCategory,pageable);

            if (!product.isEmpty()) {
                return ResponseEntity.ok(productResponseDtoList(product));
            } else {
                throw new ProductNotFoundException("No products available with " + category + " category");
            }

        } catch (ProductNotFoundException e) {
            // Handle custom exception
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


    public ResponseEntity<?> deleteById(Integer id){


        Optional<Product> product = productRepository.findById(id);

        if(product.isPresent()){
            productRepository.deleteById(id);
            return ResponseEntity.ok("Product deleted successfully");
        }else {
            throw new ProductNotFoundException("Invalid ID: Product not found with id : "+ id);
        }

    }

    public Boolean checkProductById(Integer id){
        return productRepository.existsById(id);

    }

    public ResponseEntity<?> updateProductById(Integer id, Map<String, Object> update) {
        try {
            Optional<Product> productOpt = productRepository.findById(id);

            if (productOpt.isEmpty()) {
                throw new ProductNotFoundException("Invalid ID: Product not found with id : "+ id);
               // return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid ID: Product not found");
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
            return ResponseEntity.ok(productResponseDtoList(updatedProduct));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Field or Request Data");
        }
    }

    public ResponseEntity<List<ProductResponseDto>> getAllProductById(Integer page, Integer size){


        Pageable pageable = PageRequest.of(page, size);
            Page<Product> product =  productRepository.findAll(pageable);

            if(product.isEmpty()){
                throw new ProductNotFoundException("PRODUCTS_NOT_FOUND");
            }
            return ResponseEntity.ok(productResponseDtoList(product));

    }

//    public Product DtoToProduct(ProductRequestDto productRequestDto){
//        return ProductMapper.INSTANCE.PRODUCT(productRequestDto);
//    }
//
//    public ProductResponseDto ProductToDto(Product product){
//        return ProductMapper.INSTANCE.PRODUCT_RESPONSE_DTO(product);
//    }
//
//    public List<ProductResponseDto> productResponseDtoList(List<Product> productList){
//        return ProductMapper.INSTANCE.PRODUCT_RESPONSE_DTO_LIST(productList);
//    }

    public List<ProductResponseDto> productResponseDtoList(Page<Product> productList){

        List<ProductResponseDto> responseDtoList = new ArrayList<>();

        for(Product product : productList) {
            ProductResponseDto productResponseDto = new ProductResponseDto();
            productResponseDto.setId(product.getId());
            productResponseDto.setCategory(product.getCategory().toString());
            productResponseDto.setName(product.getName());
            productResponseDto.setDescription(product.getDescription());
            productResponseDto.setIs_active(product.getIs_active());
            productResponseDto.setCreateBy(product.getCreateBy());
            productResponseDto.setPrice(1000);
            productResponseDto.setStock(50);
            List<String> imageUrls = new ArrayList<>();
            for (Image image : product.getImages()) {
                imageUrls.add(image.getImageUrl()); // Extract and add the URL
            }
            productResponseDto.setImageList(imageUrls);

            responseDtoList.add(productResponseDto);


        }



        return responseDtoList;
    }

    public Product DtoToProduct(ProductRequestDto productDTO, Integer id) {
       Product product = new Product();
       product.setCategory(ProductCategory.valueOf(productDTO.getCategory().toUpperCase()));
       product.setName(productDTO.getName());
       product.setDescription(productDTO.getDescription());
       product.setCreateAt(LocalDateTime.now());
       product.setCreateBy(identityServiceClient.getAdminNameById(id));
        if(productDTO.getIs_active() != null){
           product.setIs_active(productDTO.getIs_active());
        }


        return product;


    }

    public ProductResponseDto productResponseDtoList(Product product){
        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setId(product.getId());
        productResponseDto.setCategory(product.getCategory().toString());
        productResponseDto.setName(product.getName());
        productResponseDto.setDescription(product.getDescription());
        productResponseDto.setIs_active(product.getIs_active());
        productResponseDto.setCreateBy(product.getCreateBy());
        productResponseDto.setPrice(1000);
        List<String> imageUrls = new ArrayList<>();
        for (Image image : product.getImages()) {
            imageUrls.add(image.getImageUrl()); // Extract and add the URL
        }
        productResponseDto.setImageList(imageUrls);


        return productResponseDto;


    }

}
