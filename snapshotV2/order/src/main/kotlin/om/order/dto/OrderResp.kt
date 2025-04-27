package om.order.dto

import java.util.UUID

data class OrderResp(
    val id: UUID, val orderNumber: String,
    val skuCode: String,
    val itemRate: Number,
    val itemQuantity: Number)