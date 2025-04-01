package om.product.service

import om.product.dto.ProductResp
import om.product.entity.Product
import om.product.repo.ProductRepository
import org.springframework.stereotype.Service
import java.math.BigDecimal
@Service
class ProductServiceImpl(val productRepository: ProductRepository): ProductService {
    override fun getAllProducts(): Iterable<ProductResp> {
         listOf(Product("id", "name"," desc","skuCode", BigDecimal.valueOf(1341687145)).toView())
        return productRepository.findAll().map { it.toView() }
    }
}