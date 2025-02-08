package blinket.com.product.exception.globalHandler;


import blinket.com.product.exception.imageException.ImageNotFoundException;
import blinket.com.product.exception.imageException.InvalidImageDetailsException;
import blinket.com.product.exception.imageException.InvalidImageException;
import blinket.com.product.exception.productException.InvalidProductDetailsException;
import blinket.com.product.exception.productException.InvalidProductException;
import blinket.com.product.exception.productException.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidProductException.class)
    public ResponseEntity<String> handleInvalidProductException(InvalidProductException ex){
        return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<String> handleProductNotFoundException(ProductNotFoundException ex){
        return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidProductDetailsException.class)
    public ResponseEntity<String> handleInvalidProductDetailsException(InvalidProductDetailsException ex){
        return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
    }

    // Image Exception
    @ExceptionHandler(InvalidImageException.class)
    public ResponseEntity<String> handleInvalidImageException(InvalidImageException ex){
        return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ImageNotFoundException.class)
    public ResponseEntity<String> handleImageNotFoundException(ImageNotFoundException ex){
        return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidImageDetailsException.class)
    public ResponseEntity<String> handleInvalidImageDetailsException(InvalidImageDetailsException ex){
        return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
    }


}
