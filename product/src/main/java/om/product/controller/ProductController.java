package om.product.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import om.product.dto.ProductReq;
import om.product.dto.ProductResp;
import om.product.service.IProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api/v1/products") //TODO add API versioning
public class ProductController {
    private final IProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // Smarter way to add Status Code to Response
    public ProductResp addProduct(@RequestBody ProductReq productReq) {
        /*
        //Dev Tools: Uncomment this code fragment to test timeout and retry with Resilience4j tooling
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            log.debug(e.getMessage());
            throw new RuntimeException(e);
        }*/
        return productService.addProduct(productReq);
    }

    @GetMapping
    public ResponseEntity<List<ProductResp>> getAllProducts() {
    /* for development and debugging
    //Dev Tools: Uncomment this code fragment to test timeout and retry with Resilience4j tooling
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }*/
        // Traditional way to add Status Code Response
        return ResponseEntity.ok(productService.getAllProducts());
    }
}
