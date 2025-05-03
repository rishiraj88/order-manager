package om.inventory.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionHandler {

    @ExceptionHandler
    fun handleStockLevelException(exception: StockLevelException): ResponseEntity<Map<String, String>> {
        val response = HashMap<String, String>()
        response.put("EXCEPTION", exception.message ?: "")
        return ResponseEntity.status(HttpStatus.OK).body(response)
    }

}
