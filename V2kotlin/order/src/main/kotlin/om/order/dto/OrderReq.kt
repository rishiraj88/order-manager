package om.order.dto

data class OrderReq(val orderNumber:String,val skuCode: String,val itemRate: Float, val itemQuantity: Int)