package om.order.dto

import jakarta.persistence.criteria.CriteriaBuilder.In

data class OrderReq(val orderNumber:String,val skuCode: String,val itemRate: Float, val itemQuantity: Int)