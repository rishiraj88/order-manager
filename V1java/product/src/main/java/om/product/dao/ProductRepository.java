package om.product.dao;

import module java.base;
import om.product.model.entity.Product;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
    Collection<Product> findOneByName();

    default Collection<Product> findAll(String skuCode) {
        Product exampleProduct = new Product();
        exampleProduct.setSkuCode(skuCode);
        return findAll(Example.of(exampleProduct));
    }
}
