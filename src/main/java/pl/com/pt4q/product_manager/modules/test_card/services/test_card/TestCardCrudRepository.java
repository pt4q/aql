package pl.com.pt4q.product_manager.modules.test_card.services.test_card;

import pl.com.pt4q.product_manager.modules.product.data.product_category.ProductCategoryEntity;
import pl.com.pt4q.product_manager.modules.test_card.data.test_card.TestCardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface TestCardCrudRepository extends JpaRepository <TestCardEntity, Long> {

    List<TestCardEntity> findAllByProductCategory (ProductCategoryEntity productCategory);
}
