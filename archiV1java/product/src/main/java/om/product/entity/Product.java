package om.product.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder @Data
@Document(value = "product")
public class Product {
    @Id
    private String id;
    private String name;
    private String desc;
    private String skuCode; // Category may be inferred out of SKU code
    private BigDecimal pricePerItemUnit; //vendors and variants may be added optionally
}
