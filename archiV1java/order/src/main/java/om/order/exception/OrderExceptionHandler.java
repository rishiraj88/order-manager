package om.order.exception;

import om.order.service.InventoryShortOfStockException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
/* Optionally 'extends ResponseEntityExceptionHandler' may be added to this class declaration. */
public class OrderExceptionHandler {

    @ExceptionHandler({InventoryShortOfStockException.class})
    public ResponseEntity<ErrorDetails> handleShortOfStockException(InventoryShortOfStockException ex, WebRequest request) {
        var errorDetails = new ErrorDetails(LocalDateTime.now()
                ,ex.getMessage(),request.getDescription(false)
                ,"ORD__OUT_OF_STOCK"
                );

        return new ResponseEntity<ErrorDetails>(errorDetails
                ,  HttpStatus.PRECONDITION_FAILED);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ErrorDetails> handleOrderExceptions(Exception ex, WebRequest request){
        var errorDetails = new ErrorDetails(LocalDateTime.now()
                ,ex.getMessage(),request.getDescription(false)
                ,"ORD__GENERIC_ERROR");

        return new ResponseEntity<ErrorDetails>(errorDetails,HttpStatus.NOT_IMPLEMENTED);
    }


}
