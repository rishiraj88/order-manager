package om.product.dto

import java.math.BigDecimal

data class ProductResp(val id: String, val name: String, val desc: String, val skuCode: String, val rate: BigDecimal)