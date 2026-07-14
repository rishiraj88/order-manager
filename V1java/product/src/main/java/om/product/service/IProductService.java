package om.product.service;

import om.product.model.dto.ProductReq;
import om.product.model.dto.ProductResp;

import java.util.List;

public interface IProductService {
    ProductResp addProduct(ProductReq productReq);
    List<ProductResp> getAllProducts(String skuCode);
    ProductResp getProduct(String id);
    ProductResp updatePriceOfProductFoundBySkuCode(ProductReq productReq);
}
