package com.example.application.data.service.test_card_associated.test_card;

import com.example.application.data.entity.product.ProductEntity;
import com.example.application.data.entity.test_card_associated.test_card.TestCardEntity;
import com.example.application.data.service.product.ProductBasicCrudService;
import com.example.application.data.service.product.exceptions.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TestCardForProductUpdater {

    @Autowired
    private TestCardFinder testCardFinder;

    @Autowired
    private ProductBasicCrudService productBasicCrudService;

    @Autowired
    private TestCardCrudRepository testCardCrudRepository;

    public TestCardEntity update(TestCardEntity testCard) throws ProductNotFoundException {
        checkProduct(testCard.getProduct());
        return save(testCard);
    }
    private void checkProduct(ProductEntity product) throws ProductNotFoundException {
        productBasicCrudService.getByIdOrThrow(product.getId());
    }

    private TestCardEntity save(TestCardEntity testCardEntity){
        testCardEntity.setModificationTime(LocalDateTime.now());
        return testCardCrudRepository.save(testCardEntity);
    }
}
