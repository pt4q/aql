package pl.com.pt4q.product_manager.modules.product.ui.product_part;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import lombok.Getter;
import lombok.Setter;
import pl.com.pt4q.product_manager.modules.product.data.product_part.ProductPartAttributeEntity;
import pl.com.pt4q.product_manager.modules.product.services.product_part.ProductPartCrudSaver;
import pl.com.pt4q.product_manager.modules.product.services.product_series.ProductSeriesCrudService;
import pl.com.pt4q.product_manager.modules.product.services.product_series.ProductSeriesInMemoryManager;

import java.util.List;

class PartAttributesEditorDiv extends Div {

    private TextField newAttributeNameTextField = new TextField("New attribute name");
    private TextField newAttributeValueTextField = new TextField("New attribute value");
    private ComboBox<String> productSeriesComboBox = new ComboBox<>("Product series");
    private DatePicker validFromDateDatePicker = new DatePicker("Valid from date");

    @Getter
    private Button saveButton = new Button(new Icon(VaadinIcon.PLUS_CIRCLE_O));
    private Button clearButton = new Button(new Icon(VaadinIcon.CLOSE_CIRCLE_O));
    @Getter
    private Button deleteButton = new Button(new Icon(VaadinIcon.MINUS_CIRCLE));

    private Binder<ProductPartAttributeEntity> partAttributeEntityBinder = new Binder<>(ProductPartAttributeEntity.class);

    public PartAttributesEditorDiv() {
        setId("editor-layout");

        initAttributeBinder();
        initEditorButtons();

        initCleanEditorAction();

        HorizontalLayout editorLayout = initNewAttributeForm();
        editorLayout.add(saveButton, clearButton, deleteButton);

        editorLayout.setWidthFull();
        editorLayout.setAlignItems(FlexComponent.Alignment.BASELINE);
        editorLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);

        add(editorLayout);
    }

    private void initAttributeBinder() {
//        partAttributeEntityBinder
//                .forField(newAttributeNameTextField)
//                .asRequired("Type attribute name")
//                .bind(ProductPartAttributeEntity::getAttributeName, ProductPartAttributeEntity::setAttributeName);
//        partAttributeEntityBinder
//                .forField(newAttributeValueTextField)
//                .asRequired("Type attribute value")
//                .bind(ProductPartAttributeEntity::getAttributeValue, ProductPartAttributeEntity::setAttributeValue);
//        partAttributeEntityBinder
//                .forField(productSeriesComboBox)
//                .asRequired("Product series can't be empty")
//                .bind(attribute -> attribute.getProductSeries().getSeries(),
//                        (productPartEntity, s) -> productPartEntity.setProductSeries(null));
//        partAttributeEntityBinder
//                .forField(validFromDateDatePicker)
//                .asRequired("Part model can't be empty")
//                .bind(ProductPartAttributeEntity::getValidFromDate, ProductPartAttributeEntity::setValidFromDate);
    }

    private HorizontalLayout initNewAttributeForm() {
        String minWidth = "10%";
        String maxWidth = "20%";

        newAttributeNameTextField.setMaxWidth("50%");
        newAttributeNameTextField.setMinWidth("30%");

        newAttributeValueTextField.setMaxWidth(maxWidth);
        newAttributeValueTextField.setMinWidth(minWidth);

        productSeriesComboBox.setMinWidth(minWidth);
        productSeriesComboBox.setMaxWidth(maxWidth);

        validFromDateDatePicker.setMinWidth(minWidth);
        validFromDateDatePicker.setMaxWidth(maxWidth);

        Component[] fields = new Component[]{
                newAttributeNameTextField,
                newAttributeValueTextField,
                productSeriesComboBox,
                validFromDateDatePicker
        };
        for (Component field : fields) {
            ((HasStyle) field).addClassName("full-width");
        }

        return new HorizontalLayout(fields);
    }

    private void initEditorButtons() {
        saveButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        saveButton.getElement().setProperty("title", "save attribute");
        clearButton.getElement().setProperty("title", "clear form fields");
        deleteButton.getElement().setProperty("title", "delete attribute");
    }

    private void initCleanEditorAction() {
        clearButton.addClickListener(buttonClickEvent -> clearForm());
    }

    private void clearForm() {
        populateAttributeForm(null);
    }

    public void populateAttributeForm(ProductPartAttributeEntity value) {
        partAttributeEntityBinder.readBean(value);
    }

    public ProductPartAttributeEntity getAttributeEntity(){
        return partAttributeEntityBinder.getBean();
    }
}
