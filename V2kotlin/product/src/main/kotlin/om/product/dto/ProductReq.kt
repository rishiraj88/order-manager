package om.product.dto

import java.math.BigDecimal

data class ProductReq(val name: String, val desc: String, val skuCode: String, val rate: BigDecimal)