package pl.com.pt4q.product_manager.modules.product.ui.product.detail;

import pl.com.pt4q.product_manager.views.main.MainView;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;

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

    public ProductDetailView() {
        this.saveProductOrBackButtonsDiv = new SaveProductOrBackButtonsDiv();
        this.productDetailFormDiv = new ProductDetailFormDiv();
        this.productPartsGridDiv = new ProductPartsGridDiv();

        add(this.saveProductOrBackButtonsDiv,
                this.productDetailFormDiv,
                this.productPartsGridDiv);
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, @OptionalParameter String urlQuery) {
        Location location = beforeEvent.getLocation();
        QueryParameters queryParameters = location.getQueryParameters();
        Map<String, List<String>> parametersMap = queryParameters.getParameters();

        if (parametersMap.containsKey(QUERY_PARAM_ID_NAME)) {
//            try {
            Long id = Long.valueOf(parametersMap.get(QUERY_PARAM_ID_NAME).get(0));
//                this.testCard = testCardFinder.findTestCardById(id);

//            } catch (TestCardNotFoundException e) {
//                Notification.show(e.getMessage());
//            }
        }
    }
}
