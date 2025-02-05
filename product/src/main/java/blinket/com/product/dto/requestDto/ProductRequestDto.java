package blinket.com.product.dto.requestDto;

import blinket.com.product.enums.ProductCategory;
import lombok.Data;

@Data
public class ProductRequestDto {

    private String name;
    private ProductCategory category;
    private String description;
    private Boolean is_active;

}
