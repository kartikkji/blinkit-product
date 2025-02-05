package blinket.com.product.mapper;

import blinket.com.product.dto.requestDto.ProductRequestDto;
import blinket.com.product.dto.responseDto.ProductResponseDto;
import blinket.com.product.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(target = "id", ignore = true)
    Product PRODUCT(ProductRequestDto requestDto);

    ProductResponseDto PRODUCT_RESPONSE_DTO(Product product);

    List<ProductResponseDto> PRODUCT_RESPONSE_DTO_LIST(List<Product> productList);
}
