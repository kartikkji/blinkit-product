package blinket.com.product.dto.requestDto;


import lombok.Builder;
import lombok.Data;
import org.hibernate.Internal;

public class ImageRequestDto {

        private String imageUrl;

        private String createBy;

        private String updateBy;

        private Boolean isPrimary;

        private Integer productId;

        public Integer getProductId() {
                return productId;
        }

        public String getImageUrl() {
                return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
                this.imageUrl = imageUrl;
        }

        public String getUpdateBy() {
                return updateBy;
        }

        public void setUpdateBy(String updateBy) {
                this.updateBy = updateBy;
        }

        public String getCreateBy() { return createBy; }

        public void setCreateBy(String createBy) {this.createBy = createBy;}

        public Boolean getPrimary() {return isPrimary;}

        public void setPrimary(Boolean primary) {isPrimary = primary;}
}
