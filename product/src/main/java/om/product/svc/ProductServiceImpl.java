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

@RequiredArgsConstructor
@Slf4j
@Service
public class ProductServiceImpl implements IProductService {
    private final ProductRepo productRepo;

    @Override
    public void addProduct(ProductReq productReq) {
        Product newProduct = Product.builder()
                .name(productReq.name())
                .desc(productReq.desc())
                .pricePerItem(productReq.pricePerItem())
                .build();

        productRepo.save(newProduct);
        log.info("New product has been added with id: {}", newProduct.getId());
    }

    @Override
    public List<ProductResp> getAllProducts() {
        List<Product> productList = productRepo.findAll();
        return productList.stream().map(
                prod -> mapProductEntityToProductResp(prod)
        ).collect(Collectors.toList());
    }

    private ProductResp mapProductEntityToProductResp(Product product) {
        return ProductResp.builder()
                .id(product.getId())
                .name(product.getName())
                .desc(product.getDesc())
                .pricePerItem(product.getPricePerItem())
                .build();
    }
}
