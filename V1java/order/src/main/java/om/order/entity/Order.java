package om.order.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PastOrPresent;
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
    @NotEmpty(message="Order number is mandatory.")
    private String orderNumber;

    private LocalDateTime orderDate;
    private String status;
    private BigDecimal totalAmount;

    @Column(name="item_sku_code",nullable = false)
    @NotEmpty(message="SKU code is mandatory.")
    private String itemSkuCode;

    @Column(name="price_per_item_unit",nullable = false)
    private BigDecimal pricePerItemUnit;

    @Column(name="quantity",nullable = false)
    @Min(message = "Minimum quantity permitted is 1 unit.", value = 1L)
    private Integer quantity;

    @Column(name="created",nullable = false)
    @PastOrPresent(message = "Future record creation date/time is not permitted.")
    private LocalDateTime created;

    @Column(name="updated",nullable = false)
    @PastOrPresent(message = "Future record modification date/time is not permitted.")
    private LocalDateTime updated;
}
