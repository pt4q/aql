package pl.com.pt4q.product_manager.modules.product.ui.product_part.attribute.attribute_value;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import pl.com.pt4q.product_manager.views.main.MainView;

@Route(value = PartAttributeValuesDetailView.ROUTE, layout = MainView.class)
@PageTitle(PartAttributeValuesDetailView.PAGE_TITLE)
public class PartAttributeValuesDetailView extends Div implements HasUrlParameter<String> {

    public static final String PAGE_TITLE = "Product part attribute versions";
    public static final String ROUTE = "product-part-attributes-versions";
    public static final String QUERY_PARAM_ID_NAME = "partAttributeId";



    @Override
    public void setParameter(BeforeEvent beforeEvent, String s) {

    }
}
