package om.product.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import om.product.dao.ProductRepository;
import om.product.dto.ProductReq;
import om.product.dto.ProductResp;
import om.product.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static om.product.util.MapperUtil.mapToResponse;

@RequiredArgsConstructor
@Slf4j
@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    private final ProductRepository productRepository;

    @Override
    public ProductResp addProduct(ProductReq productReq) {
        Product newProduct = Product.builder().name(productReq.name()).desc(productReq.desc()).skuCode(productReq.skuCode()).pricePerItem(productReq.pricePerItem()).build();
        productRepository.save(newProduct);
        log.info("New product has been added with id: {}", newProduct.getId());
        return mapToResponse(newProduct);
    }

    @Override
    public List<ProductResp> getAllProducts() {
        return productRepository.findAll().stream().map(prod -> mapToResponse(prod)).collect(Collectors.toList());
    }
}
