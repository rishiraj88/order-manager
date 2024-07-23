package om.product.ctrlr;

import lombok.RequiredArgsConstructor;
import om.product.dto.ProductReq;
import om.product.dto.ProductResp;
import om.product.svc.IProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/products") //TODO add API versioning
public class ProductController {
    private final IProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResp addProduct(@RequestBody ProductReq productReq) {
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return productService.addProduct(productReq);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResp> getAllProducts() {
/* uncomment this code fragment to test timeout and retry with Resilience4j       try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }*/
        return productService.getAllProducts();
    }
}
