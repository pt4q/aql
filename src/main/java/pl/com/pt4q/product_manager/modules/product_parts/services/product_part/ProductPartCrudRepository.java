package pl.com.pt4q.product_manager.modules.product_parts.services.product_part;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.com.pt4q.product_manager.modules.product.data.product.ProductEntity;
import pl.com.pt4q.product_manager.modules.product_parts.data.product_part.ProductPartEntity;

import java.util.List;

@Repository
interface ProductPartCrudRepository extends JpaRepository<ProductPartEntity, Long> {

    List<ProductPartEntity> findAllByProduct(ProductEntity product);
}
