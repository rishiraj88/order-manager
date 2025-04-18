package om.product.repo

import om.product.entity.Product
import org.springframework.data.mongodb.repository.MongoRepository

interface ProductRepository : MongoRepository<Product,String> {
    fun findBySkuCodeIgnoreCase(skuCode: String?): List<Product?>?

    // Enabling ignoring case
    fun findByDescIgnoreCase(desc: String?): List<Product?>?

    // Enabling static ORDER BY
    fun findBySkuCodeOrderBySkuCodeAsc(skuCode: String?): List<Product?>?
    fun findBySkuCodeOrderBySkuCodeDesc(skuCode: String?): List<Product?>?
}