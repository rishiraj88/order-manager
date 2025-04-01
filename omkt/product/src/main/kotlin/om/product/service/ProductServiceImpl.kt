package om.product.service

import om.product.dto.ProductReq
import om.product.dto.ProductResp
import om.product.entity.Product
import om.product.repo.ProductRepository
import org.springframework.stereotype.Service

@Service
class ProductServiceImpl(val productRepository: ProductRepository): ProductService {
    override fun getAllProducts(): Iterable<ProductResp> {
        return productRepository.findAll().map { it.toResp() }
    }

    override fun addProduct(productReq: ProductReq):ProductResp {
        return productRepository.save(Product.fromReq(productReq)).toResp()
    }
}