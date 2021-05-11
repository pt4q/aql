package pl.com.pt4q.product_manager.modules.product.services.product_part;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.com.pt4q.product_manager.modules.product.data.product.ProductEntity;
import pl.com.pt4q.product_manager.modules.product.data.product_part.ProductPartEntity;

import java.util.List;
import java.util.Set;

@Repository
interface ProductPartCrudRepository extends JpaRepository<ProductPartEntity, Long> {

    List<ProductPartEntity> findAllByProduct(ProductEntity product);
}
