package om.product.exception;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String skuCode) {
        super("Product with SKU code "+skuCode+" could not be found right now. Try checking after a while.");
    }
}
