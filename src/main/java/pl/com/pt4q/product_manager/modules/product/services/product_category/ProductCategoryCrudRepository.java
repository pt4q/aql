package pl.com.pt4q.product_manager.modules.product.services.product_category;

import pl.com.pt4q.product_manager.modules.product.data.product_category.ProductCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCategoryCrudRepository extends JpaRepository<ProductCategoryEntity, Long> {

}
