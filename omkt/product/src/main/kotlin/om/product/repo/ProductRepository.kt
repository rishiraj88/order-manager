package om.product.repo

import om.product.entity.Product
import org.springframework.data.mongodb.repository.MongoRepository

interface ProductRepository : MongoRepository<Product,String> {
    fun findBySkucodeIgnoreCase(skucode: String?): List<Product?>?

    // Enabling ignoring case
    fun findByDescIgnoreCase(desc: String?): List<Product?>?

    // Enabling static ORDER BY
    fun findBySkucodeOrderBySkucodeAsc(skucode: String?): List<Product?>?
    fun findBySkucodeOrderBySkucodeDesc(skucode: String?): List<Product?>?
}