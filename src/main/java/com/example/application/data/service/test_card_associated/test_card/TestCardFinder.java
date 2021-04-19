package com.example.application.data.service.test_card_associated.test_card;

import com.example.application.data.entity.product_associated.product_category.ProductCategoryEntity;
import com.example.application.data.entity.test_card_associated.test_card.TestCardEntity;
import com.example.application.data.service.test_card_associated.test_card.exceptions.TestCardNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TestCardFinder {

    @Autowired
    private TestCardCrudRepository testCardCrudRepository;

    public TestCardEntity findTestCardById(Long id) throws TestCardNotFoundException {
        if(id != null){
            Optional<TestCardEntity> testCardEntity = testCardCrudRepository.findById(id);
            if (testCardEntity.isPresent())
                return testCardEntity.get();
            else
                throw new TestCardNotFoundException(String.format("Test card with id:%d not exists", id));
        }
        throw new TestCardNotFoundException(String.format("Test card id:%d", id));
    }

    public List<TestCardEntity> getAllTestCardsByProduct(ProductCategoryEntity product) {
        return testCardCrudRepository.findAllByProductCategory(product);
    }

    public List<TestCardEntity> getAll(){
        return testCardCrudRepository.findAll();
    }
}
