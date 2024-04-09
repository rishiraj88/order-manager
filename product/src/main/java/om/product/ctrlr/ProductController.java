package om.product.ctrlr;

import lombok.RequiredArgsConstructor;
import om.product.dto.ProductReq;
import om.product.dto.ProductResp;
import om.product.svc.IProductService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/api/products")
public class ProductController {
    private final IProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addProduct(@RequestBody ProductReq productReq) {
        productService.addProduct(productReq);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResp> getAllProducts() {
        return productService.getAllProducts();
    }
}
