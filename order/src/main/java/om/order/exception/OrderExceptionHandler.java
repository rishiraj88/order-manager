package om.order.exception;

import om.order.svc.InventoryShortOfStockException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class OrderExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({InventoryShortOfStockException.class})
    public ResponseEntity<Object> handleShortOfStockException(Exception ex, WebRequest request) {
        return new ResponseEntity<Object>(
                "The product has already been sold out. Please try ordering with smaller quantities later."
                , new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
