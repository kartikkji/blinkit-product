package blinket.com.product.dto.requestDto;


import lombok.Builder;
import lombok.Data;
import org.hibernate.Internal;

public class ImageRequestDto {

        private String imageUrl;

        private Boolean isPrimary;


        public String getImageUrl() {
                return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
                this.imageUrl = imageUrl;
        }


        public Boolean getPrimary() {return isPrimary;}

        public void setPrimary(Boolean primary) {isPrimary = primary;}
}
