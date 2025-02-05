package blinket.com.product.exception;

public class InvalidProductDetails extends RuntimeException {
    public InvalidProductDetails() {
        super("Invalid Product Details");
       // message("Product Not Found");
    }


}
