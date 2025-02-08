package blinket.com.product.exception.productException;



public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException() {
        super("Product Not Found Exception");
    }

    public ProductNotFoundException(String message) {
        super(message);
    }


}
