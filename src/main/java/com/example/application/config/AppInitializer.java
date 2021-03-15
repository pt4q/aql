package com.example.application.config;

import com.example.application.data.entity.product.ProductEntity;
import com.example.application.data.entity.test_card_associated.test_card.TestCardEntity;
import com.example.application.data.service.product.ProductBasicCrudService;
import com.example.application.data.service.test_card_associated.test_card.TestCardForProductCreator;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class AppInitializer implements CommandLineRunner {

    @Autowired
    private ProductBasicCrudService productBasicCrudService;
    @Autowired
    private TestCardForProductCreator cardForProductCreator;

    @Override
    public void run(String... args) throws Exception {
        ProductEntity productEntity = productBasicCrudService
                .create(ProductEntity.builder()
                        .productNumber("DED7840 (test)")
                        .build());
        log.info(String.format("TEST INIT: Created product: %s (id:%d)", productEntity.getProductNumber(), productEntity.getId()));

        TestCardEntity testCardEntity = cardForProductCreator
                .createEmptyTestCardForProduct(TestCardEntity.builder()
                        .product(productEntity)
                        .testCardName("Karta testowa wygenerowana podczas rozruchu aplikacji")
                        .build());
        log.info(String.format("TEST INIT: Created test card \"%s\" (id:%d) for TEST product %s (id:%d)",
                testCardEntity.getTestCardName(), testCardEntity.getId(),
                productEntity.getProductNumber(), productEntity.getId()));
    }
}
