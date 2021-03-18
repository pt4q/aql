package com.example.application.config;

import com.example.application.data.entity.product_category.ProductCategoryEntity;
import com.example.application.data.entity.test_card_associated.test_card.TestCardEntity;
import com.example.application.data.service.product_category.ProductCategoryCrudService;
import com.example.application.data.service.test_card_associated.test_card.TestCardForProductCategoryCreator;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class AppInitializer implements CommandLineRunner {

    @Autowired
    private ProductCategoryCrudService productCategoryCrudService;
    @Autowired
    private TestCardForProductCategoryCreator cardForProductCreator;

    @Override
    public void run(String... args) throws Exception {
        ProductCategoryEntity productCategoryEntity = productCategoryCrudService
                .create(ProductCategoryEntity.builder()
                        .productCategoryName("Wkrętarki TEST")
                        .build());
        log.info(String.format("TEST INIT: Created product category: %s (id:%d)", productCategoryEntity.getProductCategoryName(), productCategoryEntity.getId()));

        TestCardEntity testCardEntity = cardForProductCreator
                .createEmptyTestCardForProduct(TestCardEntity.builder()
                        .productCategory(productCategoryEntity)
                        .testCardName("Karta testowa wygenerowana podczas rozruchu aplikacji")
                        .build());
        log.info(String.format("TEST INIT: Created test card \"%s\" (id:%d) for TEST product category %s (id:%d)",
                testCardEntity.getTestCardName(), testCardEntity.getId(),
                productCategoryEntity.getProductCategoryName(), productCategoryEntity.getId()));
    }
}
