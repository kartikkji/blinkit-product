package blinket.com.product.service;


import blinket.com.product.dto.requestDto.ImageRequestDto;
import blinket.com.product.dto.responseDto.ImageResponseDto;
import blinket.com.product.entity.Product;
import blinket.com.product.entity.Image;
import blinket.com.product.repo.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ImageService {
    
    @Autowired
    private ImageRepository imageRepository;
    
    
    public ResponseEntity<?> addImage(ImageRequestDto image){
        try{

            Image productImage = imageRepository.save(ImageDtoTpProduct(image));
            return ResponseEntity.ok(productImage);

        }catch(Exception e){

            return new ResponseEntity<>("INVALID_IMAGE_DETAILS", HttpStatus.BAD_REQUEST);
        }
    }


//    public ProductImage ImageDtoTpProduct(ImageRequestDto imageRequestDto){
//        return ImageMapper.INSTANCE.PRODUCT_IMAGE(imageRequestDto);
//    }

        public Image ImageDtoTpProduct(ImageRequestDto imageRequestDto){
        Image image = new Image();
        image.setImageUrl(imageRequestDto.getImageUrl());
        image.setCreateBy("Kartik");
        image.setUpdateBy("Kartik");
        image.setPrimary(imageRequestDto.getPrimary());
        image.setCreatedAt(LocalDateTime.now());
        image.setUpdateAt(LocalDateTime.now());

        return image;
    }

    List<Image> findByProductId(Integer id){

        //List<ProductImage> productImageList =  imageRepository.findByProductId(id);

        return imageRepository.findByProductId(id);

       // return imageProductListToimageResponseDtoList(productImageList);

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
            image.setCreateBy("Kartik");
            image.setUpdateBy("Kartik");
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
