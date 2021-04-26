package pl.com.pt4q.product_manager.modules.product.ui.product.detail;

import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import org.springframework.beans.factory.annotation.Autowired;
import pl.com.pt4q.product_manager.modules.product.data.product.ProductEntity;
import pl.com.pt4q.product_manager.modules.product.services.manufacturer.ManufacturerCrudService;
import pl.com.pt4q.product_manager.modules.product.services.product.AddNewOrUpdateExistingProductService;
import pl.com.pt4q.product_manager.modules.product.services.product.ProductCrudFinder;
import pl.com.pt4q.product_manager.modules.product.services.product.exceptions.ProductNotFoundException;
import pl.com.pt4q.product_manager.modules.product.services.product_category.ProductCategoryCrudService;
import pl.com.pt4q.product_manager.modules.product.ui.product.general.ProductsGeneralView;
import pl.com.pt4q.product_manager.views.main.MainView;

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
    private ProductPartsGridDiv productPartsGridDiv;
    private AddNewProductPartToGridDiv addNewProductPartToGridDiv;

    private ProductCategoryCrudService productCategoryCrudService;
    private ManufacturerCrudService manufacturerCrudService;
    private ProductCrudFinder productCrudFinder;
    private AddNewOrUpdateExistingProductService addNewOrUpdateExistingProductService;

    private ProductEntity productEntity;

    @Autowired
    public ProductDetailView(
            ProductCategoryCrudService productCategoryCrudService,
            ManufacturerCrudService manufacturerCrudService,
            ProductCrudFinder productCrudFinder,
            AddNewOrUpdateExistingProductService addNewOrUpdateExistingProductService) {

        this.productCategoryCrudService = productCategoryCrudService;
        this.manufacturerCrudService = manufacturerCrudService;
        this.productCrudFinder = productCrudFinder;
        this.addNewOrUpdateExistingProductService = addNewOrUpdateExistingProductService;

        this.productEntity = getProductFromContext();

        saveProductOrBackButtonsDiv = new SaveProductOrBackButtonsDiv(this.productDetailFormDiv, this.addNewOrUpdateExistingProductService);
        productDetailFormDiv = new ProductDetailFormDiv(productCategoryCrudService, manufacturerCrudService);
        productPartsGridDiv = new ProductPartsGridDiv();
        addNewProductPartToGridDiv = new AddNewProductPartToGridDiv(this.productEntity, this.productPartsGridDiv.getProductPartsGrid());

        HorizontalLayout productInfoHorizontalLayout = new HorizontalLayout(this.productDetailFormDiv);

        VerticalLayout pageLayout = new VerticalLayout(
                saveProductOrBackButtonsDiv,
                productInfoHorizontalLayout,
                productPartsGridDiv,
                addNewProductPartToGridDiv
        );
        pageLayout.setAlignItems(FlexComponent.Alignment.CENTER);

        add(pageLayout);
    }

    private ProductEntity getProductFromContext() {
        return ComponentUtil.getData(UI.getCurrent(), ProductEntity.class);
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, @OptionalParameter String urlQuery) {
        Location location = beforeEvent.getLocation();
        QueryParameters queryParameters = location.getQueryParameters();
        Map<String, List<String>> parametersMap = queryParameters.getParameters();

        if (parametersMap.containsKey(QUERY_PARAM_ID_NAME)) {
            try {
                Long id = Long.valueOf(parametersMap.get(QUERY_PARAM_ID_NAME).get(0));
                this.productEntity = productCrudFinder.findByIdOrThrowException(id);
                saveProductToContext(productEntity);
                this.productDetailFormDiv.populateForm(this.productEntity);
            } catch (ProductNotFoundException e) {
                Notification.show(e.getMessage());
            }
        }
    }

    private void saveProductToContext(ProductEntity productEntity){
        ComponentUtil.setData(UI.getCurrent(),ProductEntity.class, productEntity);
    }
}
