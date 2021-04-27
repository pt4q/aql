package pl.com.pt4q.product_manager.config;

import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.com.pt4q.product_manager.modules.product.data.manufacturer.ManufacturerEntity;
import pl.com.pt4q.product_manager.modules.product.data.product.ProductEntity;
import pl.com.pt4q.product_manager.modules.product.data.product_category.ProductCategoryEntity;
import pl.com.pt4q.product_manager.modules.product.services.manufacturer.ManufacturerCrudService;
import pl.com.pt4q.product_manager.modules.product.services.product.AddNewOrUpdateExistingProductService;
import pl.com.pt4q.product_manager.modules.product.services.product_category.ProductCategoryCrudService;
import pl.com.pt4q.product_manager.modules.test_card.data.test_card.TestCardEntity;
import pl.com.pt4q.product_manager.modules.test_card.services.test_card.TestCardForProductCategoryCreator;

import java.util.LinkedList;
import java.util.List;

@Log4j2
@Component
public class AppInitializer implements CommandLineRunner {

    @Autowired
    private ProductCategoryCrudService productCategoryCrudService;
    @Autowired
    private ManufacturerCrudService manufacturerCrudService;
    @Autowired
    private AddNewOrUpdateExistingProductService addNewOrUpdateExistingProductService;
    @Autowired
    private TestCardForProductCategoryCreator cardForProductCreator;

    @Override
    public void run(String... args) throws Exception {
        List<ProductCategoryEntity> categories = initCategories();
        categories.forEach(category -> logToConsoleWhatWasCreated("category", category.getCategoryName(), category.getId()));

        List<ManufacturerEntity> manufacturers = initManufacturers();
        manufacturers.forEach(manufacturer -> logToConsoleWhatWasCreated("manufacturer", manufacturer.getManufacturerName(), manufacturer.getId()));

        List<ProductEntity> products = initProducts(categories,manufacturers);
        products.forEach(product -> logToConsoleWhatWasCreated("product", product.getProductSku(), product.getId()));

//        List <TestCardEntity> testCards = initTestCards(categories);
//        testCards.forEach(testCard -> logToConsoleWhatWasCreated("test card", testCard.getTestCardName(), testCard.getId()));
    }

    private List<ProductCategoryEntity> initCategories(){
        ProductCategoryEntity productCategoryEntity = productCategoryCrudService
                .create(ProductCategoryEntity.builder()
                        .categoryName("Wkrętarki TEST")
                        .build());
        ProductCategoryEntity productCategoryEntity2 = productCategoryCrudService
                .create(ProductCategoryEntity.builder()
                        .categoryName("Młotowiertarki TEST")
                        .build());
        return new LinkedList<>(){{
            add(productCategoryEntity);
            add(productCategoryEntity2);
        }};
    }

    private List<ManufacturerEntity> initManufacturers(){
        ManufacturerEntity manufacturerEntity1 = manufacturerCrudService.create(ManufacturerEntity.builder()
                .manufacturerName("Ping Pong")
                .description("ping ..... pong ..... ping ..... pong")
                .build());
        return new LinkedList<>(){{
            add(manufacturerEntity1);
        }};
    }

    @SneakyThrows
    private List<ProductEntity> initProducts(List<ProductCategoryEntity> categories, List<ManufacturerEntity> manufacturers) {
        ProductEntity productEntity1 = addNewOrUpdateExistingProductService.add(ProductEntity.builder()
                .productSku("DED1234")
                .productCategory(categories.get(0))
                .manufacturer(manufacturers.get(0))
                .build());
        return new LinkedList<>(){{
            add(productEntity1);
        }};
    }

    private List<TestCardEntity> initTestCards(List<ProductCategoryEntity> categories){
        TestCardEntity testCardEntity = cardForProductCreator
                .createEmptyTestCardForProduct(TestCardEntity.builder()
                        .productCategory(categories.get(0))
                        .testCardName("Karta testowa wygenerowana podczas rozruchu aplikacji")
                        .build());
        return new LinkedList<>(){{
            add(testCardEntity);
        }};
    }

    private void logToConsoleWhatWasCreated(String createdObjectType, String createdObjectName, Long objectId) {
        log.info(String.format("TEST INIT: Created %s: %s (id:%d)", createdObjectType, createdObjectName, objectId));
    }
}
