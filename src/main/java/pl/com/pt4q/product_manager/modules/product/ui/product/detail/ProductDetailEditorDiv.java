package pl.com.pt4q.product_manager.modules.product.ui.product.detail;

import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import pl.com.pt4q.product_manager.modules.product.data.manufacturer.ManufacturerEntity;
import pl.com.pt4q.product_manager.modules.product.data.product.ProductEntity;
import pl.com.pt4q.product_manager.modules.product.data.product_category.ProductCategoryEntity;
import pl.com.pt4q.product_manager.modules.product.services.manufacturer.ManufacturerCrudService;
import pl.com.pt4q.product_manager.modules.product.services.manufacturer.ManufacturersInMemoryManager;
import pl.com.pt4q.product_manager.modules.product.services.product_category.ProductCategoriesInMemoryManager;
import pl.com.pt4q.product_manager.modules.product.services.product_category.ProductCategoryCrudService;

import java.util.Optional;

class ProductDetailEditorDiv extends Div {

    private ComboBox<String> categoryComboBox = new ComboBox("Product category");
    private ComboBox<String> manufacturerComboBox = new ComboBox("Product manufacturer");
    private TextField productSkuTextField = new TextField("Product SKU");

    private Binder<ProductEntity> productBinder = new Binder<>(ProductEntity.class);

    private ProductCategoryCrudService productCategoryCrudService;
    private ManufacturerCrudService manufacturerCrudService;

    private ProductCategoriesInMemoryManager categoriesInMemoryListManager;
    private ManufacturersInMemoryManager manufacturersInMemoryManager;

    private ProductEntity product;

    public ProductDetailEditorDiv(ProductEntity product,
                                  ProductCategoryCrudService productCategoryCrudService,
                                  ManufacturerCrudService manufacturerCrudService) {

        this.productCategoryCrudService = productCategoryCrudService;
        this.manufacturerCrudService = manufacturerCrudService;

        this.product = product;

        this.categoriesInMemoryListManager = new ProductCategoriesInMemoryManager(productCategoryCrudService.getAll());
        this.manufacturersInMemoryManager = new ManufacturersInMemoryManager(manufacturerCrudService.getAll());

        configSkuTextField();
        configCategoryComboBox();
        configManufacturerComboBox();

        FormLayout productFormLayout = initFormLayout();
        initBinder();

        add(productFormLayout);
    }

    private void saveProductToContext(ProductEntity productEntity) {
        ComponentUtil.setData(UI.getCurrent(), ProductEntity.class, productEntity);
    }

    private FormLayout initFormLayout() {
        FormLayout formLayout = new FormLayout();
        formLayout.add(productSkuTextField);
        formLayout.add(manufacturerComboBox);
        formLayout.add(categoryComboBox);
        return formLayout;
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

    private void configSkuTextField() {
        if (product == null)
            this.productSkuTextField.setAutofocus(true);

        this.productSkuTextField.addValueChangeListener(event -> {
            String sku = event.getValue().toUpperCase();
//            this.product = initProductIfNullOrBypass(product);
            this.product.setProductSku(sku);
            saveProductToContext(product);
        });
    }

    private void configCategoryComboBox() {
        this.categoryComboBox.setItems(categoriesInMemoryListManager.getCategoriesNames());
        this.categoryComboBox.addValueChangeListener(event -> {
//            this.product = initProductIfNullOrBypass(product);
            Optional<ProductCategoryEntity> category = categoriesInMemoryListManager.getByName(event.getValue());
            category.ifPresent(productCategoryEntity -> this.product.setProductCategory(productCategoryEntity));
            saveProductToContext(product);
        });
    }

    private void configManufacturerComboBox() {
        this.manufacturerComboBox.setItems(manufacturersInMemoryManager.getManufacturersNames());
        this.manufacturerComboBox.addValueChangeListener(event -> {
//            this.product = initProductIfNullOrBypass(product);
            Optional<ManufacturerEntity> manufacturer = manufacturersInMemoryManager.getByName(event.getValue());
            manufacturer.ifPresent(manufacturerEntity -> this.product.setManufacturer(manufacturerEntity));
            saveProductToContext(product);
        });
    }

    private void cleanForm() {
        populateForm(null);
    }

    public void populateForm(ProductEntity product) {
        this.product = product;
        productBinder.readBean(this.product);
    }
}
