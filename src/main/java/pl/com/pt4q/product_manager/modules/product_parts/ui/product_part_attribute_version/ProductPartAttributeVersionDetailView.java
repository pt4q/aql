package pl.com.pt4q.product_manager.modules.product_parts.ui.product_part_attribute_version;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.*;
import pl.com.pt4q.product_manager.modules.product_parts.data.product_part_attribute.ProductPartAttributeEntity;
import pl.com.pt4q.product_manager.modules.product_parts.data.product_part_attribute_version.ProductPartAttributeVersionEntity;
import pl.com.pt4q.product_manager.view_utils.SaveAndBackButtonsDiv;
import pl.com.pt4q.product_manager.views.main.MainView;

import java.util.List;
import java.util.Map;

@Route(value = ProductPartAttributeVersionDetailView.ROUTE, layout = MainView.class)
@PageTitle(ProductPartAttributeVersionDetailView.PAGE_TITLE)
public class ProductPartAttributeVersionDetailView extends Div implements HasUrlParameter<String> {

    public static final String PAGE_TITLE = "Product part attribute version";
    public static final String ROUTE = "product-part-attribute-version";
    public static final String QUERY_PARAM_ID_NAME = "partAttributeValueVersionId";

    private TextField attributeValueTextField = new TextField("Attribute value");
    private ComboBox<String> productSeriesComboBox = new ComboBox<>("Product series");
    private DatePicker validFromDateDatePicker = new DatePicker("Valid from date");
    private ComboBox<String> previousAttributeVersionValue = new ComboBox<>("Previous attribute version");

    private SaveAndBackButtonsDiv saveAndBackButtonsDiv = new SaveAndBackButtonsDiv("Save value version");

    private Binder<ProductPartAttributeVersionEntity> attributeVersionForm = new Binder<>();

    private ProductPartAttributeEntity partAttributeEntity;

    public ProductPartAttributeVersionDetailView() {
        VerticalLayout layout = new VerticalLayout(
                saveAndBackButtonsDiv,
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
    public void setParameter(BeforeEvent beforeEvent, @OptionalParameter String s) {
        Location location = beforeEvent.getLocation();
        QueryParameters queryParameters = location.getQueryParameters();
        Map<String, List<String>> parametersMap = queryParameters.getParameters();

        if (parametersMap.containsKey(QUERY_PARAM_ID_NAME)) {
            Long id = Long.valueOf(parametersMap.get(QUERY_PARAM_ID_NAME).get(0));

        }
    }
}
