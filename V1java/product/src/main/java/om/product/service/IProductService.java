package om.product.service;

import om.product.dto.ProductReq;
import om.product.dto.ProductResp;

import java.util.List;

public interface IProductService {
    ProductResp addProduct(ProductReq productReq);
    List<ProductResp> getAllProducts(String skuCode);
    ProductResp getProduct(String id);
    ProductResp updatePriceOfProductFoundBySkuCode(ProductReq productReq);
    //ProductResp updatePriceOfProductFoundBySkuCode(String skuCode);
}
