package om.product.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
/* Optionally 'extends ResponseEntityExceptionHandler' may be added to this class declaration. */
public class ProductExceptionHandler {

    @ExceptionHandler({ProductNotFoundException.class})
    public ResponseEntity<ErrorDetails> handleProductNotFoundException(ProductNotFoundException ex, WebRequest request) {
        var errorDetails = new ErrorDetails(LocalDateTime.now()
                ,ex.getMessage(),request.getDescription(false)
                ,"PROD__PRODUCT_NOT_FOUND"
                );

        return new ResponseEntity<ErrorDetails>(errorDetails
                ,  HttpStatus.PRECONDITION_FAILED);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ErrorDetails> handleProductExceptions(Exception ex, WebRequest request){
        var errorDetails = new ErrorDetails(LocalDateTime.now()
                ,ex.getMessage(),request.getDescription(false)
                ,"PROD__GENERIC_ERROR");

        return new ResponseEntity<ErrorDetails>(errorDetails,HttpStatus.NOT_IMPLEMENTED);
    }
}
