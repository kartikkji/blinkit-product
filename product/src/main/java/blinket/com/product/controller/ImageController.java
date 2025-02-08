package blinket.com.product.controller;


import blinket.com.product.dto.requestDto.ImageRequestDto;
import blinket.com.product.entity.ProductImage;
import blinket.com.product.repo.ImageRepository;
import blinket.com.product.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/images")
public class ImageController {

    @Autowired
    private  ImageService imageService;


    @PostMapping("/add")
     ResponseEntity<?> addImages(@RequestBody ImageRequestDto images){
        return imageService.addImage(images);

    }


}
