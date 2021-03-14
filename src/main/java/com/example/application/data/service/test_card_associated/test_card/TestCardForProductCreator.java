package com.example.application.data.service.test_card_associated.test_card;

import com.example.application.data.entity.product.ProductEntity;
import com.example.application.data.entity.test_card_associated.test_card.TestCardEntity;
import com.example.application.data.service.product.ProductBasicCrudService;
import com.example.application.data.service.product.exceptions.ProductNotFoundException;
import com.example.application.data.service.test_card_associated.test_card.exceptions.TestCardAlreadyExistsException;
import com.example.application.data.service.test_card_associated.test_card.exceptions.TestCardNotFoundException;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TestCardForProductCreator {

    @Autowired
    private TestCardCrudRepository testCardCrudRepository;
    @Autowired
    private ProductBasicCrudService productBasicCrudService;
    @Autowired
    private TestCardFinder testCardFinder;

    @SneakyThrows
    public TestCardEntity createEmptyTestCardForProduct(TestCardEntity testCard) {
        checkProduct(testCard.getProduct());
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

    private void checkProduct(ProductEntity product) throws ProductNotFoundException {
        productBasicCrudService.getByIdOrThrow(product.getId());
    }

    private TestCardEntity addCreationTimeToTestCard(TestCardEntity testCard) {
        testCard.setCreationTime(LocalDateTime.now());
        return testCard;
    }


}
