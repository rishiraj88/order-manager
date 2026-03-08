package om.product.service;

import module java.base;
import om.product.model.dto.ProductReq;
import om.product.model.dto.ProductResp;

public interface IProductService {
    ProductResp addProduct(ProductReq productReq);
    List<ProductResp> getAllProducts(String skuCode);
    ProductResp getProduct(String id);
    ProductResp updatePriceOfProductFoundBySkuCode(ProductReq productReq);
}
