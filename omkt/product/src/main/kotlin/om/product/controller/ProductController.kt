package om.product.controller

import om.product.dto.ProductReq
import om.product.dto.ProductResp
import om.product.service.ProductService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder

@RestController("/api/v2/products")
class ProductController(val productService: ProductService) {
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getAllProducts(): Iterable<ProductResp> {
        return productService.getAllProducts()
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    fun addProduct(@RequestBody productReq: ProductReq, uriBuilder: UriComponentsBuilder): ResponseEntity<ProductResp> {
        val newProduct = productService.addProduct(productReq)
        val location = uriBuilder.path("/api/v2/products/{id}")
            .buildAndExpand(newProduct.id)
            .toUri()
        return ResponseEntity.created(location).body(newProduct)
    }

    @GetMapping("{id}")
    fun getProduct(@PathVariable id: String): ResponseEntity<ProductResp> {
        val product = productService.getProduct(id)

        return if (null == product) ResponseEntity.notFound().build() else ResponseEntity.ok(product)
    }
}

