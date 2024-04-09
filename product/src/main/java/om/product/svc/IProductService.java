package om.product.svc;

import om.product.dto.ProductReq;
import om.product.dto.ProductResp;

import java.util.List;

public interface IProductService {
    ProductResp addProduct(ProductReq productReq);

    List<ProductResp> getAllProducts();
}
