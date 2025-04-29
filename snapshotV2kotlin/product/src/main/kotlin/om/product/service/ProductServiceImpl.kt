package om.product.service

import om.product.dto.ProductReq
import om.product.dto.ProductResp
import om.product.entity.Product
import om.product.exception.ResourceNotFoundException
import om.product.repo.ProductRepository
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrNull

@Service
class ProductServiceImpl(val productRepository: ProductRepository) : ProductService {

    override fun getAllProducts(page: Int, size: Int): Iterable<ProductResp> {
        val pageable: Pageable = PageRequest.of(page, size)
        val products = productRepository.findAll(pageable).map { it.toResp() }
        if (products.isEmpty) throw ResourceNotFoundException("No products found.")
        return products
    }

    override fun getAllProducts(sortBy: String?): Iterable<ProductResp> {
        val sortedProducts =
            if (null == sortBy) productRepository.findAll().map { it.toResp() } else productRepository.findAll(
                Sort.by(
                    Sort.Direction.DESC,
                    sortBy
                )
            ).map { it.toResp() }
        if (sortedProducts.isEmpty()) throw ResourceNotFoundException("No products available to sort and show.")
        return sortedProducts
    }

    override fun addProduct(productReq: ProductReq): ProductResp {
        return productRepository.save(Product.fromReq(productReq)).toResp()
    }

    override fun getProduct(id: String): ProductResp? {
        val matchingProduct = productRepository.findById(id).getOrNull()?.toResp()
        if (null == matchingProduct) throw ResourceNotFoundException("No matching products found.")
        return matchingProduct
    }
}