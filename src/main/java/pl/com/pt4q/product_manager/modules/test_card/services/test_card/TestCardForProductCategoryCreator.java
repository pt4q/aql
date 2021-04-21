package pl.com.pt4q.product_manager.modules.test_card.services.test_card;

import pl.com.pt4q.product_manager.modules.product.data.product_category.ProductCategoryEntity;
import pl.com.pt4q.product_manager.modules.test_card.data.test_card.TestCardEntity;
import pl.com.pt4q.product_manager.modules.product.services.product_category.ProductCategoryCrudService;
import pl.com.pt4q.product_manager.modules.product.services.product_category.exceptions.ProductCategoryNotFoundException;
import pl.com.pt4q.product_manager.modules.test_card.services.test_card.exceptions.TestCardAlreadyExistsException;
import pl.com.pt4q.product_manager.modules.test_card.services.test_card.exceptions.TestCardNotFoundException;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TestCardForProductCategoryCreator {

    @Autowired
    private TestCardCrudRepository testCardCrudRepository;
    @Autowired
    private ProductCategoryCrudService productCategoryCrudService;
    @Autowired
    private TestCardFinder testCardFinder;

    @SneakyThrows
    public TestCardEntity createEmptyTestCardForProduct(TestCardEntity testCard) {
        checkProduct(testCard.getProductCategory());
        return save(
                        addCreationTimeToTestCard(testCard));
    }

    private TestCardEntity save(TestCardEntity entity) throws TestCardAlreadyExistsException {
        try {
            testCardFinder.findTestCardById(entity.getId());
        } catch (TestCardNotFoundException e) {
            return testCardCrudRepository.save(entity);
        }
        throw new TestCardAlreadyExistsException(String.format("Test card for is already exists on id:%d", entity.getId()));
    }

    private void checkProduct(ProductCategoryEntity product) throws ProductCategoryNotFoundException {
        productCategoryCrudService.getByIdOrThrow(product.getId());
    }

    private TestCardEntity addCreationTimeToTestCard(TestCardEntity testCard) {
        testCard.setCreationTime(LocalDateTime.now());
        return testCard;
    }


}
