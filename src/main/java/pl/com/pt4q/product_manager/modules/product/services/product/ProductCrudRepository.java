package pl.com.pt4q.product_manager.modules.product.services.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.com.pt4q.product_manager.modules.product.data.product.ProductEntity;

import java.util.Optional;

@Repository
interface ProductCrudRepository extends JpaRepository<ProductEntity, Long> {

    Optional<ProductEntity> findBySku(String sku);
}
