package om.product.controller

import om.product.dto.ProductReq
import om.product.dto.ProductResp
import om.product.service.ProductService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder

@RestController
@RequestMapping("/api/v2/products")
class ProductController(val productService: ProductService) {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getAllProducts(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "20") size: Int,
        @RequestParam(required = false) sortBy: String?
    ): ResponseEntity<Iterable<ProductResp>> {
        return if (null == sortBy) ResponseEntity.ok(productService.getAllProducts(page, size)) else ResponseEntity.ok(
            productService.getAllProducts(sortBy)
        )
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    fun addProduct(
        @Validated @RequestBody productReq: ProductReq,
        uriBuilder: UriComponentsBuilder
    ): ResponseEntity<ProductResp> {
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

/** HTTP Responses used frequently
 * 200 OK                       success
 * 201 Created                  resource created
 * 204 No Content               success and no content to return
 * 400 Bad Request              invalid client input received
 * 401 Unauthorized             auth required
 * 403 Forbidden                client principal is authenticated but not authorized for the operation
 * 404 Not Found                resource not found
 * 500 Internal Server Error    any of server-side errors
 */
