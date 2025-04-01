package om.product.entity

import om.product.dto.ProductResp
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.math.BigDecimal

@Document
data class Product(@Id val id: String, val name: String, val desc: String, val skuCode: String, val rate: BigDecimal) {
    fun toView() = ProductResp(id, name, desc,skuCode, rate)
}