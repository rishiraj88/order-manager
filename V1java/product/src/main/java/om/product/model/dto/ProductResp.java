package om.product.model.dto;

import module java.base;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;

@Builder @JsonIgnoreProperties(ignoreUnknown=true)
public record ProductResp(String id, String name, String desc, String skuCode, BigDecimal pricePerItemUnit) {
}
