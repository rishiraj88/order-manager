package om.product.service

import om.product.dto.ProductResp

interface ProductService {
    fun getAllProducts():Iterable<ProductResp>
}