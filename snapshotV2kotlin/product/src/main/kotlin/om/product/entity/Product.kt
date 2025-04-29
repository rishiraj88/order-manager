package om.product.entity

import om.product.dto.ProductReq
import om.product.dto.ProductResp
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.math.BigDecimal

@Document(collection="products")
data class Product(val name: String, val desc: String, val skuCode: String, val rate: BigDecimal) {
    @Id
    lateinit var id: String

    fun toResp() = ProductResp(id, name, desc, skuCode, rate)

    companion object {
        fun fromReq(productReq: ProductReq): Product =
            Product(productReq.name, productReq.desc, productReq.skuCode, productReq.rate)
    }
}