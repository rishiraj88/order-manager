package om.order.dto

data class OrderReq(val orderNumber:String,val skuCode: String,val itemRate: Number, val itemQuantity: Number)