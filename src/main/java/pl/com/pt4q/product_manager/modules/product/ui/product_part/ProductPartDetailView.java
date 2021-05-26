package pl.com.pt4q.product_manager.modules.product.ui.product_part;

import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import org.springframework.beans.factory.annotation.Autowired;
import pl.com.pt4q.product_manager.modules.product.data.product.ProductEntity;
import pl.com.pt4q.product_manager.modules.product.data.product_part.ProductPartEntity;
import pl.com.pt4q.product_manager.modules.product.services.product_part_attribute.ProductPartAttributeFinderService;
import pl.com.pt4q.product_manager.modules.product.services.product_part.ProductPartCrudSaver;
import pl.com.pt4q.product_manager.modules.product.services.product_part.ProductPartFinderService;
import pl.com.pt4q.product_manager.modules.product.services.product_part.exceptions.ProductPartAlreadyExistsException;
import pl.com.pt4q.product_manager.modules.product.services.product_part.exceptions.ProductPartAttributeNotFoundException;
import pl.com.pt4q.product_manager.modules.product.services.product_part.exceptions.ProductPartNotFoundException;
import pl.com.pt4q.product_manager.modules.product.services.product_series.ProductSeriesCrudService;
import pl.com.pt4q.product_manager.modules.product.ui.product.detail.ProductDetailView;
import pl.com.pt4q.product_manager.modules.product.ui.product.general.ProductsGeneralView;
import pl.com.pt4q.product_manager.view_utils.SaveObjectAndBackButtonsDiv;
import pl.com.pt4q.product_manager.views.main.MainView;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Route(value = ProductPartDetailView.ROUTE, layout = MainView.class)
@PageTitle(ProductPartDetailView.PAGE_TITLE)
public class ProductPartDetailView extends VerticalLayout implements HasUrlParameter<String> {

    public static final String PAGE_TITLE = "Product part detail";
    public static final String ROUTE = "product-part-detail";
    public static final String QUERY_PARAM_ID_NAME = "productPartId";

    private SaveObjectAndBackButtonsDiv saveProductPartOrBackButtonsDiv;
    private ProductPartFormDiv productPartFormDiv;
    private ProductPartAttributesDiv productPartAttributesDiv;

    private ProductSeriesCrudService productSeriesCrudService;
    private ProductPartCrudSaver productPartCrudSaver;
    private ProductPartFinderService productPartFinderService;
    private ProductPartAttributeFinderService productPartAttributeFinderService;

    private ProductPartEntity productPart;

    @Autowired
    public ProductPartDetailView(ProductSeriesCrudService productSeriesCrudService,
                                 ProductPartCrudSaver productPartCrudSaver,
                                 ProductPartFinderService productPartFinderService,
                                 ProductPartAttributeFinderService productPartAttributeFinderService) {

        this.productSeriesCrudService = productSeriesCrudService;
        this.productPartCrudSaver = productPartCrudSaver;
        this.productPartFinderService = productPartFinderService;
        this.productPartAttributeFinderService = productPartAttributeFinderService;

        this.productPart = getProductPartFromContext();
//        ifPartIsNullThenRedirectToProductDetailView(productPart);

        this.saveProductPartOrBackButtonsDiv = new SaveObjectAndBackButtonsDiv("Save part");
        this.productPartFormDiv = new ProductPartFormDiv(this.productPart);
        this.productPartAttributesDiv = new ProductPartAttributesDiv(this.productPart, productSeriesCrudService, productPartCrudSaver, productPartAttributeFinderService);

        initSaveButtonAction();
//        initBackButtonAction();
        populateProductPartForm(productPart);
        refreshPartAttributesGrid(productPart);

        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setHorizontalComponentAlignment(Alignment.CENTER);

        add(
                saveProductPartOrBackButtonsDiv,
                productPartFormDiv,
                productPartAttributesDiv
        );
    }

    private ProductPartEntity getProductPartFromContext() {
        return ComponentUtil.getData(UI.getCurrent(), ProductPartEntity.class);
    }

    //// TODO: 14.05.2021 Serialize and deserialize object using cookies -> view_utils -> ObjectToCookieManager.class
    private void ifPartIsNullThenRedirectToProductDetailView(ProductPartEntity productPart) {
        if (productPart == null)
            UI.getCurrent().navigate(ProductsGeneralView.ROUTE);
    }

    private void saveProductPartToContext(ProductPartEntity productPart) {
        ComponentUtil.setData(UI.getCurrent(), ProductPartEntity.class, productPart);
    }

    private void populateProductPartForm(ProductPartEntity part) {
        if (part != null)
            this.productPartFormDiv.populatePartForm(part);
    }

    private void refreshPartAttributesGrid(ProductPartEntity part) {
//        this.partAttributesDiv.refreshAttributesGrid(part);
        try {
            this.productPartAttributesDiv.refreshGrid(productPartAttributeFinderService.findAllProductPartsAttributesByProductPart(part));
        } catch (ProductPartAttributeNotFoundException e) {
            this.productPartAttributesDiv.refreshGrid(Collections.emptyList());
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
                this.productPart = productPartFinderService.findByIdOrThrowException(id);
                saveProductPartToContext(this.productPart);
                populateProductPartForm(this.productPart);
                refreshPartAttributesGrid(this.productPart);
            } catch (ProductPartNotFoundException e) {
                Notification.show(e.getMessage());
            }
        }
    }

    private void initSaveButtonAction() {
        this.saveProductPartOrBackButtonsDiv.getSaveButton().addClickListener(buttonClickEvent -> {
            try {
                ProductPartEntity partFromForm = this.productPartFormDiv.getPartFromForm();
                productPart.setPartModelOrPartName(partFromForm.getPartModelOrPartName());
                productPart.setPartDescription(partFromForm.getPartDescription());
                this.productPart = productPartCrudSaver.save(productPart);

                Notification.show(String.format("The part %s has been saved in the product %s", productPart.getPartModelOrPartName(), productPart.getProduct().getProductSku()));
            } catch (ProductPartAlreadyExistsException e) {
                try {
                    this.productPart = productPartCrudSaver.update(productPart);

                    Notification.show(String.format("The part %s has been updated in the product %s", productPart.getPartModelOrPartName(), productPart.getProduct().getProductSku()));
                } catch (ProductPartNotFoundException ex) {
                    Notification.show(String.format("Can't save part because: %s", ex.getMessage()));
                }
            }
        });
    }

//    private void initBackButtonAction() {
//        this.saveProductPartOrBackButtonsDiv.getBackButton().addClickListener(buttonClickEvent -> {
//            UI ui = UI.getCurrent();
//            if (productPart != null) {
//                ProductEntity product = productPart.getProduct();
//                ComponentUtil.setData(ui, ProductEntity.class, product);
//            }
//            ui.navigate(ProductDetailView.ROUTE);
//        });
//    }
}
