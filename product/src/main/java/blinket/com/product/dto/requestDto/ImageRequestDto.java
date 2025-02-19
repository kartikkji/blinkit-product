package blinket.com.product.dto.requestDto;


import lombok.Builder;
import lombok.Data;
import org.hibernate.Internal;

import java.util.List;

public class ImageRequestDto {

        private List<String> imageUrl;

        private Boolean isPrimary;

        public List<String> getImageUrl() {
                return imageUrl;
        }

        public void setImageUrl(List<String> imageUrl) {
                this.imageUrl = imageUrl;
        }

        public Boolean getPrimary() {
                return isPrimary;
        }

        public void setPrimary(Boolean primary) {
                isPrimary = primary;
        }
}
