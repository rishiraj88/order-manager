package om.product.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown=true)
public record ProductReq(String id,String name, String desc, String skuCode, BigDecimal pricePerItemUnit) {
}
