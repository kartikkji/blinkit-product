package blinket.com.product.mapper;


import blinket.com.product.dto.requestDto.ImageRequestDto;
import blinket.com.product.dto.responseDto.ImageResponseDto;
import blinket.com.product.entity.ProductImage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ImageMapper {

    ImageMapper INSTANCE = Mappers.getMapper(ImageMapper.class);

    List<ImageResponseDto> IMAGE_RESPONSE_DTO(List<ProductImage> image);

    @Mapping(target = "createdAt", expression = "java(LocalDateTime.now())")
    ProductImage PRODUCT_IMAGE(ImageRequestDto imageResponseDto);
}

