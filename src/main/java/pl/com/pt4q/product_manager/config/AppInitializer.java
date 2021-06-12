package pl.com.pt4q.product_manager.config;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.com.pt4q.product_manager.modules.product.data.manufacturer.ManufacturerEntity;
import pl.com.pt4q.product_manager.modules.product.data.product.ProductEntity;
import pl.com.pt4q.product_manager.modules.product.data.product_category.ProductCategoryEntity;
import pl.com.pt4q.product_manager.modules.product.data.product_part.ProductPartEntity;
import pl.com.pt4q.product_manager.modules.product.data.product_series.ProductSeriesEntity;
import pl.com.pt4q.product_manager.modules.product.data.unit.UnitEntity;
import pl.com.pt4q.product_manager.modules.product.data.unit.UnitTypeEnum;
import pl.com.pt4q.product_manager.modules.product.services.manufacturer.ManufacturerCrudService;
import pl.com.pt4q.product_manager.modules.product.services.product.ProductCreatorAndUpdaterService;
import pl.com.pt4q.product_manager.modules.product.services.product_category.ProductCategoryCrudService;
import pl.com.pt4q.product_manager.modules.product.services.product_category.exceptions.ProductCategoryAlreadyExistsException;
import pl.com.pt4q.product_manager.modules.product.services.product_series.ProductSeriesCrudService;
import pl.com.pt4q.product_manager.modules.product.services.product_series.exceptions.ProductSeriesAlreadyExistsException;
import pl.com.pt4q.product_manager.modules.product.services.unit.UnitCrudService;
import pl.com.pt4q.product_manager.modules.product.services.unit.exceptions.UnitAlreadyExistsException;
import pl.com.pt4q.product_manager.modules.test_card.data.test_card.TestCardEntity;
import pl.com.pt4q.product_manager.modules.test_card.services.test_card.TestCardForProductCategoryCreator;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class AppInitializer implements CommandLineRunner {

    @Autowired
    private UnitCrudService unitCrudService;
    @Autowired
    private ProductCategoryCrudService productCategoryCrudService;
    @Autowired
    private ManufacturerCrudService manufacturerCrudService;
    @Autowired
    private ProductSeriesCrudService productSeriesCrudService;
    @Autowired
    private ProductCreatorAndUpdaterService productCreatorAndUpdaterService;
    @Autowired
    private TestCardForProductCategoryCreator cardForProductCreator;

    @Override
    public void run(String... args) throws Exception {
        List<UnitEntity> units = initDefaultUnits();
        units.forEach(unitEntity -> logToConsoleWhatWasCreated("units", unitEntity.getName(), unitEntity.getId()));

        List<ProductCategoryEntity> categories = initCategories();
        categories.forEach(category -> logToConsoleWhatWasCreated("category", category.getCategoryName(), category.getId()));

        List<ManufacturerEntity> manufacturers = initManufacturers();
        manufacturers.forEach(manufacturer -> logToConsoleWhatWasCreated("manufacturer", manufacturer.getManufacturerName(), manufacturer.getId()));

        List<ProductSeriesEntity> seriesList = initSeries();
        seriesList.forEach(series -> logToConsoleWhatWasCreated("series", series.getSeries(), series.getId()));

        List<ProductEntity> products = initProducts(categories, manufacturers);
        products.forEach(product -> logToConsoleWhatWasCreated("product", product.getProductSku(), product.getId()));

//        List<ProductPartEntity> firstProductParts = initProductParts(products.get(0), seriesList);
//        firstProductParts.forEach(part -> logToConsoleWhatWasCreated("part", String.format("%s part for %s product (id:%d)", part.getPartModelOrPartName(), part.getProduct().getProductSku(), part.getProduct().getId()), part.getId()));

//        List<ProductPartAttributeEntity> firstProductPartAttributes = initAttributesForPart(firstProductParts.get(0));
//        firstProductPartAttributes
//                .forEach(attribute -> logToConsoleWhatWasCreated("part attribute", String.format("%s for %s part (id:%d) for %s product (id:%d)", attribute.getAttributeName(), attribute.getPart(), attribute.getPart()), attribute.getId()));

//        List <TestCardEntity> testCards = initTestCards(categories);
//        testCards.forEach(testCard -> logToConsoleWhatWasCreated("test card", testCard.getTestCardName(), testCard.getId()));
    }

    private List<UnitEntity> initDefaultUnits() {
        List<UnitEntity> units = new ArrayList<>();
        units.add(UnitEntity.builder()
                .name("tekst")
                .units("[-]")
                .decimalPlaces(0)
                .valuesType(UnitTypeEnum.TEXT)
                .build());
        units.add(UnitEntity.builder()
                .name("sztuka")
                .units("[szt]")
                .decimalPlaces(0)
                .valuesType(UnitTypeEnum.DECIMAL)
                .build());
        units.add(UnitEntity.builder()
                .name("metr")
                .units("[m]")
                .decimalPlaces(0)
                .valuesType(UnitTypeEnum.FLOAT)
                .build());
        units.add(UnitEntity.builder()
                .name("milimetr")
                .units("[mm]")
                .decimalPlaces(-3)
                .valuesType(UnitTypeEnum.FLOAT)
                .build());
        units.add(UnitEntity.builder()
                .name("kilogram")
                .units("[kg]")
                .decimalPlaces(0)
                .valuesType(UnitTypeEnum.DECIMAL)
                .build());
        units.add(UnitEntity.builder()
                .name("gram")
                .units("[g]")
                .decimalPlaces(-3)
                .valuesType(UnitTypeEnum.DECIMAL)
                .build());

        return units.stream()
                .map(unit -> {
                    try {
                        return unitCrudService.create(unit);
                    } catch (UnitAlreadyExistsException e) {
                        return null;
                    }
                })
                .collect(Collectors.toList());
    }

    private List<ProductCategoryEntity> initCategories() {
        ProductCategoryEntity productCategoryEntity = productCategoryCrudService
                .create(ProductCategoryEntity.builder()
                        .categoryName("Wkrętarki TEST")
                        .build());
        ProductCategoryEntity productCategoryEntity2 = productCategoryCrudService
                .create(ProductCategoryEntity.builder()
                        .categoryName("Młotowiertarki TEST")
                        .build());
        return new LinkedList<>() {{
            add(productCategoryEntity);
            add(productCategoryEntity2);
        }};
    }

    private List<ManufacturerEntity> initManufacturers() {
        List<ManufacturerEntity> manufacturers = new LinkedList<>();
        try {
            manufacturers.add(
                    manufacturerCrudService.create(ManufacturerEntity.builder()
                            .manufacturerName("Ping Pong")
                            .description("ping ..... pong ..... ping ..... pong")
                            .build())
            );
        } catch (ProductCategoryAlreadyExistsException e) {
        }

        return manufacturers;
    }

    private List<ProductSeriesEntity> initSeries() {
        List<String> series = new ArrayList<>();
        series.add("12345678");
        series.add("87654321");

        return series.stream()
                .map(s -> {
                    try {
                        return productSeriesCrudService.create(ProductSeriesEntity.builder()
                                .series(s)
                                .build());
                    } catch (ProductSeriesAlreadyExistsException e) {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    @SneakyThrows
    private List<ProductEntity> initProducts(List<ProductCategoryEntity> categories, List<ManufacturerEntity> manufacturers) {
        ProductEntity productEntity1 = productCreatorAndUpdaterService.add(ProductEntity.builder()
                .productSku("DED1234")
                .productCategory(categories.get(0))
                .manufacturer(manufacturers.get(0))
                .build());
        return new LinkedList<>() {{
            add(productEntity1);
        }};
    }

    private List<ProductPartEntity> initProductParts(ProductEntity product, List<ProductSeriesEntity> seriesList) {
        List<String> partNames = new ArrayList<>();
        partNames.add("wirnik");

        int maxSeries = seriesList.stream()
                .mapToInt(series -> Integer.parseInt(series.getSeries()))
                .max()
                .getAsInt();

        List<ProductPartEntity> productParts = new ArrayList<>();

        int seriesSize = seriesList.size();
        int partNamesSize = partNames.size();

        for (int i = 0; i < partNamesSize; i++) {
            for (int j = 0; j < seriesSize; j++) {
                productParts.add(ProductPartEntity.builder()
                        .product(product)
                        .partModelOrPartName(partNames.get(i))
                        .build());
            }
        }
        return productParts;
    }

//    private List<ProductPartAttributeEntity> initAttributesForPart(ProductPartEntity part) {
//
//    }

    private List<TestCardEntity> initTestCards(List<ProductCategoryEntity> categories) {
        TestCardEntity testCardEntity = cardForProductCreator
                .createEmptyTestCardForProduct(TestCardEntity.builder()
                        .productCategory(categories.get(0))
                        .testCardName("Karta testowa wygenerowana podczas rozruchu aplikacji")
                        .build());
        return new LinkedList<>() {{
            add(testCardEntity);
        }};
    }

    private void logToConsoleWhatWasCreated(String createdObjectType, String createdObjectName, Long objectId) {
        System.out.println(String.format("TEST INIT: Created %s: %s (id:%d)", createdObjectType, createdObjectName, objectId));
    }
}
