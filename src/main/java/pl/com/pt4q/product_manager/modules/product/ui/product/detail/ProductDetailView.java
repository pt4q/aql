package pl.com.pt4q.product_manager.modules.product.ui.product.detail;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import org.springframework.ui.context.support.UiApplicationContextUtils;
import pl.com.pt4q.product_manager.modules.product.data.product.ProductEntity;
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
    private AddNewProductPartToGridDiv addNewProductPartToGridDiv;

    private ProductEntity productEntity;

    public ProductDetailView() {
        this.productEntity = initProduct();

        this.saveProductOrBackButtonsDiv = new SaveProductOrBackButtonsDiv();
        this.productDetailFormDiv = new ProductDetailFormDiv();
        this.productPartsGridDiv = new ProductPartsGridDiv();
        this.addNewProductPartToGridDiv = new AddNewProductPartToGridDiv(this.productEntity, this.productPartsGridDiv.getProductPartsGrid());

        HorizontalLayout hl = new HorizontalLayout(this.productDetailFormDiv);

        setAlignItems(Alignment.CENTER);
        add(
                this.saveProductOrBackButtonsDiv,
                hl,
                this.productPartsGridDiv,
                this.addNewProductPartToGridDiv
        );
    }

    private ProductEntity initProduct(){
        return productEntity != null ? productEntity : new ProductEntity();
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
