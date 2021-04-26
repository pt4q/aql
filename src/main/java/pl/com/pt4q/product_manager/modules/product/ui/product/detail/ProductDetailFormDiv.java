package pl.com.pt4q.product_manager.modules.product.ui.product.detail;

import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationResult;
import com.vaadin.flow.data.validator.RegexpValidator;
import pl.com.pt4q.product_manager.modules.product.data.manufacturer.ManufacturerEntity;
import pl.com.pt4q.product_manager.modules.product.data.product.ProductEntity;
import pl.com.pt4q.product_manager.modules.product.data.product_category.ProductCategoryEntity;
import pl.com.pt4q.product_manager.modules.product.services.manufacturer.ManufacturerCrudService;
import pl.com.pt4q.product_manager.modules.product.services.manufacturer.ManufacturersInMemoryManager;
import pl.com.pt4q.product_manager.modules.product.services.product_category.ProductCategoriesInMemoryManager;
import pl.com.pt4q.product_manager.modules.product.services.product_category.ProductCategoryCrudService;

import java.util.Locale;
import java.util.Optional;

class ProductDetailFormDiv extends Div {

    //    private H2 formLabel = new H2("Product info");
    private ComboBox<String> categoryComboBox = new ComboBox("Product category");
    private ComboBox<String> manufacturerComboBox = new ComboBox("Product manufacturer");
    private TextField productSkuTextField = new TextField("Product SKU");

    private Binder<ProductEntity> productBinder = new Binder<>(ProductEntity.class);
    private FormLayout formLayout = new FormLayout();

    private ProductCategoryCrudService productCategoryCrudService;
    private ManufacturerCrudService manufacturerCrudService;

    private ProductCategoriesInMemoryManager categoriesInMemoryListManager;
    private ManufacturersInMemoryManager manufacturersInMemoryManager;
    private ProductEntity product;

    public ProductDetailFormDiv(ProductCategoryCrudService productCategoryCrudService,
                                ManufacturerCrudService manufacturerCrudService) {

        this.productCategoryCrudService = productCategoryCrudService;
        this.manufacturerCrudService = manufacturerCrudService;

        this.categoriesInMemoryListManager = new ProductCategoriesInMemoryManager(productCategoryCrudService.getAll());
        this.manufacturersInMemoryManager = new ManufacturersInMemoryManager(manufacturerCrudService.getAll());

        configSkuTextField();
        configCategoryComboBox();
        configManufacturerComboBox();

        initFormLayout();
        initBinder();

        add(formLayout);
    }

    private void saveProductToContext(ProductEntity productEntity){
        ComponentUtil.setData(UI.getCurrent(),ProductEntity.class, productEntity);
    }

    private void initFormLayout() {
        formLayout.add(categoryComboBox);
        formLayout.add(manufacturerComboBox);
        formLayout.add(productSkuTextField);
    }

    private void initBinder() {
        productBinder
                .forField(categoryComboBox)
                .asRequired("Product category can't be empty")
                .bind(
                        productEntity -> productEntity.getProductCategory().getCategoryName(),
                        (productEntity, s) -> productEntity.setProductCategory(categoriesInMemoryListManager.getByName(s).get())
                );
        productBinder
                .forField(manufacturerComboBox)
                .asRequired("Product manufacturer can't be empty")
                .bind(
                        productEntity -> productEntity.getManufacturer().getManufacturerName(),
                        (productEntity, s) -> productEntity.setManufacturer(manufacturersInMemoryManager.getByName(s).get())
                );
        productBinder
                .forField(productSkuTextField)
                .asRequired("Product SKU can't be empty")
                .withValidator(sku -> sku.matches("^[A-Z0-9, -]{4,}$"), "Bad SKU format")
                .bind(ProductEntity::getProductSku, ProductEntity::setProductSku);
    }

    private void configSkuTextField(){
        productSkuTextField.addValueChangeListener(event -> {
            String sku = event.getValue().toUpperCase();
            this.product = initProductIfNullOrBypass(product);
            this.product.setProductSku(sku);
        });
    }

    private void configCategoryComboBox() {
        categoryComboBox.setItems(categoriesInMemoryListManager.getCategoriesNames());
        categoryComboBox.addValueChangeListener(event -> {
            this.product = initProductIfNullOrBypass(product);
            Optional<ProductCategoryEntity> category = categoriesInMemoryListManager.getByName(event.getValue());
            category.ifPresent(productCategoryEntity -> this.product.setProductCategory(productCategoryEntity));
            saveProductToContext(product);
        });
    }

    private void configManufacturerComboBox() {
        manufacturerComboBox.setItems(manufacturersInMemoryManager.getManufacturersNames());
        manufacturerComboBox.addValueChangeListener(event -> {
            this.product = initProductIfNullOrBypass(product);
            Optional<ManufacturerEntity> manufacturer = manufacturersInMemoryManager.getByName(event.getValue());
            manufacturer.ifPresent(manufacturerEntity -> this.product.setManufacturer(manufacturerEntity));
            saveProductToContext(product);
        });
    }

    private ProductEntity initProductIfNullOrBypass(ProductEntity product){
        return product != null ? product : new ProductEntity();
    }

    private void cleanForm() {
        populateForm(null);
    }

    public void populateForm(ProductEntity product) {
        this.product = product;
        productBinder.readBean(this.product);
    }

    public ProductEntity getProductToSave(){
        return this.product;
    }
}
