package blinket.com.product.service;


import blinket.com.product.dto.requestDto.ImageRequestDto;
import blinket.com.product.dto.responseDto.ImageResponseDto;
import blinket.com.product.entity.Product;
import blinket.com.product.entity.Image;
import blinket.com.product.exception.productException.ProductNotFoundException;
import blinket.com.product.repo.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ImageService {
    
    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private IdentityServiceClient identityServiceClient;


    @Autowired
    private ProductService productService;
    
    
    public ResponseEntity<?> addImage(ImageRequestDto image, Integer productId, Integer userId){
        try{

            Optional<Product> product = productService.getProductById(productId);

            if(!product.isPresent()){
                throw new ProductNotFoundException("Product Not Found By "+ productId + " this ID");
            }

            List<Image> productImage = ImageDtoTpProduct(image, userId, product.get());

            imageRepository.saveAll(productImage);

             return new ResponseEntity<>("IMAGES_ADD_SUCCESSFULL" ,HttpStatus.ACCEPTED);

        }catch(Exception e){

            return new ResponseEntity<>("INVALID_IMAGE_DETAILS", HttpStatus.BAD_REQUEST);
        }
    }


//    public ProductImage ImageDtoTpProduct(ImageRequestDto imageRequestDto){
//        return ImageMapper.INSTANCE.PRODUCT_IMAGE(imageRequestDto);
//    }

        public List<Image> ImageDtoTpProduct(ImageRequestDto imageRequestDto, Integer useerId, Product product){
        List<Image> images = new ArrayList<>();

        for(String Url : imageRequestDto.getImageUrl()) {
            Image image = new Image();
            image.setImageUrl(Url);
            image.setCreateBy(identityServiceClient.getAdminNameById(useerId));
            image.setUpdateBy(identityServiceClient.getAdminNameById(useerId));
            image.setPrimary(imageRequestDto.getPrimary());
            image.setCreatedAt(LocalDateTime.now());
            image.setUpdateAt(LocalDateTime.now());
            image.setProduct(product);
            images.add(image);

        }
            return images;
        }



    List<Image> findByProductId(Integer id){

        //List<ProductImage> productImageList =  imageRepository.findByProductId(id);

        return imageRepository.findByProductId(id);

       // return imageProductListToImageResponseDtoList(productImageList);

    }

    public List<ImageResponseDto>  imageProductListToimageResponseDtoList(List<Image> imageList){
        List<ImageResponseDto> imageResponseDtoList = new ArrayList<>();
        for(Image image : imageList){
            ImageResponseDto imageResponseDto = new ImageResponseDto();
            imageResponseDto.setImageUrl(image.getImageUrl());
            imageResponseDto.setCreateBy(image.getCreateBy());
            imageResponseDto.setUpdateBy(image.getUpdateBy());
            imageResponseDto.setPrimary(image.getPrimary());
            imageResponseDto.setCreatedAt(image.getCreatedAt());
            imageResponseDto.setUpdateAt(image.getUpdateAt());
            imageResponseDtoList.add(imageResponseDto);
        }

        return imageResponseDtoList;
    }


    public List<String> productImageListToImage(List<String> imageList, Product product){

        List<String> imageUrlList = new ArrayList<>();

        for(String Url : imageList) {

            Image image = new Image();
            image.setImageUrl(Url);
            image.setCreateBy(product.getCreateBy());
            image.setUpdateBy(product.getCreateBy());
            image.setPrimary(true);
            image.setProduct(product);
            image.setCreatedAt(LocalDateTime.now());
            image.setUpdateAt(LocalDateTime.now());

            imageUrlList.add(Url);
            imageRepository.save(image);
        }

        return  imageUrlList;
    }


}
