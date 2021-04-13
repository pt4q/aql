package com.example.application.data.service.test_card_associated.test_card;

import com.example.application.data.entity.product_associated.product_category.ProductCategoryEntity;
import com.example.application.data.entity.test_card_associated.test_card.TestCardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface TestCardCrudRepository extends JpaRepository <TestCardEntity, Long> {

    List<TestCardEntity> findAllByProductCategory (ProductCategoryEntity productCategory);
}
