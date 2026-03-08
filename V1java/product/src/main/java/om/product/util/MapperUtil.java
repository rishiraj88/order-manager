package om.product.util;

import om.product.model.dto.ProductResp;
import om.product.model.entity.Product;

public class MapperUtil {
    static public ProductResp mapToResponse(Product product) {
        return new ProductResp(product.getId(), product.getName(), product.getDesc(), product.getSkuCode(), product.getPricePerItemUnit());
    }
}