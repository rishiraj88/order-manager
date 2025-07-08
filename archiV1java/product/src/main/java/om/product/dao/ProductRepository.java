package om.product.dao;

import om.product.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ProductRepository extends MongoRepository<Product,String> {
    Collection<Object> findOneByName();
}
