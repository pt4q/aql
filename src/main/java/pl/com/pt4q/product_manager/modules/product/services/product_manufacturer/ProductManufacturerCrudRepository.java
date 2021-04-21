package pl.com.pt4q.product_manager.modules.product.services.product_manufacturer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.com.pt4q.product_manager.modules.product.data.product_category.ProductCategoryEntity;
import pl.com.pt4q.product_manager.modules.product.data.product_manufacturer.ProductManufacturerEntity;
import pl.com.pt4q.product_manager.modules.product.services.product_manufacturer.exceptions.ProductManufacturerNotFoundException;

@Repository
public interface ProductManufacturerCrudRepository extends JpaRepository<ProductManufacturerEntity, Long> {

}
