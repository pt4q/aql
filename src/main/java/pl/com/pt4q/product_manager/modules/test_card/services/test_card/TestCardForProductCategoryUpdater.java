package pl.com.pt4q.product_manager.modules.test_card.services.test_card;

import pl.com.pt4q.product_manager.modules.product.data.product_category.ProductCategoryEntity;
import pl.com.pt4q.product_manager.modules.test_card.data.test_card.TestCardEntity;
import pl.com.pt4q.product_manager.modules.product.services.product_category.ProductCategoryCrudService;
import pl.com.pt4q.product_manager.modules.product.services.product_category.exceptions.ProductCategoryNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TestCardForProductCategoryUpdater {

    @Autowired
    private TestCardFinder testCardFinder;

    @Autowired
    private ProductCategoryCrudService productCategoryCrudService;

    @Autowired
    private TestCardCrudRepository testCardCrudRepository;

    public TestCardEntity update(TestCardEntity testCard) throws ProductCategoryNotFoundException {
        checkProduct(testCard.getProductCategory());
        return save(testCard);
    }
    private void checkProduct(ProductCategoryEntity product) throws ProductCategoryNotFoundException {
        productCategoryCrudService.getByIdOrThrow(product.getId());
    }

    private TestCardEntity save(TestCardEntity testCardEntity){
        testCardEntity.setModificationTime(LocalDateTime.now());
        return testCardCrudRepository.save(testCardEntity);
    }
}
