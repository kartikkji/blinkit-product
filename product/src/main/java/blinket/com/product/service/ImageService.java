package blinket.com.product.service;


import blinket.com.product.dto.requestDto.ImageRequestDto;
import blinket.com.product.dto.responseDto.ImageResponseDto;
import blinket.com.product.entity.ProductImage;
import blinket.com.product.mapper.ImageMapper;
import blinket.com.product.repo.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

@Service
public class ImageService {
    
    @Autowired
    private ImageRepository imageRepository;
    
    
    public ResponseEntity<?> addImage(ImageRequestDto image){
        try{

            ProductImage productImage = imageRepository.save(ImageDtoTpProduct(image));
            return ResponseEntity.ok(productImage);

        }catch(Exception e){

            return new ResponseEntity<>("INVALID_IMAGE_DETAILS", HttpStatus.BAD_REQUEST);
        }
    }


//    public ProductImage ImageDtoTpProduct(ImageRequestDto imageRequestDto){
//        return ImageMapper.INSTANCE.PRODUCT_IMAGE(imageRequestDto);
//    }

        public ProductImage ImageDtoTpProduct(ImageRequestDto imageRequestDto){
        ProductImage productImage = new ProductImage();
        productImage.setImageUrl(imageRequestDto.getImageUrl());
        productImage.setProductId(imageRequestDto.getProductId());
        productImage.setCreateBy(imageRequestDto.getCreateBy());
        productImage.setUpdateBy(imageRequestDto.getUpdateBy());
        productImage.setPrimary(imageRequestDto.getPrimary());
        productImage.setCreatedAt(LocalDateTime.now());
        productImage.setUpdateAt(LocalDateTime.now());

        return  productImage;
    }

    List<ProductImage> findByProductId(Integer id){

        //List<ProductImage> productImageList =  imageRepository.findByProductId(id);

        return imageRepository.findByProductId(id);

       // return imageProductListToimageResponseDtoList(productImageList);

    }

    public List<ImageResponseDto>  imageProductListToimageResponseDtoList(List<ProductImage> productImageList){
        List<ImageResponseDto> imageResponseDtoList = new ArrayList<>();
        for(ProductImage productImage : productImageList){
            ImageResponseDto imageResponseDto = new ImageResponseDto();
            imageResponseDto.setImageUrl(productImage.getImageUrl());
            imageResponseDto.setProductId(productImage.getProductId());
            imageResponseDto.setCreateBy(productImage.getCreateBy());
            imageResponseDto.setUpdateBy(productImage.getUpdateBy());
            imageResponseDto.setPrimary(productImage.getPrimary());
            imageResponseDto.setCreatedAt(productImage.getCreatedAt());
            imageResponseDto.setUpdateAt(productImage.getUpdateAt());
            imageResponseDtoList.add(imageResponseDto);
        }

        return imageResponseDtoList;


    }


}
