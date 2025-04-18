package om.product.service

import om.product.dto.ProductReq
import om.product.dto.ProductResp

interface ProductService {
    fun getAllProducts():Iterable<ProductResp>
    fun addProduct(productReq: ProductReq):ProductResp
}