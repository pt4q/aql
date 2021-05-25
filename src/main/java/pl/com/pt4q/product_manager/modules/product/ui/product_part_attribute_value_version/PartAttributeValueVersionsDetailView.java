package pl.com.pt4q.product_manager.modules.product.ui.product_part_attribute_value_version;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import pl.com.pt4q.product_manager.modules.product.data.product_part_attribute.ProductPartAttributeEntity;
import pl.com.pt4q.product_manager.modules.product.data.product_part_attribute_value_version.ProductPartAttributeValueVersionEntity;
import pl.com.pt4q.product_manager.views.main.MainView;

@Route(value = PartAttributeValueVersionsDetailView.ROUTE, layout = MainView.class)
@PageTitle(PartAttributeValueVersionsDetailView.PAGE_TITLE)
public class PartAttributeValueVersionsDetailView extends Div implements HasUrlParameter<String> {

    public static final String PAGE_TITLE = "Product part attribute versions";
    public static final String ROUTE = "product-part-attributes-versions";
    public static final String QUERY_PARAM_ID_NAME = "partAttributeId";

    private TextField attributeValueTextField = new TextField("Attribute value");
    private ComboBox<String> productSeriesComboBox = new ComboBox<>("Product series");
    private DatePicker validFromDateDatePicker = new DatePicker("Valid from date");
    private ComboBox<String> previousAttributeVersionValue = new ComboBox<>("Previous attribute version");

    private Binder<ProductPartAttributeValueVersionEntity> attributeVersionForm = new Binder<>();

    private ProductPartAttributeEntity partAttributeEntity;

    public PartAttributeValueVersionsDetailView() {
        VerticalLayout layout = new VerticalLayout(
                attributeValueTextField,
                productSeriesComboBox,
                validFromDateDatePicker,
                previousAttributeVersionValue
        );
        layout.setAlignItems(FlexComponent.Alignment.CENTER);
        layout.setWidthFull();

        setWidthFull();
        add(layout);
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, String s) {

    }
}
