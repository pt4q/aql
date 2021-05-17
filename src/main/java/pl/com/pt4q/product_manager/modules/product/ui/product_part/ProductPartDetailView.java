package pl.com.pt4q.product_manager.modules.product.ui.product_part;

import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.router.*;
import org.springframework.beans.factory.annotation.Autowired;
import pl.com.pt4q.product_manager.modules.product.data.product_part.ProductPartEntity;
import pl.com.pt4q.product_manager.modules.product.services.product_part.ProductPartFinderService;
import pl.com.pt4q.product_manager.modules.product.services.product_part.exceptions.ProductPartNotFoundException;
import pl.com.pt4q.product_manager.modules.product.services.product_series.ProductSeriesCrudService;
import pl.com.pt4q.product_manager.modules.product.ui.product.general.ProductsGeneralView;
import pl.com.pt4q.product_manager.views.main.MainView;

import java.util.List;
import java.util.Map;

@Route(value = ProductPartDetailView.ROUTE, layout = MainView.class)
@PageTitle(ProductPartDetailView.PAGE_TITLE)
public class ProductPartDetailView extends VerticalLayout implements HasUrlParameter<String> {

    public static final String PAGE_TITLE = "Product part detail";
    public static final String ROUTE = "product-part-detail";
    public static final String QUERY_PARAM_ID_NAME = "productPartId";

    private SaveProductPartOrBackButtonsDiv saveProductPartOrBackButtonsDiv;
    private PartFormDiv partFormDiv;

    private PartAttributesGridDiv attributesGridDiv;
    private PartAttributesEditorDiv attributeEditorDiv;

    private ProductSeriesCrudService productSeriesCrudService;
    //    private ProductPartAttributeCrudService productPartAttributeCrudService;
    private ProductPartFinderService productPartFinderService;

    private ProductPartEntity productPart;

    @Autowired
    public ProductPartDetailView(ProductSeriesCrudService productSeriesCrudService) {
        this.productSeriesCrudService = productSeriesCrudService;

        this.productPart = getProductPartFromContext();
//        ifPartIsNullThenRedirectToProductDetailView(productPart);

        this.saveProductPartOrBackButtonsDiv = new SaveProductPartOrBackButtonsDiv(productPart);
        this.partFormDiv = new PartFormDiv(productPart);

        Div partAttributesDiv = initAttributesAndCreateDivWithLayout(productPart, productSeriesCrudService);

        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setHorizontalComponentAlignment(Alignment.CENTER);

        add(
                saveProductPartOrBackButtonsDiv,
                partFormDiv,
                partAttributesDiv
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

    private Div initAttributesAndCreateDivWithLayout(ProductPartEntity productPart, ProductSeriesCrudService productSeriesCrudService){
        this.attributesGridDiv = new PartAttributesGridDiv();
        this.attributeEditorDiv = new PartAttributesEditorDiv(productSeriesCrudService);

        SplitLayout splitLayout = new SplitLayout();
        splitLayout.setOrientation(SplitLayout.Orientation.VERTICAL);
        splitLayout.setHeightFull();

        splitLayout.addToPrimary(attributesGridDiv);
        splitLayout.addToSecondary(attributeEditorDiv);

        Div partAttributesGridDiv = new Div(splitLayout);
        partAttributesGridDiv.setSizeFull();

        populateProductPartForm(productPart);
        return partAttributesGridDiv;
    }

    private void populateProductPartForm(ProductPartEntity part) {
        if (part.getPartModelOrPartName() != null)
            this.partFormDiv.populateForm(part);
    }

    private void refreshPartAttributes(ProductPartEntity part) {
        this.attributesGridDiv
                .refreshGrid(productPartFinderService.findAllProductPartAttributes(part));
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
                refreshPartAttributes(this.productPart);
            } catch (ProductPartNotFoundException e) {
                Notification.show(e.getMessage());
            }
        }
    }
}
