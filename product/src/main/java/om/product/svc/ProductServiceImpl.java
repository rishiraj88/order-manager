package om.product.svc;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import om.product.dao.ProductRepo;
import om.product.dto.ProductReq;
import om.product.dto.ProductResp;
import om.product.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static om.product.util.MapperUtil.mapToResponse;

@RequiredArgsConstructor
@Slf4j
@Service
public class ProductServiceImpl implements IProductService {
    private final ProductRepo productRepo;

    @Override
    public ProductResp addProduct(ProductReq productReq) {
        Product newProduct = Product.builder().name(productReq.name()).desc(productReq.desc()).skuCode(productReq.skuCode()).pricePerItem(productReq.pricePerItem()).build();
        productRepo.save(newProduct);
        log.info("New product has been added with id: {}", newProduct.getId());
        return mapToResponse(newProduct);
    }

    @Override
    public List<ProductResp> getAllProducts() {
        List<Product> productList = productRepo.findAll();
        return productList.stream().map(prod -> mapToResponse(prod)).collect(Collectors.toList());
    }
}
