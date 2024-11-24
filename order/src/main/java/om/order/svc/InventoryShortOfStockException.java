package om.order.svc;

public class InventoryShortOfStockException extends RuntimeException {
    public InventoryShortOfStockException(String skuCode, Integer quantity) {
        super(quantity+" units of product "+skuCode+" may not be fulfilled right now. Check inventory for available stock.");
    }
}
