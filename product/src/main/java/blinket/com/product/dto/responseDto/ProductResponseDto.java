package blinket.com.product.dto.responseDto;

import blinket.com.product.enums.ProductCategory;


public class ProductResponseDto {

    private Integer id;
    private String name;
    private ProductCategory category;
    private Integer price;
    private String description;
    private Boolean is_active;
    private Integer stock;

    public Integer getStock() {return stock;}

    public void setStock(Integer stock) {this.stock = stock;}

    public Boolean getIs_active() {return is_active;}

    public void setIs_active(Boolean is_active) {this.is_active = is_active;}

    public String getDescription() {return description;}

    public void setDescription(String description) {this.description = description;}

    public Integer getPrice() {return price;}

    public void setPrice(Integer price) {this.price = price;}

    public ProductCategory getCategory() {return category;}

    public void setCategory(ProductCategory category) {this.category = category;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public Integer getId() {return id;}

    public void setId(Integer id) {this.id = id;}
}
