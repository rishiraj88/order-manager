package om.product.svc;

import om.product.dto.ProductReq;
import om.product.dto.ProductResp;

import java.util.List;

public interface IProductService {
    void addProduct(ProductReq productReq);

    List<ProductResp> getAllProducts();
}
