package om.order.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.id.factory.spi.GenerationTypeStrategy;

import java.math.BigDecimal;

@Entity @Data @AllArgsConstructor @NoArgsConstructor @Builder
@Table(name = "orders")
public class Order {
@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private String id;
private String orderNumber;
private String itemSkuCode;
private BigDecimal pricePerItem;
private Integer quantity;
}
