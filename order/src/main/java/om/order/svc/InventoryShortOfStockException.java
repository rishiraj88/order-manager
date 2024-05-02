package om.order.svc;

public class InventoryShortOfStockException extends RuntimeException {
    public InventoryShortOfStockException(String skuCode, Integer quantity) {
        super(quantity+" units of product "+skuCode+" may not be fulfilled right away. Check invntory stock.");
    }
}
