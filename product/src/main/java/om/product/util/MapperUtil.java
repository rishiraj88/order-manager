package om.product.util;

import om.product.dto.ProductResp;
import om.product.entity.Product;

public class MapperUtil {
    static public ProductResp mapProductEntityToProductResp(Product product) {
        return ProductResp.builder().id(product.getId()).name(product.getName()).desc(product.getDesc()).skuCode(product.getSkuCode()).pricePerItem(product.getPricePerItem()).build();
    }
}
