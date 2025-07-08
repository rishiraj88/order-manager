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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api/v1/products") // one scheme of API versioning is here. Another scheme is in Order app.
public class ProductController {
    private final IProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // Smarter way to add <Status Code> to Response
    public ProductResp addProduct(@RequestBody ProductReq productReq) {
        //Developer Note: Uncomment this code fragment to test timeout and retry with Resilience4j tooling
        /*
        See the following property in Gateway:
        # idle wait time before switching from 'open' to 'half-open'
        resilience4j.circuitbreaker.configs.default.waitDurationInOpenState
        */
        if (0 == new Random().nextInt(2)) {
            try {
                Thread.sleep(6000);
            } catch (InterruptedException e) {
                log.debug(e.getMessage());
                throw new RuntimeException(e);
            }
        }
        return productService.addProduct(productReq);
    }

    @GetMapping
    public ResponseEntity<List<ProductResp>> getAllProducts() {
        //Dev Tools: Uncomment this code fragment to test timeout and retry with Resilience4j tooling
        if (0 == new Random().nextInt(2)) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        // Traditional way to add <Status Code> to Response
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping
    public ResponseEntity<ProductResp> getAnyOneProductByName(@RequestParam String name) {
        //Dev Tools: Uncomment this code fragment to test timeout and retry with Resilience4j tooling
        if (0 == new Random().nextInt(2)) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        // Traditional way to add <Status Code> to Response
        return ResponseEntity.ok(productService.getAnyOneProductByName(name));
    }


    @PutMapping
    public ResponseEntity<ProductResp> updateProductsFoundByName(@RequestParam ProductReq productReq) {
        //Dev Tools: Uncomment this code fragment to test timeout and retry with Resilience4j tooling
        if (0 == new Random().nextInt(2)) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        // Traditional way to add <Status Code> to Response
        return ResponseEntity.ok(productService.udpatePriceOfProductsFoundBySku(productReq));
    }
}
