package blinket.com.product.dto.responseDto;

import blinket.com.product.enums.ProductCategory;
import lombok.Data;

@Data
public class ProductResponseDto {

    private Integer id;
    private String name;
    private ProductCategory category;
    private Integer price;
    private String description;
    private Boolean is_active;
    private Integer stock;


}
