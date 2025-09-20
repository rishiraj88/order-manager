package om.product.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;

import java.math.BigDecimal;

@Builder @JsonIgnoreProperties(ignoreUnknown=true)
public record ProductResp(String id, String name, String desc, String skuCode, BigDecimal pricePerItemUnit) {
}
