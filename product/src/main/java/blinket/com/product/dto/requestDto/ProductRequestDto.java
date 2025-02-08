package blinket.com.product.dto.requestDto;

import blinket.com.product.enums.ProductCategory;
import lombok.Builder;

@Builder
public class ProductRequestDto {

    private String name;
    private String category;
    private String description;
    private Boolean is_active;
    private String createBy;


    public String getName() {
        return name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIs_active() {
        return is_active;
    }

    public void setIs_active(Boolean is_active) {
        this.is_active = is_active;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }
}
