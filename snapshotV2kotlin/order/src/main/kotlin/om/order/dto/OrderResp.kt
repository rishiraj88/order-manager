package om.order.dto

data class OrderResp(
    val id: String, val orderNumber: String,
    val skuCode: String,
    val itemRate: Float,
    val itemQuantity: Int)