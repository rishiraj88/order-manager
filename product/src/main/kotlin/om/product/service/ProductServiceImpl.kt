package om.product.service

import om.product.dto.ProductReq
import om.product.dto.ProductResp
import om.product.entity.Product
import om.product.repo.ProductRepository
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrNull

@Service
class ProductServiceImpl(val productRepository: ProductRepository) : ProductService {
    override fun getAllProducts(page: Int, size: Int): Iterable<ProductResp> {
        val pageable: Pageable = PageRequest.of(page, size)
        return productRepository.findAll(pageable).map { it.toResp() }
    }

    override fun getAllProducts(sortBy: String?): Iterable<ProductResp> {
        return if (null == sortBy) productRepository.findAll().map { it.toResp() } else productRepository.findAll()
            .map { it.toResp() }
    }

    override fun addProduct(productReq: ProductReq): ProductResp {
        return productRepository.save(Product.fromReq(productReq)).toResp()
    }

    override fun getProduct(id: String): ProductResp? {
        return productRepository.findById(id).getOrNull()?.toResp()
    }
}