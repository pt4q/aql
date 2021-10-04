package pl.com.pt4q.product_manager.config;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.ManagedList;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.com.pt4q.product_manager.modules.environment.data.master.EnvMasterEntity;
import pl.com.pt4q.product_manager.modules.environment.data.material_associated.group_of_material.EnvMaterialGroupEntity;
import pl.com.pt4q.product_manager.modules.environment.data.material_associated.material.EnvMaterialEntity;
import pl.com.pt4q.product_manager.modules.environment.services.master.EnvMasterSaverService;
import pl.com.pt4q.product_manager.modules.environment.services.master.exceptions.EnvMasterAlreadyExistsException;
import pl.com.pt4q.product_manager.modules.environment.services.material_associated.group_of_material.EnvMaterialGroupCrudService;
import pl.com.pt4q.product_manager.modules.environment.services.material_associated.group_of_material.exceptions.EnvMaterialGroupAlreadyExistsException;
import pl.com.pt4q.product_manager.modules.environment.services.material_associated.material.EnvMaterialCrudService;
import pl.com.pt4q.product_manager.modules.environment.services.material_associated.material.exception.EnvMaterialAlreadyExistsException;
import pl.com.pt4q.product_manager.modules.product.data.manufacturer.ManufacturerEntity;
import pl.com.pt4q.product_manager.modules.product.data.product.ProductEntity;
import pl.com.pt4q.product_manager.modules.product.data.product_category.ProductCategoryEntity;
import pl.com.pt4q.product_manager.modules.product_parts.data.product_part.ProductPartEntity;
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

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.*;
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
    private EnvMasterSaverService envMasterSaverService;
    @Autowired
    private EnvMaterialGroupCrudService envMaterialGroupCrudService;
    @Autowired
    private EnvMaterialCrudService materialCrudService;
//    @Autowired
//    private TestCardForProductCategoryCreator cardForProductCreator;


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
        products.forEach(product -> logToConsoleWhatWasCreated("product", product.getSku(), product.getId()));

        List<EnvMasterEntity> envMasterCards = initEnvMasterCards(products);
        envMasterCards.forEach(master -> logToConsoleWhatWasCreated("master card", String.format("master for %s", master.getProduct().getSku()), master.getId()));

        List<EnvMaterialGroupEntity> materialGroups = initMaterialGroups();
        materialGroups.forEach(group -> logToConsoleWhatWasCreated("material group", group.getNameENG(), group.getId()));

        List<EnvMaterialEntity> materials = initMaterials(materialGroups);
        materials.forEach(material -> logToConsoleWhatWasCreated("material", material.getNameENG(), material.getId()));

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
                .units("-")
                .decimalPlaces(0)
                .valuesType(UnitTypeEnum.TEXT)
                .build());
        units.add(UnitEntity.builder()
                .name("sztuka")
                .units("szt")
                .decimalPlaces(0)
                .valuesType(UnitTypeEnum.DECIMAL)
                .build());
        units.add(UnitEntity.builder()
                .name("metr")
                .units("m")
                .decimalPlaces(0)
                .valuesType(UnitTypeEnum.FLOAT)
                .build());
        units.add(UnitEntity.builder()
                .name("milimetr")
                .units("mm")
                .decimalPlaces(-3)
                .valuesType(UnitTypeEnum.FLOAT)
                .build());
        units.add(UnitEntity.builder()
                .name("kilogram")
                .units("kg")
                .decimalPlaces(0)
                .valuesType(UnitTypeEnum.FLOAT)
                .build());
        units.add(UnitEntity.builder()
                .name("gram")
                .units("g")
                .decimalPlaces(-3)
                .valuesType(UnitTypeEnum.FLOAT)
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
                .sku("DED1234")
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

//        int maxSeries = seriesList.stream()
//                .mapToInt(series -> Integer.parseInt(series.getSeries()))
//                .max()
//                .getAsInt();

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

    private List<EnvMasterEntity> initEnvMasterCards(List<ProductEntity> products) {
        double minWeightValue = 0d;
        double maxWeightValue = 50d;
        int roundPlaces = 2;

        return products.stream()
                .map(product -> {
                    try {
                        return envMasterSaverService.create(EnvMasterEntity.builder()
                                .product(product)
                                .validFrom(LocalDate.now())
                                .grossWeight(BigDecimal.valueOf(new Random().doubles(minWeightValue, maxWeightValue).findFirst().orElse(0.00d))
                                        .setScale(roundPlaces, RoundingMode.HALF_UP)
                                        .doubleValue())
                                .grossWeightUnit(unitCrudService.findByUnits("kg").get())
                                .build());
                    } catch (EnvMasterAlreadyExistsException e) {
                        return null;
                    }
                })
                .collect(Collectors.toList());
    }

//    private List<ProductPartAttributeEntity> initAttributesForPart(ProductPartEntity part) {
//
//    }

//    private List<TestCardEntity> initTestCards(List<ProductCategoryEntity> categories) {
//        TestCardEntity testCardEntity = cardForProductCreator
//                .createEmptyTestCardForProduct(TestCardEntity.builder()
//                        .productCategory(categories.get(0))
//                        .testCardName("Karta testowa wygenerowana podczas rozruchu aplikacji")
//                        .build());
//        return new LinkedList<>() {{
//            add(testCardEntity);
//        }};
//    }

    private List<EnvMaterialGroupEntity> initMaterialGroups() {
        List<EnvMaterialGroupEntity> materialGroups = new ArrayList<>();
        materialGroups.add(EnvMaterialGroupEntity.builder()
                .nameENG("plastic")
                .namePL("plastik")
                .build());

        return materialGroups.stream()
                .map(group -> {
                    try {
                        return this.envMaterialGroupCrudService.create(group);
                    } catch (EnvMaterialGroupAlreadyExistsException e) {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

    }

    private List<EnvMaterialEntity> initMaterials(List<EnvMaterialGroupEntity> materialGroups) {
        List<EnvMaterialEntity> materials = new ArrayList<>();
        materials.add(EnvMaterialEntity.builder()
                .group(materialGroups.get(0))
                .nameENG("PLA")
                .namePL("PLA")
                .build());
        return materials.stream()
                .map(material -> {
                    try {
                        return this.materialCrudService.create(material);
                    } catch (EnvMaterialAlreadyExistsException e) {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private void logToConsoleWhatWasCreated(String createdObjectType, String createdObjectName, Long objectId) {
        System.out.println(String.format("TEST INIT: Created %s: %s (id:%d)", createdObjectType, createdObjectName, objectId));
    }
}
