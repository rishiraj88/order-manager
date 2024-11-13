package om.order.exception;

import om.order.svc.InventoryShortOfStockException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class OrderExceptionHandler /*extends ResponseEntityExceptionHandler*/ {

    @ExceptionHandler({InventoryShortOfStockException.class})
    public ResponseEntity<ErrorDetails> handleShortOfStockException(InventoryShortOfStockException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now()
                ,ex.getMessage(),request.getDescription(false)
                ,"ORD__OUT_OF_STOCK"
                );

        return new ResponseEntity<ErrorDetails>(errorDetails
                ,  HttpStatus.PRECONDITION_FAILED);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ErrorDetails> handleOrderExceptions(Exception ex, WebRequest request){
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now()
                ,ex.getMessage(),request.getDescription(false)
                ,"ORD__GENERIC_ERROR");

        return new ResponseEntity<ErrorDetails>(errorDetails,HttpStatus.NOT_IMPLEMENTED);
    }


}
