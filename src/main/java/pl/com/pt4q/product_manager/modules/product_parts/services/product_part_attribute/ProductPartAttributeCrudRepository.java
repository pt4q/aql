package pl.com.pt4q.product_manager.modules.product_parts.services.product_part_attribute;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.com.pt4q.product_manager.modules.product_parts.data.product_part_attribute.ProductPartAttributeEntity;
import pl.com.pt4q.product_manager.modules.product_parts.data.product_part.ProductPartEntity;

import java.util.List;

@Repository
interface ProductPartAttributeCrudRepository extends JpaRepository<ProductPartAttributeEntity, Long> {

    List<ProductPartAttributeEntity> findAllByPart(ProductPartEntity part);
}
