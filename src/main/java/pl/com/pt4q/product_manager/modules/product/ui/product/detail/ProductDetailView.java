package pl.com.pt4q.product_manager.modules.product.ui.product.detail;

import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import org.springframework.beans.factory.annotation.Autowired;
import pl.com.pt4q.product_manager.modules.product.data.product.ProductEntity;
import pl.com.pt4q.product_manager.modules.product.services.manufacturer.ManufacturerCrudService;
import pl.com.pt4q.product_manager.modules.product.services.product.AddNewProductService;
import pl.com.pt4q.product_manager.modules.product.services.product.ProductCrudFinder;
import pl.com.pt4q.product_manager.modules.product.services.product.exceptions.ProductNotFoundException;
import pl.com.pt4q.product_manager.modules.product.services.product_category.ProductCategoryCrudService;
import pl.com.pt4q.product_manager.views.main.MainView;

import java.util.List;
import java.util.Map;

@Route(value = ProductDetailView.ROUTE, layout = MainView.class)
@PageTitle(ProductDetailView.PAGE_TITLE)
public class ProductDetailView extends VerticalLayout implements HasUrlParameter<String> {

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
    private AddNewProductService addNewProductService;

    private ProductEntity productEntity;

    @Autowired
    public ProductDetailView(
            ProductCategoryCrudService productCategoryCrudService,
            ManufacturerCrudService manufacturerCrudService,
            ProductCrudFinder productCrudFinder,
            AddNewProductService addNewProductService) {

        this.productCategoryCrudService = productCategoryCrudService;
        this.manufacturerCrudService = manufacturerCrudService;
        this.productCrudFinder = productCrudFinder;
        this.addNewProductService = addNewProductService;

        this.productEntity = getProductFromContext();

        saveProductOrBackButtonsDiv = new SaveProductOrBackButtonsDiv(this.productEntity, this.addNewProductService);
        productDetailFormDiv = new ProductDetailFormDiv(this.productEntity, productCategoryCrudService, manufacturerCrudService);
        productPartsGridDiv = new ProductPartsGridDiv();
        addNewProductPartToGridDiv = new AddNewProductPartToGridDiv(this.productEntity, this.productPartsGridDiv.getProductPartsGrid());

        HorizontalLayout hl = new HorizontalLayout(this.productDetailFormDiv);

        setAlignItems(Alignment.CENTER);
        add(
                saveProductOrBackButtonsDiv,
                hl,
                productPartsGridDiv,
                addNewProductPartToGridDiv
        );
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
                ComponentUtil.setData(UI.getCurrent(), ProductEntity.class, productEntity);
            } catch (ProductNotFoundException e) {
                Notification.show(e.getMessage());
            }
        }
    }
}
