package blinket.com.product.controller;


import blinket.com.product.dto.requestDto.ImageRequestDto;
import blinket.com.product.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/images")
public class ImageController {

    @Autowired
    private  ImageService imageService;


    @PostMapping("/add")
     ResponseEntity<?> addImages(@RequestParam Integer id, @RequestParam Integer userId ,@RequestBody ImageRequestDto images){
        return imageService.addImage(images,id, userId);
    }


}
