package pl.com.pt4q.product_manager.modules.product.ui.product_part_attribute;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import pl.com.pt4q.product_manager.views.main.MainView;

@Route(value = ProductPartAttributeDetailView.ROUTE, layout = MainView.class)
@PageTitle(ProductPartAttributeDetailView.PAGE_TITLE)
public class ProductPartAttributeDetailView extends Div implements HasUrlParameter<String> {

    public static final String PAGE_TITLE = "Product part attributes";
    public static final String ROUTE = "product-part-attributes";
    public static final String QUERY_PARAM_ID_NAME = "productPartId";

    private ProductPartAttributeEditorDiv attributesEditorDiv;
    private ProductPartAttributeValueVersionsDiv productPartAttributeValueVersionsDiv;

    public ProductPartAttributeDetailView() {
        this.attributesEditorDiv = new ProductPartAttributeEditorDiv();
        this.productPartAttributeValueVersionsDiv = new ProductPartAttributeValueVersionsDiv();

        VerticalLayout layout = new VerticalLayout(attributesEditorDiv, productPartAttributeValueVersionsDiv);
        layout.setAlignItems(FlexComponent.Alignment.CENTER);
        layout.setWidthFull();

        setSizeFull();
        add(layout);
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, String s) {

    }
}
