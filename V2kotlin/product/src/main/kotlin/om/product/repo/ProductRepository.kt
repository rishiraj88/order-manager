package om.product.repo

import om.product.entity.Product
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository : MongoRepository<Product,String> {
    fun findBySkuCodeIgnoreCase(skuCode: String?): List<Product?>?
    fun findByDescIgnoreCase(desc: String?): List<Product?>?
    fun findBySkuCodeOrderBySkuCodeAsc(skuCode: String?): List<Product?>?
    fun findBySkuCodeOrderBySkuCodeDesc(skuCode: String?): List<Product?>?
}