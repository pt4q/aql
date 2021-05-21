package pl.com.pt4q.product_manager.modules.product.ui.product.detail;

import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import org.springframework.beans.factory.annotation.Autowired;
import pl.com.pt4q.product_manager.modules.product.data.product.ProductEntity;
import pl.com.pt4q.product_manager.modules.product.data.product_part.ProductPartEntity;
import pl.com.pt4q.product_manager.modules.product.services.manufacturer.ManufacturerCrudService;
import pl.com.pt4q.product_manager.modules.product.services.product.AddNewOrUpdateExistingProductService;
import pl.com.pt4q.product_manager.modules.product.services.product.ProductFinderService;
import pl.com.pt4q.product_manager.modules.product.services.product.exceptions.ProductNotFoundException;
import pl.com.pt4q.product_manager.modules.product.services.product_category.ProductCategoryCrudService;
import pl.com.pt4q.product_manager.modules.product.services.product_part.ProductPartFinderService;
import pl.com.pt4q.product_manager.modules.product.services.product_part.exceptions.ProductPartNotFoundException;
import pl.com.pt4q.product_manager.modules.product.ui.product_part.ProductPartDetailView;
import pl.com.pt4q.product_manager.views.main.MainView;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Route(value = ProductDetailView.ROUTE, layout = MainView.class)
@PageTitle(ProductDetailView.PAGE_TITLE)
public class ProductDetailView extends Div implements HasUrlParameter<String> {

    public static final String PAGE_TITLE = "Product detail";
    public static final String ROUTE = "product-detail";
    public static final String QUERY_PARAM_ID_NAME = "productId";

    private SaveProductOrBackButtonsDiv saveProductOrBackButtonsDiv;
    private ProductDetailFormDiv productDetailFormDiv;
    private ProductPartsDiv productPartsDiv;

    private ProductCategoryCrudService productCategoryCrudService;
    private ManufacturerCrudService manufacturerCrudService;
    private ProductFinderService productFinderService;
    private ProductPartFinderService productPartFinderService;
    private AddNewOrUpdateExistingProductService addNewOrUpdateExistingProductService;

    private ProductEntity productEntity;

    @Autowired
    public ProductDetailView(
            ProductCategoryCrudService productCategoryCrudService,
            ManufacturerCrudService manufacturerCrudService,
            ProductFinderService productFinderService,
            ProductPartFinderService productPartFinderService,
            AddNewOrUpdateExistingProductService addNewOrUpdateExistingProductService) {

        this.productCategoryCrudService = productCategoryCrudService;
        this.manufacturerCrudService = manufacturerCrudService;
        this.productFinderService = productFinderService;
        this.productPartFinderService = productPartFinderService;
        this.addNewOrUpdateExistingProductService = addNewOrUpdateExistingProductService;

        this.productEntity = getProductFromContextOrCreateNewEmptyInstance();

        saveProductOrBackButtonsDiv = new SaveProductOrBackButtonsDiv(this.productEntity, this.addNewOrUpdateExistingProductService);
        productDetailFormDiv = new ProductDetailFormDiv(this.productEntity, productCategoryCrudService, manufacturerCrudService);
        productPartsDiv = new ProductPartsDiv();

        initSaveButtonActionListener();
        initAddNewAttributeAction();

        populateProductForm(productEntity);
        refreshPartsGrid(productEntity);

        VerticalLayout pageLayout = new VerticalLayout(
                saveProductOrBackButtonsDiv,
                productDetailFormDiv,
                productPartsDiv
        );
        pageLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        add(pageLayout);
    }

    private ProductEntity getProductFromContextOrCreateNewEmptyInstance() {
        ProductEntity product = ComponentUtil.getData(UI.getCurrent(), ProductEntity.class);
        return product != null ? product : new ProductEntity();
    }

    private void saveProductToContext(ProductEntity productEntity) {
        ComponentUtil.setData(UI.getCurrent(), ProductEntity.class, productEntity);
    }

    private void populateProductForm(ProductEntity product) {
        if (product.getProductSku() != null)
            this.productDetailFormDiv.populateForm(product);
    }

    private void refreshPartsGrid(ProductEntity product) {
        try {
            this.productPartsDiv.refreshGrid(productPartFinderService.findAllProductPartsByProduct(product));
        } catch (ProductPartNotFoundException e) {
            this.productPartsDiv.refreshGrid(Collections.emptyList());
        }
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, @OptionalParameter String urlQuery) {
        Location location = beforeEvent.getLocation();
        QueryParameters queryParameters = location.getQueryParameters();
        Map<String, List<String>> parametersMap = queryParameters.getParameters();

        if (parametersMap.containsKey(QUERY_PARAM_ID_NAME)) {
            Long id = Long.valueOf(parametersMap.get(QUERY_PARAM_ID_NAME).get(0));
            try {
                this.productEntity = productFinderService.findByIdOrThrowException(id);
                saveProductToContext(this.productEntity);
                populateProductForm(this.productEntity);
                refreshPartsGrid(this.productEntity);
            } catch (ProductNotFoundException e) {
                Notification.show(e.getMessage());
            }
        }
    }

    private void initSaveButtonActionListener() {
        this.saveProductOrBackButtonsDiv.getSaveButton().addClickListener(buttonClickEvent -> {
            System.out.println(productEntity.toString());
        });
    }

    private void initAddNewAttributeAction(){
        this.productPartsDiv.getAddNewPartButton().addClickListener(buttonClickEvent -> {
            UI ui = UI.getCurrent();

            ProductEntity productFromContext = ComponentUtil.getData(ui, ProductEntity.class);
            ProductPartEntity newPart = ProductPartEntity.builder()
                    .product(productFromContext)
                    .build();

            ComponentUtil.setData(ui, ProductPartEntity.class, newPart);
            ui.navigate(ProductPartDetailView.ROUTE);
        });
    }
}
