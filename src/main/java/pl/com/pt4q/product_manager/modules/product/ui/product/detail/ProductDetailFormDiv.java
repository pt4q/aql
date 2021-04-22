package pl.com.pt4q.product_manager.modules.product.ui.product.detail;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import pl.com.pt4q.product_manager.modules.product.data.manufacturer.ManufacturerEntity;
import pl.com.pt4q.product_manager.modules.product.data.product.ProductEntity;
import pl.com.pt4q.product_manager.modules.product.services.manufacturer.ManufacturerCrudService;
import pl.com.pt4q.product_manager.modules.product.services.manufacturer.ManufacturersInMemoryManager;
import pl.com.pt4q.product_manager.modules.product.services.product_category.ProductCategoriesInMemoryManager;
import pl.com.pt4q.product_manager.modules.product.services.product_category.ProductCategoryCrudService;

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

    public ProductDetailFormDiv(ProductEntity product,
                                ProductCategoryCrudService productCategoryCrudService,
                                ManufacturerCrudService manufacturerCrudService) {
        this.product = product;
        this.productCategoryCrudService = productCategoryCrudService;
        this.manufacturerCrudService = manufacturerCrudService;

        this.categoriesInMemoryListManager = new ProductCategoriesInMemoryManager(productCategoryCrudService.getAll());
        this.manufacturersInMemoryManager = new ManufacturersInMemoryManager(manufacturerCrudService.getAll());

        configCategoryComboBox();
        configManufacturerComboBox();

        initFormLayout();
        initBinder();

        add(formLayout);
    }

    private void initFormLayout() {
        formLayout.add(categoryComboBox);
        formLayout.add(manufacturerComboBox);
        formLayout.add(productSkuTextField);
    }

    private void initBinder() {
        productBinder
                .forField(categoryComboBox)
                .asRequired("Product category can't be empty");
        productBinder
                .forField(manufacturerComboBox)
                .asRequired("Product manufacturer can't be empty");
        productBinder
                .forField(productSkuTextField)
                .asRequired("Product SKU can't be empty")
                .bind(ProductEntity::getProductSku, ProductEntity::setProductSku);
    }

    private void configCategoryComboBox() {
        categoryComboBox.setItems(categoriesInMemoryListManager.getCategoriesNames());
    }

    private void configManufacturerComboBox() {
        manufacturerComboBox.setItems(manufacturersInMemoryManager.getManufacturersNames());
        manufacturerComboBox.addValueChangeListener(event -> {
           product.setManufacturer(ManufacturerEntity.builder()
                   .manufacturerName(event.getValue()).build());

           manufacturerComboBox.setValue(event.getValue());
        });
    }

    private void cleanForm() {
        populateForm(null);
    }

    private void populateForm(ProductEntity product) {
        this.product = product;
        productBinder.readBean(this.product);
    }
}
