package blinket.com.product.dto.responseDto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ImageResponseDto {


    private Integer id;

    private Integer productId;

    private String imageUrl;

    private LocalDateTime createdAt;

    private String createBy;

    private LocalDateTime updateAt;

    private String updateBy;

    private Boolean isPrimary;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setPrimary(Boolean primary) {
        isPrimary = primary;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }
}
