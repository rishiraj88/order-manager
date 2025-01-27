package om.product.util;

import om.product.dto.ProductResp;
import om.product.entity.Product;

public class MapperUtil {
    static public ProductResp mapToResponse(Product product) {
        return new ProductResp(product.getId(), product.getName(), product.getDesc(), product.getSkuCode(), product.getPricePerItem());
    }
}
