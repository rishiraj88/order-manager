package om.product.service

import om.product.dto.ProductReq
import om.product.dto.ProductResp

interface ProductService {
    fun getAllProducts(page: Int, size: Int): Iterable<ProductResp>
    fun getAllProducts(sortBy: String?): Iterable<ProductResp>
    fun addProduct(productReq: ProductReq): ProductResp
    fun getProduct(id: String): ProductResp?
}