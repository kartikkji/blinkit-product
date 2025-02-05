package blinket.com.product.dto.requestDto;

import blinket.com.product.enums.ProductCategory;

public class ProductRequestDto {

    private String name;
    private ProductCategory category;
    private String description;
    private Boolean is_active;

    public Boolean getIs_active() {return is_active;}

    public void setIs_active(Boolean is_active) {this.is_active = is_active;}

    public String getDescription() {return description;}

    public void setDescription(String description) {this.description = description;}

    public ProductCategory getCategory() {return category;}

    public void setCategory(ProductCategory category) {this.category = category;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}
}
