package om.product.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ProductController {

    @GetMapping
    fun getAllProducts():String {
return "ps"
    }
}