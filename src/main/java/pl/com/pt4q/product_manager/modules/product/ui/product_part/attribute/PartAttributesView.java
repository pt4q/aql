package pl.com.pt4q.product_manager.modules.product.ui.product_part.attribute;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import pl.com.pt4q.product_manager.views.main.MainView;

@Route(value = PartAttributesView.ROUTE, layout = MainView.class)
@PageTitle(PartAttributesView.PAGE_TITLE)
public class PartAttributesView extends Div implements HasUrlParameter<String> {

    public static final String PAGE_TITLE = "Product part attributes";
    public static final String ROUTE = "product-part-attributes";
    public static final String QUERY_PARAM_ID_NAME = "productPartId";



    @Override
    public void setParameter(BeforeEvent beforeEvent, String s) {

    }
}
