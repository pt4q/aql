package pl.com.pt4q.product_manager.modules.product.services.product_part_attribute_version;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.com.pt4q.product_manager.modules.product.data.product_part_attribute_version.ProductPartAttributeVersionEntity;

@Repository
interface ProductPartAttributeVersionCrudRepository extends JpaRepository<ProductPartAttributeVersionEntity, Long> {
}
