package om.order.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="id",updatable = false,nullable = false)
    private UUID id;

    @Column(name="order_number",updatable = false,nullable = false)
    private String orderNumber;

    @Column(name="item_sku_code",nullable = false)
    private String itemSkuCode;

    @Column(name="price_per_item",nullable = false)
    private BigDecimal pricePerItem;

    @Column(name="quantity",nullable = false)
    private Integer quantity;

    @Column(name="created",nullable = false)
    private LocalDateTime created;

    @Column(name="updated",nullable = false)
    private LocalDateTime updated;

}
