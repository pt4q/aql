package pl.com.pt4q.product_manager.modules.product.ui.product.detail;

import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import pl.com.pt4q.product_manager.modules.product.data.product.ProductEntity;
import pl.com.pt4q.product_manager.modules.product_parts.data.product_part.ProductPartEntity;
import pl.com.pt4q.product_manager.modules.product.services.manufacturer.ManufacturerCrudService;
import pl.com.pt4q.product_manager.modules.product.services.product.ProductCreatorAndUpdaterService;
import pl.com.pt4q.product_manager.modules.product.services.product.ProductFinderService;
import pl.com.pt4q.product_manager.modules.product.services.product.exceptions.ProductAlreadyExistsException;
import pl.com.pt4q.product_manager.modules.product.services.product.exceptions.ProductNotFoundException;
import pl.com.pt4q.product_manager.modules.product.services.product.exceptions.ProductValidatorException;
import pl.com.pt4q.product_manager.modules.product.services.product_category.ProductCategoryCrudService;
import pl.com.pt4q.product_manager.modules.product_parts.services.product_part.ProductPartFinderService;
import pl.com.pt4q.product_manager.modules.product_parts.services.product_part.exceptions.ProductPartNotFoundException;
import pl.com.pt4q.product_manager.modules.product.ui.product.general.ProductsGeneralView;
import pl.com.pt4q.product_manager.modules.product_parts.ui.product_part.ProductPartDetailView;
import pl.com.pt4q.product_manager.view_utils.SaveObjectAndBackButtonsDiv;
import pl.com.pt4q.product_manager.views.main.MainView;

import java.util.List;
import java.util.Map;

@Log4j2
@Route(value = ProductDetailView.ROUTE, layout = MainView.class)
@PageTitle(ProductDetailView.PAGE_TITLE)
public class ProductDetailView extends Div implements HasUrlParameter<String> {

    public static final String PAGE_TITLE = "Product detail";
    public static final String ROUTE = "product-detail";
    public static final String QUERY_PARAM_ID_NAME = "productId";

    private SaveObjectAndBackButtonsDiv saveProductOrBackButtonsDiv;
    private ProductDetailEditorDiv productDetailEditorDiv;
    private ProductPartsGridDiv productPartsGridDiv;

    private ProductCategoryCrudService productCategoryCrudService;
    private ManufacturerCrudService manufacturerCrudService;
    private ProductFinderService productFinderService;
    private ProductPartFinderService productPartFinderService;
    private ProductCreatorAndUpdaterService productCreatorAndUpdaterService;

    private ProductEntity product;

    @Autowired
    public ProductDetailView(
            ProductCategoryCrudService productCategoryCrudService,
            ManufacturerCrudService manufacturerCrudService,
            ProductFinderService productFinderService,
            ProductPartFinderService productPartFinderService,
            ProductCreatorAndUpdaterService productCreatorAndUpdaterService) {

        this.productCategoryCrudService = productCategoryCrudService;
        this.manufacturerCrudService = manufacturerCrudService;
        this.productFinderService = productFinderService;
        this.productPartFinderService = productPartFinderService;
        this.productCreatorAndUpdaterService = productCreatorAndUpdaterService;

        this.product = getProductFromContextOrCreateNewEmptyInstance();

        saveProductOrBackButtonsDiv = new SaveObjectAndBackButtonsDiv("Save product");
        productDetailEditorDiv = new ProductDetailEditorDiv(this.product, productCategoryCrudService, manufacturerCrudService);
        productPartsGridDiv = new ProductPartsGridDiv();

        initSaveButtonActionListener();
        initBackButtonActionListener();
        initAddNewPartAction();

        populateProductForm(product);
        refreshPartsGrid(product);

        VerticalLayout pageLayout = new VerticalLayout(
                saveProductOrBackButtonsDiv,
                productDetailEditorDiv,
                productPartsGridDiv
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
            this.productDetailEditorDiv.populateForm(product);
    }

    private void refreshPartsGrid(ProductEntity product) {
        try {
            this.productPartsGridDiv.refreshGrid(productPartFinderService.findAllProductPartsByProduct(product));
        } catch (ProductPartNotFoundException e) {
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
                this.product = productFinderService.findByIdOrThrowException(id);
                saveProductToContext(this.product);
                populateProductForm(this.product);
                refreshPartsGrid(this.product);
            } catch (ProductNotFoundException e) {
                log.warn(showNotification(e.getMessage()));
            }
        }
    }

    private void initSaveButtonActionListener() {
        this.saveProductOrBackButtonsDiv.getSaveButton().addClickListener(buttonClickEvent -> {
            try {
                this.product = productCreatorAndUpdaterService.add(product);
                saveProductToContext(this.product);
                showNotification(String.format("The product %s has been created", product.getProductSku()));
            } catch (ProductValidatorException | ProductAlreadyExistsException e) {
                try {
                    this.product = productCreatorAndUpdaterService.updateExisting(product);
                    saveProductToContext(this.product);
                    showNotification(String.format("The product %s has been updated", product.getProductSku()));
                } catch (ProductValidatorException ex) {
                    log.error(showNotification(ex.getMessage()));
                }
            }
        });
    }

    private void initBackButtonActionListener() {
        this.saveProductOrBackButtonsDiv.getBackButton().addClickListener(buttonClickEvent -> {
            UI ui = UI.getCurrent();
            ComponentUtil.setData(ui, ProductEntity.class, null);
            ui.navigate(ProductsGeneralView.ROUTE);
        });
    }

    private void initAddNewPartAction() {
        this.productPartsGridDiv.getAddNewPartButton().addClickListener(buttonClickEvent -> {
            UI ui = UI.getCurrent();

            ProductEntity productFromContext = ComponentUtil.getData(ui, ProductEntity.class);
            ProductPartEntity newPart = ProductPartEntity.builder()
                    .product(productFromContext)
                    .build();

            ComponentUtil.setData(ui, ProductPartEntity.class, newPart);
            ui.navigate(ProductPartDetailView.ROUTE);
        });
    }

    private String showNotification(String message) {
        String theWholeMessage = String.format("%s: %s", ProductDetailView.PAGE_TITLE, message);
        Notification.show(theWholeMessage);
        return theWholeMessage;
    }
}
