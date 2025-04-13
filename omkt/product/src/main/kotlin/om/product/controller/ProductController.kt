package om.product.controller

import om.product.dto.ProductReq
import om.product.dto.ProductResp
import om.product.service.ProductService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController("/api/v2/products")
class ProductController(val productService: ProductService) {
    @GetMapping
    fun getAllProducts():Iterable<ProductResp> {
        return productService.getAllProducts()
    }

    @PostMapping()
    fun addProduct(@RequestBody productReq: ProductReq):ProductResp {
    return productService.addProduct(productReq);
    }
}

