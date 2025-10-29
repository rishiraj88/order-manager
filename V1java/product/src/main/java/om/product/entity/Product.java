package om.product.entity;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder @Data
@Document(value = "product")
public class Product {
    @Id
    private String id;

    @NotEmpty(message="Product name is cannot be omitted.")
    private String name;
    private String desc;

    @NotEmpty(message="SKU code is mandatory.")
    private String skuCode; // Category may be inferred from SKU code
    private String primaryCategory;
    private BigDecimal pricePerItemUnit; //vendors and variants may be added optionally
    private boolean inStock;
    private LocalDate releaseDate;
    private List<String> tags;
}
