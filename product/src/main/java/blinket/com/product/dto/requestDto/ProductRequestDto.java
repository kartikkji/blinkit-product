package blinket.com.product.dto.requestDto;

import jakarta.validation.constraints.NotBlank;

import java.util.List;


public class ProductRequestDto {

    @NotBlank(message = "must be enter name")
    private String name;

    @NotBlank(message = "must be enter category")
    private String category;

    @NotBlank(message = "must be enter description")
    private String description;

    @NotBlank(message = "must be enter is_active")
    private Boolean is_active;

    @NotBlank(message = "must be enter imageList")
    private List<String> imageList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public List<String> getImageList() {
        return imageList;
    }

    public void setImageList(List<String> imageList) {
        this.imageList = imageList;
    }
}